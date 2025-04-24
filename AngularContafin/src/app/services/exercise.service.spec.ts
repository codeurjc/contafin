import { TestBed } from '@angular/core/testing';
import { ExerciseService } from './exercise.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable, of, throwError } from 'rxjs';

const mockExercise = {
  id: 1,
  kind: 1,
  statement: 'Test statement', 
  texts: ['text1', 'text2'], 
  answer: { result: 'test' }};

describe('ExerciseService', () => {
  let service: ExerciseService;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ExerciseService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    service = TestBed.inject(ExerciseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  describe('getExercise', () => {
    it('should fetch exercise data successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockExercise));

      const result = await service.getExercise(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Exercise/', {
        queryString: 1,
        method: 'get',
      });
      expect(result).toEqual(mockExercise);
    });

    it('should handle errors when fetching exercise data', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.getExercise(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('checkExercise', () => {
    it('should mark an exercise as completed successfully', async () => {;
      mockUtilsService.restService.and.returnValue(of(true));

      const result = await service.checkExercise(1, { result: 'test' });

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Exercise/', {
        queryString: 1+'/Solution',
        params: { result: 'test' },
        method: 'post',
      });
      expect(result).toEqual(true);
    });

    it('should handle errors when marking an exercise as completed', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.checkExercise(1, { result: 'test' });

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
});