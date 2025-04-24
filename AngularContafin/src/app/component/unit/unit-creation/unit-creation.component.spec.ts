import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UnitCreationComponent } from './unit-creation.component';
import { Router } from '@angular/router';
import { UnitsService } from '../../../services/unit.service';
import { FormBuilder, ReactiveFormsModule, FormsModule  } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { ErrorService } from '../../../services/error.service';
import { of } from 'rxjs';

const mockUnit = { id: 1, name: 'Unit 1', lessons: [] };

describe('UnitCreationComponent', () => {
  let component: UnitCreationComponent;
  let fixture: ComponentFixture<UnitCreationComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockUnitsService: jasmine.SpyObj<UnitsService>;
  let mockFormBuilder: FormBuilder;
  let mockActivatedRoute: any;
  let mockSanitizer: jasmine.SpyObj<DomSanitizer>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockUnitsService = jasmine.createSpyObj('UnitsService', ['getUnit', 'addUnit', 'uploadImages']);
    mockFormBuilder = new FormBuilder();
    mockActivatedRoute = {
      snapshot: {
        params: {
          id: '1',
          resume: '1',
        },
      },
    };
    mockSanitizer = jasmine.createSpyObj('DomSanitizer', ['bypassSecurityTrustUrl']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [UnitCreationComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: UnitsService, useValue: mockUnitsService },
        { provide: FormBuilder, useValue: mockFormBuilder },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: DomSanitizer, useValue: mockSanitizer },
        { provide: ErrorService, useValue: mockErrorService },
      ],
      imports: [ReactiveFormsModule, FormsModule],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(UnitCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should initialize the form and load unit if unitId is not -1', async () => {
      const mockUnit = { id: 1, name: 'Test Unit', lessons: [] };
      mockUnitsService.getUnit.and.returnValue(Promise.resolve(mockUnit));

      await component.ngOnInit();

      expect(component.unitId).toBe(1);
      expect(component.mode).toBeTrue();
      expect(mockUnitsService.getUnit).toHaveBeenCalledWith(1);
      expect(component.unit).toEqual(mockUnit);
    });

    it('should initialize the form with a new lesson if unitId is -1', async () => {
      mockActivatedRoute.snapshot.params.id = '-1';

      await component.ngOnInit();

      expect(component.unitId).toBe(-1);
      expect(component.mode).toBeTrue();
      expect(component.lessons.length).toBe(1);
    });
  });

  describe('getUnit', () => {
    it('should fetch a unit successfully', async () => {
      mockUnitsService.getUnit.and.returnValue(Promise.resolve(mockUnit));

      await component.getUnit();

      expect(mockUnitsService.getUnit).toHaveBeenCalledWith(1);
      expect(component.unit).toEqual(mockUnit);
    });

    it('should handle errors when fetching a unit', async () => {
      mockUnitsService.getUnit.and.returnValue(Promise.reject('Error occurred'));

      await component.getUnit();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('addLesson', () => {
    it('should add a new lesson to the form', () => {
      component.addLesson(null);

      expect(component.lessons.length).toBe(1);
      expect(component.createUnitForm.contains('name_' + component.lessons[0].id)).toBeTrue();
    });
  });

  describe('addExercise', () => {
    it('should add a new exercise to the form', () => {
      const lessonId = 1;
      component.lessons.push({ id: lessonId, exerciseList: [] });

      const exerciseId = component.addExercise(1, null, lessonId);

      expect(component.exercises[exerciseId]).toBe(1);
      expect(component.createUnitForm.contains('statement_' + exerciseId)).toBeTrue();
      expect(component.lessons[0].exerciseList).toContain(exerciseId);

      const exerciseId2 = component.addExercise(2, null, lessonId);
      expect(component.exercises[exerciseId2]).toBe(2);
      expect(component.createUnitForm.contains('statement_' + exerciseId2)).toBeTrue();
      expect(component.lessons[0].exerciseList).toContain(exerciseId2);

      const exerciseId3 = component.addExercise(3, null, lessonId);
      expect(component.exercises[exerciseId3]).toBe(3);
      expect(component.createUnitForm.contains('statement_' + exerciseId3)).toBeTrue();
      expect(component.lessons[0].exerciseList).toContain(exerciseId3);

      const exerciseId5 = component.addExercise(5, null, lessonId);
      expect(component.exercises[exerciseId5]).toBe(5);
      expect(component.createUnitForm.contains('statement_' + exerciseId5)).toBeTrue();
      expect(component.lessons[0].exerciseList).toContain(exerciseId5);
    });
  });

  describe('imageShow', () => {
    it('should return a sanitized image URL', () => {
      const mockImage = 'mockImage';
      const mockUrl = 'data:image/jpeg;base64,' + mockImage;
      mockSanitizer.bypassSecurityTrustUrl.and.returnValue(mockUrl);

      const result = component.imageShow(mockImage);

      expect(mockSanitizer.bypassSecurityTrustUrl).toHaveBeenCalledWith(mockUrl);
      expect(result).toBe(mockUrl);
    });
  });

  describe('preSave', () => {
    it('should validate and prepare data for saving', () => {
      component.createUnitForm.addControl('name', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('name_1', mockFormBuilder.control('Test Unit'));
      component.lessons.push({ id: 1, exerciseList: [4] });
      component.createUnitForm.addControl('statement_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('texts_1_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('texts_2_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('texts_3_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('images_1_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('images_2_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('images_3_4', mockFormBuilder.control('Test Unit'));
      component.createUnitForm.addControl('result_4', mockFormBuilder.control('Test Unit'));
      component.exercises[4] = 5;

      component.preSave();

      expect(component.alertDanger).toBeTrue();
    });

    it('should set alertDanger to true if validation fails', () => {
      component.createUnitForm.addControl('name', mockFormBuilder.control(''));
      component.lessons.push({ id: 1, exerciseList: [] });

      component.preSave();

      expect(component.alertDanger).toBeTrue();
    });
  });

  describe('addUnit', () => {
    it('should save a unit successfully', async () => {
      const mockUnit = { id: 1, name: 'Test Unit', lessons: [{ id: 1, exerciseList: [] }] };
      mockUnitsService.addUnit.and.returnValue(Promise.resolve(mockUnit));

      await component.addUnit(mockUnit);

      expect(mockUnitsService.addUnit).toHaveBeenCalledWith(mockUnit);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/Admin/Home']);
    });

    it('should handle errors when saving a unit', async () => {
      mockUnitsService.addUnit.and.returnValue(Promise.reject('Error occurred'));

      await component.addUnit({});

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('deleteExercise', () => {
    it('should delete an exercise from a lesson', () => {
      const lessonId = 1;
      const exerciseId = 2;
      component.lessons.push({ id: lessonId, exerciseList: [exerciseId] });

      component.deleteExercise(exerciseId, lessonId);

      expect(component.lessons[0].exerciseList).not.toContain(exerciseId);
    });
  });

  describe('deleteLesson', () => {
    it('should delete a lesson from the list', () => {
      const lesson = { id: 1, exerciseList: [] };
      component.lessons.push(lesson);

      component.deleteLesson(lesson);

      expect(component.lessons).not.toContain(lesson);
    });
  });
});