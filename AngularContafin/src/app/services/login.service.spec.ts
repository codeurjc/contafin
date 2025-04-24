import { TestBed } from '@angular/core/testing';
import { LoginService } from './login.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SafeUrl } from '@angular/platform-browser';
import { User } from '../Interfaces/User/user.model';
import { Observable, of, throwError } from 'rxjs';

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

describe('LoginService', () => {
  let service: LoginService;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        LoginService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    service = TestBed.inject(LoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('login', () => {
    it('should log in successfully', async () => {
      const mockCredentials = { name: 'test@example.com', pass: 'password123' };
      const mockResponse = { token: 'test-token', user: mockUser };

      mockUtilsService.restService.and.returnValue(of(mockResponse));

      const result = await service.logIn('test@example.com', 'password123');

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/login', {
        params: mockCredentials,
        method: 'post',
      });
      expect(result).toEqual(mockResponse);
    });

    it('should handle errors during login', async () => {

      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.logIn('test@example.com', 'password123');

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
});