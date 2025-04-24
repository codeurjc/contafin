import { TestBed } from '@angular/core/testing';
import { SignUpService } from './sign-up.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { User } from '../Interfaces/User/user.model';
import { SafeUrl } from '@angular/platform-browser';
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

describe('SignUpService', () => {
  let service: SignUpService;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    TestBed.configureTestingModule({
      providers: [
        SignUpService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    service = TestBed.inject(SignUpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('signup', () => {
    it('should sign up a user successfully', async () => {
      const mockUserData = { name: 'John Doe', email: 'john@example.com', pass: 'password123' };

      mockUtilsService.restService.and.returnValue(of(mockUser));

      const result = await service.signup(mockUserData);

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/signup', {
        params: mockUserData,
        method: 'post'
      });
      expect(result).toEqual(mockUser);
    });

    it('should handle errors during signup', async () => {
      const mockUserData = { name: 'John Doe', email: 'john@example.com', pass: 'password123' };

      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.signup(mockUserData);

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
});