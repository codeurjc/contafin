import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../services/login.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { ErrorService } from '../../services/error.service';
import { of } from 'rxjs';
import { InjectionToken } from '@angular/core';

export const WINDOW = new InjectionToken('window');

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockLoginService: jasmine.SpyObj<LoginService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockModalService: jasmine.SpyObj<NgbModal>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockLoginService = jasmine.createSpyObj('LoginService', ['logIn']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'saveToken', 'saveLoginInfo', 'signOut', 'getUser']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);
  
    

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: LoginService, useValue: mockLoginService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: NgbModal, useValue: mockModalService }
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.reloadPage = function() { };
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should set isLoggedIn to true if token exists', () => {
      mockTokenStorage.getToken.and.returnValue('mockToken');

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(component.isLoggedIn).toBeTrue();
    });

    it('should set isLoggedIn to false if token does not exist', () => {
      mockTokenStorage.getToken.and.returnValue(null);

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(component.isLoggedIn).toBeFalse();
    });
  });

  describe('logIn', () => {
    it('should log in successfully and navigate to /home if formkind is "1"', async () => {
      const mockUser = { token: 'mockToken', user: { id: 1, name: 'Test User' } };
      mockLoginService.logIn.and.returnValue(Promise.resolve(mockUser));
      component.formkind = '1';

      await component.logIn(new Event('submit'), 'test@example.com', 'password');

      expect(mockLoginService.logIn).toHaveBeenCalledWith('test@example.com', 'password');
      expect(mockTokenStorage.saveToken).toHaveBeenCalledWith('mockToken');
      expect(mockTokenStorage.saveLoginInfo).toHaveBeenCalledWith(mockUser);
      expect(component.isLoginFailed).toBeFalse();
      expect(component.isLoggedIn).toBeTrue();
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
      expect(mockLoginService.logIn).toHaveBeenCalledWith('test@example.com', 'password');
    });

    it('should log in successfully and navigate to /home via / if formkind is not "1"', async () => {
      const mockUser = { token: 'mockToken', user: { id: 1, name: 'Test User' } };
      mockLoginService.logIn.and.returnValue(Promise.resolve(mockUser));
      component.formkind = "2";
      fixture.detectChanges();

      await component.logIn(new Event('submit'), 'test@example.com', 'password');

      expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
    });

    it('should handle login failure', async () => {
      mockLoginService.logIn.and.returnValue(Promise.reject('Invalid user or password'));

      await component.logIn(new Event('submit'), 'test@example.com', 'password');

      expect(mockLoginService.logIn).toHaveBeenCalledWith('test@example.com', 'password');
      expect(component.isLoginFailed).toBeFalse();
    });

    it('should handle errors during login', async () => {
      mockLoginService.logIn.and.returnValue(Promise.reject('Error occurred'));

      await component.logIn(new Event('submit'), 'test@example.com', 'password');

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('logOut', () => {
    it('should log out and navigate to /', async () => {
      component.isLoggedIn = true;
      component.formkind = "2";
      fixture.detectChanges();

      await component.logOut();

      expect(component.isLoggedIn).toBeFalse();
      expect(mockTokenStorage.signOut).toHaveBeenCalled();
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/']);
    });
  });

  describe('open', () => {
    it('should open a modal and set closeResult on success', async () => {
      const mockModalRef = { result: Promise.resolve('Closed') };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockContent');

      mockModalRef.result.then(() => {
        expect(component.closeResult).toBe('Closed with: Closed');
      });
    });

    it('should set closeResult on dismiss', async () => {
      const mockModalRef = { result: Promise.reject(ModalDismissReasons.ESC) };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockContent');

      mockModalRef.result.catch(() => {
        expect(component.closeResult).toBe('Dismissed by pressing ESC');
      });
    });
  });

  describe('getDismissReason', () => {
    it('should return "by pressing ESC" for ESC reason', () => {
      const result = component['getDismissReason'](ModalDismissReasons.ESC);
      expect(result).toBe('by pressing ESC');
    });

    it('should return "by clicking on a backdrop" for BACKDROP_CLICK reason', () => {
      const result = component['getDismissReason'](ModalDismissReasons.BACKDROP_CLICK);
      expect(result).toBe('by clicking on a backdrop');
    });

    it('should return "with: <reason>" for other reasons', () => {
      const result = component['getDismissReason']('Other reason');
      expect(result).toBe('with: Other reason');
    });
  });
});