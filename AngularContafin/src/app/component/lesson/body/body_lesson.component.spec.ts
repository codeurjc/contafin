import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyLessonComponent } from './body_lesson.component';
import { LessonsService } from '../../../services/lesson.service';
import { UnitsService } from '../../../services/unit.service';
import { Unit } from '../../../Interfaces/Unit/unit.model';
import { of } from 'rxjs';

describe('BodyLessonComponent', () => {
  let component: BodyLessonComponent;
  let fixture: ComponentFixture<BodyLessonComponent>;
  let mockLessonsService: jasmine.SpyObj<LessonsService>;
  let mockUnitsService: jasmine.SpyObj<UnitsService>;

  beforeEach(async () => {
    mockLessonsService = jasmine.createSpyObj('LessonsService', ['isCompleted2']);
    mockUnitsService = jasmine.createSpyObj('UnitsService', ['getUnit', 'numberOfCompletedLessons2']);

    await TestBed.configureTestingModule({
      declarations: [BodyLessonComponent],
      providers: [
        { provide: LessonsService, useValue: mockLessonsService },
        { provide: UnitsService, useValue: mockUnitsService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BodyLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should call getLessons on initialization', () => {
      spyOn(component, 'getLessons');
      component.ngOnInit();
      expect(component.getLessons).toHaveBeenCalled();
    });
  });

  describe('getLessons', () => {
    it('should fetch unit and lessons completed data successfully', async () => {
      const mockUnit: Unit = { id: 1, name: 'Unit 1' } as Unit;
      const mockLessonsCompleted = [true, false, true];

      mockUnitsService.getUnit.and.returnValue(Promise.resolve(mockUnit));
      mockUnitsService.numberOfCompletedLessons2.and.returnValue(Promise.resolve(2));
      mockLessonsService.isCompleted2.and.returnValue(Promise.resolve(mockLessonsCompleted));

      component.id = 1;
      await component.getLessons();

      expect(mockUnitsService.getUnit).toHaveBeenCalledWith(1);
      expect(component.unit).toEqual(mockUnit);
      expect(mockUnitsService.numberOfCompletedLessons2).toHaveBeenCalledWith(1);
      expect(component.nlesson).toBe(2);
      expect(mockLessonsService.isCompleted2).toHaveBeenCalledWith(1);
      expect(component.lessonsCompleted).toEqual(mockLessonsCompleted);
    });

    it('should handle errors when fetching unit data', async () => {
      mockUnitsService.getUnit.and.returnValue(Promise.reject('Error occurred'));
      spyOn(console, 'error');

      component.id = 1;
      await component.getLessons();

      expect(mockUnitsService.getUnit).toHaveBeenCalledWith(1);
      expect(console.error).toHaveBeenCalledWith('Error occurred');
    });
  });
});