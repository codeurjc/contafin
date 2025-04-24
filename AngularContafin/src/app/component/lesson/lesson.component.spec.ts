import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LessonComponent } from './lesson.component';
import { ActivatedRoute } from '@angular/router';
import { invalid } from '@angular/compiler/src/render3/view/util';

describe('LessonComponent', () => {
  let component: LessonComponent;
  let fixture: ComponentFixture<LessonComponent>;
  let mockActivatedRoute: any;

  beforeEach(async () => {
    mockActivatedRoute = {
      snapshot: {
        params: {
          id: 123,
        },
      },
    };

    await TestBed.configureTestingModule({
      declarations: [LessonComponent],
      providers: [
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(LessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize idhome from route params', () => {
    expect(component.idhome).toBe(123);
  });

  it('should handle invalid route params gracefully', () => {
    mockActivatedRoute.snapshot.params.id = 'invalid';
    const invalidFixture = TestBed.createComponent(LessonComponent);
    const invalidComponent = invalidFixture.componentInstance;

    expect(invalidComponent.idhome).toBe(invalidFixture.componentInstance.idhome);
  });
});