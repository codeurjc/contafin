import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SafeUrl } from '@angular/platform-browser';
import { BodyCompleteLessonComponent } from './body_completeLesson.component';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../../../services/user.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
import { of, throwError } from 'rxjs';
import { User } from '../../../Interfaces/User/user.model';
import { NO_ERRORS_SCHEMA } from '@angular/core';

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

describe('BodyCompleteLessonComponent', () => {
  let component: BodyCompleteLessonComponent;
  let fixture: ComponentFixture<BodyCompleteLessonComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockUserService: jasmine.SpyObj<UserService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockModalService: jasmine.SpyObj<NgbModal>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate', 'resetConfig']);
    mockUserService = jasmine.createSpyObj('UserService', ['completeLesson']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser', 'saveUser']);
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [BodyCompleteLessonComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: UserService, useValue: mockUserService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: NgbModal, useValue: mockModalService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(BodyCompleteLessonComponent);
    component = fixture.componentInstance;
    component.idLesson = 1;
    component.points = 100;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser and pointsLogged', () => {
      const mockUser: User = { id: 1, name: 'Test User' } as User;
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);

      component.ngOnInit();

      expect(component.loggedUser).toEqual(mockUser);
      expect(component.pointsLogged).toBe('+100 Puntos');
    });

    it('should set pointsLogged to an empty string if points is null', () => {
      component.points = null;
      mockTokenStorage.getToken.and.returnValue('mockToken');

      component.ngOnInit();

      expect(component.pointsLogged).toBe('');
    });
  });

  describe('completeLesson', () => {
    it('should complete the lesson and navigate to /ContinueLesson', async () => {
      component.loggedUser = mockUser;
      mockUserService.completeLesson.and.returnValue(Promise.resolve(mockUser));
      const spy = spyOn(component, 'resetConfiguration');
  
      // executing render
      fixture.detectChanges();
    
      // assertions
      

      await component.completeLesson();

      expect(mockUserService.completeLesson).toHaveBeenCalledWith(1, 100);
      expect(mockTokenStorage.saveUser).toHaveBeenCalledWith(mockUser);
      expect(spy).toHaveBeenCalledTimes(1);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/ContinueLesson']);
    });

    it('should navigate to /home if loggedUser is null', async () => {
      component.loggedUser = null;

      await component.completeLesson();

      expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
    });

    it('should handle errors when completing the lesson', async () => {
      component.loggedUser = { id: 1, name: 'Test User' } as User;
      mockUserService.completeLesson.and.returnValue(Promise.reject('Error occurred'));

      await component.completeLesson();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('open', () => {
    it('should open a modal and set closeResult on success', () => {
      const mockModalRef = { result: Promise.resolve('Closed') };
      mockModalService.open.and.returnValue(mockModalRef as any);

      component.open('mockContent');

      mockModalRef.result.then(() => {
        expect(component.closeResult).toBe('Closed with: Closed');
      });
    });

    it('should set closeResult on dismiss', () => {
      const mockModalRef = { result: Promise.reject(ModalDismissReasons.ESC) };
      mockModalService.open.and.returnValue(mockModalRef as any);

      component.open('mockContent');

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

  describe('resetConfiguration', () => {
    it('should reset the router configuration', () => {
      const mockConfig = [{ path: 'test' }];
      mockRouter.config = mockConfig;

      component.resetConfiguration();

      expect(mockRouter.resetConfig).toHaveBeenCalledWith(mockConfig);
    });
  });
});