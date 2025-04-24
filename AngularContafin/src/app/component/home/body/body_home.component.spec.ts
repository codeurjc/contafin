import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyHomeComponent } from './body_home.component';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../../../services/unit.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { of } from 'rxjs';
import { LessonsService } from '../../../services/lesson.service';
import { User } from '../../../Interfaces/User/user.model';
import { SafeUrl } from '@angular/platform-browser';
import { ErrorService } from '../../../services/error.service';

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

     const mockUnit = { id: 1, name: 'Unit 1', lessons: [] };

describe('BodyHomeComponent', () => {
  let component: BodyHomeComponent;
  let fixture: ComponentFixture<BodyHomeComponent>;
  let mockModalService: jasmine.SpyObj<NgbModal>;
  let mockUnitsService: jasmine.SpyObj<UnitsService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockLessonsService: jasmine.SpyObj<LessonsService>;

  

  beforeEach(async () => {
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);
    mockUnitsService = jasmine.createSpyObj('UnitsService', ['getUnits', 'numberOfCompletedLessons2']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockLessonsService = jasmine.createSpyObj('LessonsService', ['UnitCompletedForHome']);


    await TestBed.configureTestingModule({
      declarations: [BodyHomeComponent],
      providers: [
        { provide: NgbModal, useValue: mockModalService },
        { provide: UnitsService, useValue: mockUnitsService },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: LessonsService, useValue: mockLessonsService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BodyHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize loggedUser and isLoggedUser if token exists', () => {
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);
      const spy = spyOn(component, 'getUnits');

      component.ngOnInit();

      expect(mockTokenStorage.getToken).toHaveBeenCalled();
      expect(mockTokenStorage.getUser).toHaveBeenCalled();
      expect(component.loggedUser).toEqual(mockUser);
      expect(component.isLoggedUser).toBeTrue();
      expect(spy).toHaveBeenCalledTimes(1);
    });

    it('should not initialize loggedUser if token does not exist', () => {
      mockTokenStorage.getToken.and.returnValue(null);
      const spy = spyOn(component, 'getUnits');

      component.ngOnInit();

      expect(component.loggedUser).toBeUndefined();
      expect(component.isLoggedUser).toBeFalse();
      expect(spy).toHaveBeenCalledTimes(1);
    });

  });

  describe('getUnits', () => {
    it('should fetch units and calculate lessons completed', async () => {
      const mockUnits = [
        mockUnit,
        mockUnit
      ];
      const mockLessonData = { ncompleted: 5, unitId: 1 };

      mockUnitsService.getUnits.and.returnValue(Promise.resolve(mockUnits));
      mockLessonsService.UnitCompletedForHome.and.returnValue(Promise.resolve(mockLessonData));

      await component.getUnits();

      expect(mockUnitsService.getUnits).toHaveBeenCalled();
      expect(component.units).toEqual(mockUnits);
      expect(component.n).toBe(5);
      expect(component.idUnitlast).toBe(1);
      expect(mockLessonsService.UnitCompletedForHome).toHaveBeenCalled();
    });

    it('should handle errors when fetching units', async () => {
      mockUnitsService.getUnits.and.returnValue(Promise.reject('Error occurred'));
      mockLessonsService.UnitCompletedForHome.and.returnValue(Promise.reject('Error occurred'));

      await component.getUnits();

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