import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UnitsService } from './unit.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { LoginService } from './login.service';
import { Observable, of, throwError } from 'rxjs';

const mockUnit = { id: 1, name: 'Unit 1', lessons: [] };

describe('UnitsService', () => {
  let service: UnitsService;
  let httpMock: HttpTestingController;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockLoginService: jasmine.SpyObj<LoginService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockLoginService = jasmine.createSpyObj('LoginService', ['getUser']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UnitsService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: LoginService, useValue: mockLoginService },
      ],
    });

    service = TestBed.inject(UnitsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getUnits', () => {
    it('should fetch all units successfully', async () => {
      mockUtilsService.restService.and.returnValue(of([mockUnit]));

      const result = await service.getUnits();

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Unit/', { method: 'get' });
      expect(result).toEqual([mockUnit]);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('Error'));

      const result = await service.getUnits();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error');
      expect(result).toBeNull();
    });
  });

  describe('getUnit', () => {
    it('should fetch a single unit successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockUnit));

      const result = await service.getUnit(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Unit/', {
        queryString: 1,
        method: 'get',
      });
      expect(result).toEqual(mockUnit);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('Error'));

      const result = await service.getUnit(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error');
      expect(result).toBeNull();
    });
  });

  describe('numberOfCompletedLessons2', () => {
    it('should fetch the number of completed lessons successfully', async () => {
      const mockResponse = 5;
      mockUtilsService.restService.and.returnValue(of(mockResponse));

      const result = await service.numberOfCompletedLessons2(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Unit/', {
        queryString: '1/numberOfCompletedLessons',
        method: 'get',
      });
      expect(result).toEqual(mockResponse);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('Error'));

      const result = await service.numberOfCompletedLessons2(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error');
      expect(result).toBeNull();
    });
  });

  describe('addUnit', () => {
    it('should add a unit successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockUnit));

      const result = await service.addUnit(mockUnit);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Unit/', {
        method: 'post',
        params: mockUnit,
      });
      expect(result).toEqual(mockUnit);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('Error'));

      const result = await service.addUnit({ id: 1, name: 'New Unit' });

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error');
      expect(result).toBeNull();
    });
  });

  describe('deleteUnit', () => {
    it('should delete a unit successfully', async () => {
      const mockResponse = { success: true };
      mockUtilsService.restService.and.returnValue(of(mockResponse));

      const result = await service.deleteUnit(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Unit/', {
        queryString: 'delete/1',
        method: 'get',
        params: 1,
      });
      expect(result).toEqual(mockResponse);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('Error'));

      const result = await service.deleteUnit(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('Error');
      expect(result).toBeNull();
    });
  });
});