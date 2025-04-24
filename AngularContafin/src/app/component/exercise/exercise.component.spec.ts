import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Answer } from '../../Interfaces/Answer/answer.model';
import { Exercise } from '../../Interfaces/Exercise/exercise.model'; // Adjust the path as needed
import { ExerciseComponent } from './exercise.component';
import { Router, ActivatedRoute } from '@angular/router';
import { LessonsService } from '../../services/lesson.service';
import { ErrorService } from '../../services/error.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { of, throwError } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

const mockExercise: Exercise = {
        id: 1,
        kind: 1,
        statement: 'Test statement',
        texts: ['Option 1', 'Option 2', 'Option 3'],
        image1: 'image1Base64',
        image2: 'image2Base64',
        image3: 'image3Base64',
        answer: { result: 'test' } as Answer,
      } as Exercise;

describe('ExerciseComponent', () => {
  let component: ExerciseComponent;
  let fixture: ComponentFixture<ExerciseComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockActivatedRoute: any;
  let mockLessonsService: jasmine.SpyObj<LessonsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockActivatedRoute = {
      snapshot: {
        params: {
          id: '1',
          idlesson: '2',
        },
      },
    };
    mockLessonsService = jasmine.createSpyObj('LessonsService', ['getLesson']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken']);

    await TestBed.configureTestingModule({
      declarations: [ExerciseComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: LessonsService, useValue: mockLessonsService },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(ExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should call getExercises on initialization', () => {
      spyOn(component, 'getExercises');
      component.ngOnInit();
      expect(component.getExercises).toHaveBeenCalled();
    });
  });

  describe('getExercises', () => {
    it('should fetch exercises and update idExercises and kindExercises', async () => {
      const mockLesson = {
        id : 1,
        name: 'Lesson 1',
        exercises: [
          mockExercise,
          mockExercise,
        ],
      };

    
      mockLessonsService.getLesson.and.returnValue(Promise.resolve(mockLesson));

      await component.getExercises();

      expect(mockLessonsService.getLesson).toHaveBeenCalledWith(2);
      expect(component.idExercises).toEqual(mockLesson.exercises);
      expect(component.kindExercises).toEqual([1, 1]);
      expect(component.nElement).toBe(2);
      expect(component.nTotal).toBe(2);
    });

    it('should handle errors when fetching exercises', async () => {
      mockLessonsService.getLesson.and.returnValue(Promise.reject('Error occurred'));

      await component.getExercises();

      expect(mockLessonsService.getLesson).toHaveBeenCalledWith(2);
      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
    });
  });

  describe('newExercisechange', () => {
    it('should update idExercises, kindExercises, and points when newExercise is true', () => {
      component.idExercises = [{ id: 1, kind: 1 }, { id: 2, kind: 2 }];
      component.kindExercises = [1, 2];
      component.points = 0;

      component.newExercisechange(true);

      expect(component.idExercises).toEqual([{ id: 2, kind: 2 }]);
      expect(component.kindExercises).toEqual([2]);
      expect(component.nElement).toBe(1);
    });

    it('should update idExercises, kindExercises, and points when newExercise is false', () => {
      component.idExercises = [{ id: 1, kind: 1 }, { id: 2, kind: 2 }];
      component.kindExercises = [1, 2];
      component.points = 0;

      component.newExercisechange(false);

      expect(component.idExercises).toEqual([{ id: 2, kind: 2 }, { id: 1, kind: 1 }]);
      expect(component.kindExercises).toEqual([2, 1]);
      expect(component.nElement).toBe(2);
    });
  });

  describe('pulsar', () => {
    it('should log debug information to the console', () => {
      spyOn(console, 'log');
      component.idUnit = 1;
      component.idLesson = 2;
      component.idExercise = 3;
      component.idExercises = [{ id: 1, kind: 1 }];
      component.kindExercises = [1];

      component.pulsar();

      expect(console.log).toHaveBeenCalledWith(1);
      expect(console.log).toHaveBeenCalledWith(2);
      expect(console.log).toHaveBeenCalledWith(3);
      expect(console.log).toHaveBeenCalledWith([{ id: 1, kind: 1 }]);
      expect(console.log).toHaveBeenCalledWith([1]);
    });
  });
});