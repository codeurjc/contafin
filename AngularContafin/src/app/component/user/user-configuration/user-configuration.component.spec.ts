import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserConfigurationComponent } from './user-configuration.component';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../../../services/user.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
import { DomSanitizer } from '@angular/platform-browser';
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

describe('UserConfigurationComponent', () => {
  let component: UserConfigurationComponent;
  let fixture: ComponentFixture<UserConfigurationComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockUserService: jasmine.SpyObj<UserService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockModalService: jasmine.SpyObj<NgbModal>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockUserService = jasmine.createSpyObj('UserService', ['validation', 'deleteAccount']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser', 'getLoginInfo', 'saveToken', 'saveLoginInfo', 'signOut']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);

    await TestBed.configureTestingModule({
      declarations: [UserConfigurationComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: UserService, useValue: mockUserService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: NgbModal, useValue: mockModalService },
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(UserConfigurationComponent);
    component = fixture.componentInstance;
    component.userData = mockUser;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser and related properties', () => {
      const mockLoginInfo = { info: {}, isLogged: true, user: mockUser, isAdmin: true, imageView: 'mockImage'  };
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      mockTokenStorage.getLoginInfo.and.returnValue(mockLoginInfo);

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockTokenStorage.getUser).toHaveBeenCalled();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(component.loggedUser).toEqual(mockUser);
      expect(component.imageView).toBe('mockImage');
      expect(component.noAdmin).toBeFalse();
    });
  });

  describe('updateUser', () => {
    it('should update user successfully if old password is provided', async () => {
      mockUserService.validation.and.returnValue(Promise.resolve(mockUser));
      spyOn(component, 'getPasswords');
      spyOn(component, 'loadChanges');
      spyOn(component, 'ngOnInit');

      component.oldPassInput = { nativeElement: { value: 'oldPass' } } as any;
      component.newPassInput = { nativeElement: { value: 'newPass' } } as any;
      component.userData = mockUser;

      await component.updateUser();

      expect(mockUserService.validation).toHaveBeenCalled();
      expect(component.getPasswords).toHaveBeenCalled();
      expect(component.loadChanges).toHaveBeenCalledWith(mockUser);
      expect(component.ngOnInit).toHaveBeenCalled();
      expect(component.alertSuccess).toBeTrue();
    });

    it('should show an error if old password is not provided', async () => {
      component.oldPassInput = { nativeElement: { value: '' } } as any;

      await component.updateUser();

      expect(component.alertDanger).toBeTrue();
      expect(component.errorMessage).toBe('Introduce tu contraseña actual.');
    });

    it('should handle errors during user update', async () => {
      mockUserService.validation.and.returnValue(Promise.reject('Error occurred'));
      component.oldPassInput = { nativeElement: { value: 'oldPass' } } as any;
      component.newPassInput = { nativeElement: { value: 'newPass' } } as any;

      await component.updateUser();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('getPasswords', () => {
    it('should clear password inputs', () => {
      component.userData = mockUser;
      component.loggedUser = mockUser;
      component.oldPassInput = { nativeElement: { value: 'oldPass' } } as any;
      component.newPassInput = { nativeElement: { value: 'newPass' } } as any;

      component.getPasswords();

      expect(component.oldPassInput.nativeElement.value).toBe('');
      expect(component.newPassInput.nativeElement.value).toBe('');
    });
  });

  describe('loadChanges', () => {
    it('should save token and login info', () => {
      const mockUser = { token: 'mockToken', user: { id: 1, name: 'Test User' } };;

      component.loadChanges(mockUser);

      expect(mockTokenStorage.saveToken).toHaveBeenCalledWith('mockToken');
      expect(mockTokenStorage.saveLoginInfo).toHaveBeenCalledWith(mockUser);
    });
  });

  describe('selectFile', () => {
    it('should process and store the selected image', () => {
      const mockFile = new Blob(['mockImage'], { type: 'image/jpeg' });
      const mockEvent = { target: { files: [mockFile] } };
      const mockReader = jasmine.createSpyObj('FileReader', ['readAsDataURL', 'onloadend']);
      spyOn(window as any, 'FileReader').and.returnValue(mockReader);

      component.selectFile(mockEvent);

      expect(mockReader.readAsDataURL).toHaveBeenCalledWith(mockFile);
    });
  });

  describe('deleteAccount', () => {
    it('should delete the account and navigate to /', async () => {
      mockUserService.deleteAccount.and.returnValue(Promise.resolve());

      await component.deleteAccount();

      expect(mockUserService.deleteAccount).toHaveBeenCalledWith(component.loggedUser.id);
      expect(mockTokenStorage.signOut).toHaveBeenCalled();
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/']);
    });

    it('should handle errors during account deletion', async () => {
      mockUserService.deleteAccount.and.returnValue(Promise.reject('Error occurred'));

      await component.deleteAccount();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
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
      const result = component.getDismissReason(ModalDismissReasons.ESC);

      expect(result).toBe('by pressing ESC');
    });

    it('should return "by clicking on a backdrop" for BACKDROP_CLICK reason', () => {
      const result = component.getDismissReason(ModalDismissReasons.BACKDROP_CLICK);
      expect(result).toBe('by clicking on a backdrop');
    });

    it('should return "with: <reason>" for other reasons', () => {
      const result = component.getDismissReason('Other reason');
      expect(result).toBe('with: Other reason');
    });
  });
});