import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UnitListComponent } from './unit-list.component';
import { UnitsService } from '../../../services/unit.service';
import { ErrorService } from '../../../services/error.service';
import { of } from 'rxjs';
import { Unit } from '../../../Interfaces/Unit/unit.model';


const mockUnit = { id: 1, name: 'Unit 1', lessons: [] };

describe('UnitListComponent', () => {
  let component: UnitListComponent;
  let fixture: ComponentFixture<UnitListComponent>;
  let mockUnitsService: jasmine.SpyObj<UnitsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockUnitsService = jasmine.createSpyObj('UnitsService', ['getUnits', 'deleteUnit']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [UnitListComponent],
      providers: [
        { provide: UnitsService, useValue: mockUnitsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(UnitListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should call getUnits on initialization', () => {
      spyOn(component, 'getUnits');
      component.ngOnInit();
      expect(component.getUnits).toHaveBeenCalled();
    });
  });

  describe('getUnits', () => {
    it('should fetch units and update the units property', async () => {
      const mockUnits: Unit[] = [
        mockUnit,
        mockUnit
      ];
      mockUnitsService.getUnits.and.returnValue(Promise.resolve(mockUnits));

      await component.getUnits();

      expect(mockUnitsService.getUnits).toHaveBeenCalled();
      expect(component.units).toEqual(mockUnits);
      expect(component.spinner).toBeFalse();
    });

    it('should handle errors and call handleError', async () => {
      mockUnitsService.getUnits.and.returnValue(Promise.reject('Error occurred'));

      await component.getUnits();

      expect(mockUnitsService.getUnits).toHaveBeenCalled();
      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
      expect(component.spinner).toBeFalse();
    });
  });

  describe('deleteUnit', () => {
    it('should delete a unit and refresh the units list', async () => {
      const mockUnitId = 1;
      spyOn(component, 'getUnits');
      mockUnitsService.deleteUnit.and.returnValue(Promise.resolve());

      await component.deleteUnit(mockUnitId);

      expect(mockUnitsService.deleteUnit).toHaveBeenCalledWith(mockUnitId);
      expect(component.getUnits).toHaveBeenCalled();
      expect(component.spinner).toBeFalse();
    });

    it('should handle errors during deletion and call handleError', async () => {
      const mockUnitId = 1;
      mockUnitsService.deleteUnit.and.returnValue(Promise.reject('Error occurred'));

      await component.deleteUnit(mockUnitId);

      expect(mockUnitsService.deleteUnit).toHaveBeenCalledWith(mockUnitId);
      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error occurred');
      expect(component.spinner).toBeFalse();
    });
  });
});