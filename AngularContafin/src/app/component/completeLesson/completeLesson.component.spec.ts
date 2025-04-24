import { TestBed, ComponentFixture } from '@angular/core/testing';
import { CompleteLessonComponent } from './completeLesson.component';
import { Router, ActivatedRoute } from '@angular/router';
import { ExerciseService } from '../../services/exercise.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('CompleteLessonComponent', () => {
  let component: CompleteLessonComponent;
  let fixture: ComponentFixture<CompleteLessonComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockActivatedRoute: any;
  let mockExerciseService: jasmine.SpyObj<ExerciseService>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockActivatedRoute = {
      snapshot: {
        params: {
          id: '1',
          idlesson: '2',
          points: '100',
        },
      },
    };
    mockExerciseService = jasmine.createSpyObj('ExerciseService', ['someMethod']);

    await TestBed.configureTestingModule({
      declarations: [CompleteLessonComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: ExerciseService, useValue: mockExerciseService },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(CompleteLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize idUnit, idLesson, and points from route params', () => {
    expect(component.idUnit).toBe(1);
    expect(component.idLesson).toBe(2);
    expect(component.points).toBe(100);
  });

  it('should handle invalid route params gracefully', () => {
    mockActivatedRoute.snapshot.params = {
      id: 'invalid',
      idlesson: 'invalid',
      points: 'invalid',
    };

    const invalidFixture = TestBed.createComponent(CompleteLessonComponent);
    const invalidComponent = invalidFixture.componentInstance;

    expect(invalidComponent.idUnit).toBeNaN();
    expect(invalidComponent.idLesson).toBeNaN();
    expect(invalidComponent.points).toBeNaN();
  });
});