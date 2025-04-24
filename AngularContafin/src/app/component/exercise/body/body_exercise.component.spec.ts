import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyExerciseComponent } from './body_exercise.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('BodyExerciseComponent', () => {
  let component: BodyExerciseComponent;
  let fixture: ComponentFixture<BodyExerciseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BodyExerciseComponent],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(BodyExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize @Input properties correctly', () => {
    component.idunit = 1;
    component.idlesson = 2;
    component.idkind = 3;
    component.idexercise = 4;

    fixture.detectChanges();

    expect(component.idunit).toBe(1);
    expect(component.idlesson).toBe(2);
    expect(component.idkind).toBe(3);
    expect(component.idexercise).toBe(4);
  });
});