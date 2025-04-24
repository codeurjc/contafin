import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Exercise5Component } from './exercise5.component';
import { ExerciseService } from '../../../../../services/exercise.service';
import { ErrorService } from '../../../../../services/error.service';
import { Exercise } from '../../../../../Interfaces/Exercise/exercise.model';
import { NO_ERRORS_SCHEMA } from '@angular/core';

const mockExercise = {
  id: 1,
  kind: 1,
  statement: 'Test statement', 
  texts: ['Option 1', 'Option 2', 'Option 3'], 
  answer: { result: 'test' },
  image1: 'mockImage1',
  image2: 'mockImage2',
  image3: 'mockImage3',
};

describe('Exercise5Component', () => {
  let component: Exercise5Component;
  let fixture: ComponentFixture<Exercise5Component>;
  let mockExerciseService: jasmine.SpyObj<ExerciseService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockExerciseService = jasmine.createSpyObj('ExerciseService', ['getExercise', 'checkExercise']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [Exercise5Component],
      providers: [
        { provide: ExerciseService, useValue: mockExerciseService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
                  schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(Exercise5Component);
    component = fixture.componentInstance;
    component.exercise = mockExercise as Exercise;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should fetch exercise data and initialize properties', async () => {
      mockExerciseService.getExercise.and.returnValue(Promise.resolve(mockExercise));

      component.nElement = 2;
      component.nTotal = 5;

      await component.ngOnInit();

      expect(mockExerciseService.getExercise).toHaveBeenCalledWith(1);
      expect(component.statement).toBe('Test statement');
      expect(component.texts).toEqual(['Option 1', 'Option 2', 'Option 3']);
      expect(component.complete_bar).toBe(60); // (5 - 2) / 5 * 100
    });

    it('should handle errors when fetching exercise data', async () => {
      mockExerciseService.getExercise.and.returnValue(Promise.reject('Error occurred'));

      await component.ngOnInit();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('check', () => {
    it('should mark the answer as correct and update the UI', async () => {
      component.exercise = { id: 1 } as any;
      component.answer = 'uno';
      mockExerciseService.checkExercise.and.returnValue(Promise.resolve(true));

      await component.check();

      expect(mockExerciseService.checkExercise).toHaveBeenCalledWith(1, { result: 'uno' });
      expect(component.right).toBeTrue();
      expect(component.option1).toBe('exercise1Good');
      expect(component.press).toBeTrue();
    });

    it('should mark the answer as incorrect and update the UI', async () => {
      component.exercise = { id: 1 } as any;
      component.answer = 'dos';
      mockExerciseService.checkExercise.and.returnValue(Promise.resolve(false));

      await component.check();

      expect(mockExerciseService.checkExercise).toHaveBeenCalledWith(1, { result: 'dos' });
      expect(component.right).toBeFalse();
      expect(component.option2).toBe('exercise1Bad');
      expect(component.press).toBeTrue();
    });

    it('should handle errors when checking the answer', async () => {
      component.exercise = { id: 1 } as any;
      component.answer = 'tres';
      mockExerciseService.checkExercise.and.returnValue(Promise.reject('Error occurred'));

      await component.check();

      expect(mockExerciseService.checkExercise).toHaveBeenCalledWith(1, { result: 'tres' });
      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('nextExercise', () => {
    it('should emit the newExercise event and reset press', () => {
      spyOn(component.newExercise, 'next');
      component.right = true;
      component.press = true;

      component.nextExercise();

      expect(component.newExercise.next).toHaveBeenCalledWith(true);
      expect(component.press).toBeFalse();
    });
  });
});