import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Exercise3Component } from './exercise3.component';
import { ExerciseService } from '../../../../../services/exercise.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ErrorService } from '../../../../../services/error.service';
import { FormBuilder, FormControl } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('Exercise3Component', () => {
  let component: Exercise3Component;
  let fixture: ComponentFixture<Exercise3Component>;
  let mockExerciseService: jasmine.SpyObj<ExerciseService>;
  let mockSanitizer: jasmine.SpyObj<DomSanitizer>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  const mockExercise = {
    id: 1,
    statement: 'Test statement',
    texts: ['Option 1:Option 2', 'Option 3:Option 4'],
    answer: { result: 'Option 1:Option 3/Option 2:Option 4' },
    kind : 3,
  };

  beforeEach(async () => {
    mockExerciseService = jasmine.createSpyObj('ExerciseService', ['getExercise']);
    mockSanitizer = jasmine.createSpyObj('DomSanitizer', ['bypassSecurityTrustUrl']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [Exercise3Component],
      providers: [
        { provide: ExerciseService, useValue: mockExerciseService },
        { provide: DomSanitizer, useValue: mockSanitizer },
        { provide: ErrorService, useValue: mockErrorService },
        FormBuilder,
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(Exercise3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
    const element = document.createElement('input');
    element.setAttribute('id', 'Option 1l');
    const element2 = document.createElement('input');
    element2.setAttribute('id', 'Option 3r');
    const element3 = document.createElement('input');
    element3.setAttribute('id', 'Option 4r');
    document.body.appendChild(element);
    document.body.appendChild(element2);
    document.body.appendChild(element3);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should fetch exercise data and initialize properties', async () => {
      mockExerciseService.getExercise.and.returnValue(Promise.resolve(mockExercise));

      component.exercise = mockExercise;
      component.nElement = 2;
      component.nTotal = 5;

      await component.ngOnInit();

      expect(mockExerciseService.getExercise).toHaveBeenCalledWith(1);
      expect(component.statement).toBe('Test statement');
      expect(component.columnLeftText).toBe('Option 1:Option 2');
      expect(component.columnRightText).toBe('Option 3:Option 4');
      expect(component.complete_bar).toBe(60); // (5 - 2) / 5 * 100
    });

    it('should handle errors when fetching exercise data', async () => {
      mockExerciseService.getExercise.and.returnValue(Promise.reject('Error occurred'));
      component.exercise = mockExercise;
      
      await component.ngOnInit();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('initPairs', () => {
    it('should initialize pairs and shuffle options', () => {
      component.columnLeftText = 'Option 1:Option 2';
      component.columnRightText = 'Option 3:Option 4';
      component.exercise = mockExercise;

      component.initPairs();

      expect(component.leftoptions).toContain('Option 1');
      expect(component.leftoptions).toContain('Option 2');
      expect(component.rightoptions).toContain('Option 3');
      expect(component.rightoptions).toContain('Option 4');
      expect(component.pairsAnswer['Option 1']).toBe('Option 3');
      expect(component.pairsAnswer['Option 2']).toBe('Option 4');
    });
  });

  

  describe('shuffleArray', () => {
    it('should shuffle the array', () => {
      const array = [1, 2, 3, 4, 5];
      const shuffledArray = component.shuffleArray(array);

      expect(shuffledArray).not.toEqual([1, 2, 3, 4, 5]); // The order should change
      expect(shuffledArray.sort()).toEqual([1, 2, 3, 4, 5]); // The content should remain the same
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