import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserGoalComponent } from './user-goal.component';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
import { of } from 'rxjs';
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


describe('UserGoalComponent', () => {
  let component: UserGoalComponent;
  let fixture: ComponentFixture<UserGoalComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockUserService: jasmine.SpyObj<UserService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockUserService = jasmine.createSpyObj('UserService', ['updateUser']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser', 'getLoginInfo', 'saveUser']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [UserGoalComponent],
      providers: [
        { provide: UserService, useValue: mockUserService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(UserGoalComponent);
    component = fixture.componentInstance;
    component.loggedUser = mockUser; // Initialize loggedUser with the mock user
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser and imageView if token exists', async() => {
      const mockLoginInfo = { info: {}, isLogged: true, user: mockUser, isAdmin: true, imageView: mockUser.image };
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      mockTokenStorage.getLoginInfo.and.returnValue(mockLoginInfo);

      await component.ngOnInit();

      

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockTokenStorage.getUser).toHaveBeenCalled();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(component.loggedUser).toEqual(mockUser);

    });

    it('should not initialize loggedUser if token does not exist', async() => {
      mockTokenStorage.getToken.and.returnValue(null);
      component.loggedUser = undefined;

      await component.ngOnInit();

      expect(component.loggedUser).toBeUndefined();
      expect(component.imageView).toBeUndefined();
    });
  });

  describe('updateUser', () => {
    it('should update user successfully and set addGoal to true', async () => {
      mockUserService.updateUser.and.returnValue(Promise.resolve(mockUser));
      component.loggedUser = mockUser;
      fixture.detectChanges();

      await component.updateUser();

      expect(mockUserService.updateUser).toHaveBeenCalledWith(1,mockUser);
      expect(mockTokenStorage.saveUser).toHaveBeenCalledWith(mockUser);
      expect(component.noGoal).toBeFalse();
      expect(component.addGoal).toBeTrue();
    });

    it('should handle errors and set noGoal to true', async () => {
      mockUserService.updateUser.and.returnValue(Promise.reject('Error occurred'));
      component.loggedUser = mockUser;
      fixture.detectChanges();

      await component.updateUser();

      expect(mockUserService.updateUser).toHaveBeenCalledWith(1, mockUser);
      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });
});