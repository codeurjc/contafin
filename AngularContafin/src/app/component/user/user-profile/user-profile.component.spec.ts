import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserProfileComponent } from './user-profile.component';
import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
import { Chart } from 'chart.js';
import { User } from '../../../Interfaces/User/user.model';
import { SafeUrl } from '@angular/platform-browser';

const mockUser: User = {
     id : 1,
     name: 'John Doe',
     email: 'admin@admin.es',
     passwordHash: 'hashedpassword',
     level: 1,
     points: 100,
     streak: 5,
     fluency: 80,
     dailyGoal: 10,
     lastConnection: '2023-10-01T12:00:00Z',
     lastUnit: 1,
     lastLesson: 1,
     progress: [1, 2, 3],
     remainingGoals: 5,
     exp: 1000,
     needexp: 500,
     image: 'https://example.com/image.jpg' as SafeUrl, // Mocked URL
     // roles: ['ROLE_USER', 'ROLE_ADMIN'],
     roles: ['ROLE_USER']};

describe('UserProfileComponent', () => {
  let component: UserProfileComponent;
  let fixture: ComponentFixture<UserProfileComponent>;
  let mockLoginService: jasmine.SpyObj<LoginService>;
  let mockUserService: jasmine.SpyObj<UserService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockUserService = jasmine.createSpyObj('UserService', ['getProgress']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser', 'getLoginInfo']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [UserProfileComponent],
      providers: [
        { provide: LoginService, useValue: mockLoginService },
        { provide: UserService, useValue: mockUserService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(UserProfileComponent);
    component = fixture.componentInstance;
    component.loggedUser = mockUser; // Initialize loggedUser with the mock user
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should fetch user progress and call lineChart', async () => {
      const mockProgress = [1, 2, 3, 4, 5, 6, 7];
      const mockLoginInfo = { info: {}, isLogged: true, user: mockUser, isAdmin: true, imageView: mockUser.image };
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      mockTokenStorage.getLoginInfo.and.returnValue(mockLoginInfo);
      mockUserService.getProgress.and.returnValue(Promise.resolve(mockProgress));
      spyOn(component, 'lineChart');

      await component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockUserService.getProgress).toHaveBeenCalledWith(mockUser.id);
      expect(component.lineChart).toHaveBeenCalledWith(mockProgress);
    });

    it('should handle errors when fetching user progress', async () => {
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      mockUserService.getProgress.and.returnValue(Promise.reject('Error occurred'));

      await component.ngOnInit();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('lineChart', () => {
    it('should create a chart with the correct data', () => {
      const mockProgress = [1, 2, 3, 4, 5, 6, 7];

      component.lineChart(mockProgress);

      expect(component.chart).toBeDefined();
      expect(component.chart.data.datasets[0].data).toEqual(mockProgress);
      expect(component.chart.data.labels.length).toBe(7);
    });
  });
});