import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NavBarComponent } from './navbar.component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { of } from 'rxjs';
import { User } from '../../Interfaces/User/user.model';
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

describe('NavBarComponent', () => {
  let component: NavBarComponent;
  let fixture: ComponentFixture<NavBarComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockLoginService: jasmine.SpyObj<LoginService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockLoginService = jasmine.createSpyObj('LoginService', ['']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser', 'getLoginInfo', 'signOut']);

    await TestBed.configureTestingModule({
      declarations: [NavBarComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: LoginService, useValue: mockLoginService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(NavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser, isLoggedUser, and isAdmin if token exists', () => {
      const mockLoginInfo = { info: {}, isLogged: true, user: mockUser, isAdmin: true, imageView: mockUser.image as SafeUrl };
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      mockTokenStorage.getLoginInfo.and.returnValue(mockLoginInfo);

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockTokenStorage.getUser).toHaveBeenCalled();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(component.loggedUser).toEqual(mockUser);
      expect(component.isLoggedUser).toBeTrue();
      expect(component.isAdmin).toBeTrue();
      expect(component.imageView).toBe(mockUser.image as SafeUrl);
    });

    it('should not initialize loggedUser if token does not exist', () => {
      mockTokenStorage.getToken.and.returnValue(null);

      component.ngOnInit();

      expect(component.loggedUser).toBeUndefined();
      expect(component.isLoggedUser).toBeFalse();
      expect(component.isAdmin).toBeFalse();
    });
  });

  describe('logOut', () => {
    it('should log out and navigate to /', async () => {
      component.isLoggedUser = true;

      await component.logOut();

      expect(component.isLoggedUser).toBeFalse();
      expect(mockTokenStorage.signOut).toHaveBeenCalled();
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/']);
    });
  });

  describe('getImage', () => {
    it('should set imageView from login info', () => {
      const mockLoginInfo = { info: {}, isLogged: true, user: mockUser, isAdmin: true, imageView: mockUser.image as SafeUrl };
      mockTokenStorage.getLoginInfo.and.returnValue(mockLoginInfo);

      component.getImage();

      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(component.imageView).toBe(mockUser.image as SafeUrl);
    });
  });
});