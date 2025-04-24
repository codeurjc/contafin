import { TestBed } from '@angular/core/testing';
import { LessonsService } from './lesson.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable, of, throwError } from 'rxjs';

const mockLesson = {
  id : 1,
  name: 'Lesson 1',
  exercises: null};

describe('LessonService', () => {
  let service: LessonsService;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        LessonsService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    service = TestBed.inject(LessonsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getLesson', () => {
    it('should fetch lesson data successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockLesson));

      const result = await service.getLesson(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Lesson/', {
        queryString: 1,
        method: 'get',
      });
      expect(result).toEqual(mockLesson);
    });

    it('should handle errors when fetching lesson data', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.getLesson(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('isCompleted2', () => {
    it('should mark a lesson as completed successfully', async () => {
      const mockResponse = [true];
      mockUtilsService.restService.and.returnValue(of(mockResponse));

      const result = await service.isCompleted2(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Lesson/', {
        queryString: '1/AllCompleted',
        method: 'get',
      });
      expect(result).toEqual(mockResponse);
    });

    it('should handle errors when marking a lesson as completed', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.isCompleted2(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
});