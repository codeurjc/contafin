import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { TokenStorageService } from './token-storage.service';
import { Observable, of, throwError } from 'rxjs';
import { User } from '../Interfaces/User/user.model';
import { SafeUrl } from '@angular/platform-browser';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockSanitizer: jasmine.SpyObj<any>; // Adjust type as necessary
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  const mockUser: User = {
     id : 1,
     name: 'John Doe',
     email: 'admin@admin.es',
     passwordHash: 'hashedpassword',
     level: 1,
     points: 100,
     streak: 5,
     fluency: 80,
     dailyGoal: 10,
     lastConnection: '2023-10-01T12:00:00Z',
     lastUnit: 1,
     lastLesson: 1,
     progress: [1, 2, 3],
     remainingGoals: 5,
     exp: 1000,
     needexp: 500,
     image: 'https://example.com/image.jpg' as SafeUrl, // Mocked URL
     // roles: ['ROLE_USER', 'ROLE_ADMIN'],
     roles: ['ROLE_USER']};

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getLoginInfo', 'getUser']);
    mockSanitizer = jasmine.createSpyObj('DomSanitizer', ['bypassSecurityTrustUrl']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UserService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getUser', () => {
    it('should fetch user data successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockUser));

      const result = await service.getUser(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/User', {
        queryString: '/1',
        method: 'get',
      });
      expect(result).toEqual(mockUser);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.getUser(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('deleteAccount', () => {
    it('should delete user account successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(true));

      const result = await service.deleteAccount(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/User', {
        queryString: '/1',
        method: 'delete',
      });
      expect(result).toEqual(true);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.deleteAccount(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('validation', () => {
    it('should validate user data successfully', async () => {
      const mockResponse = { valid: true };
      mockUtilsService.restService.and.returnValue(of(mockResponse));

      const result = await service.validation(1, { param: 'value' });

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/User', {
        queryString: '/Validation/1',
        method: 'post',
        params: { param: 'value' },
      });
      expect(result).toEqual(mockResponse);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.validation(1, { param: 'value' });

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('getProgress', () => {
    it('should fetch user progress successfully', async () => {
      const mockProgress = { progress: 50 };
      mockUtilsService.restService.and.returnValue(of(mockProgress));

      const result = await service.getProgress(1);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/User/', {
        queryString: '1/Progress',
        method: 'get',
      });
      expect(result).toEqual(mockProgress);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.getProgress(1);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('completeLesson', () => {
    it('should complete lesson successfully', async () => {
      mockUtilsService.restService.and.returnValue(of(mockUser));

      const result = await service.completeLesson(1, 10);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/User/', {
        queryString: '1/Completed/10',
        method: 'post',
      });
      expect(result).toEqual(mockUser);
    });

    it('should handle errors', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.completeLesson(1, 10);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
  // Similar tests can be written for other methods like validation, updateUser, setGoal, etc.
});