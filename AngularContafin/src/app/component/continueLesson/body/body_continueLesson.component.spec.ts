/*import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyContinueLessonComponent } from './body_continueLesson.component';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorageService } from '../../../services/token-storage.service';
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

describe('BodyContinueLessonComponent', () => {
  let component: BodyContinueLessonComponent;
  let fixture: ComponentFixture<BodyContinueLessonComponent>;
  let mockModalService: jasmine.SpyObj<NgbModal>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;

  beforeEach(async () => {
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser']);

    await TestBed.configureTestingModule({
      declarations: [BodyContinueLessonComponent],
      providers: [
        { provide: NgbModal, useValue: mockModalService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BodyContinueLessonComponent);
    component = fixture.componentInstance;
    mockUser.fluency = 80;
    component.loggedUser = mockUser; // Initialize loggedUser with mock data
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser and log token and user data', () => {
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockTokenStorage.getUser).toHaveBeenCalled();
      expect(component.loggedUser).toEqual(mockUser);
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
});*/