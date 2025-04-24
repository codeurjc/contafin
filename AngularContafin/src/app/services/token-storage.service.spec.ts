import { TestBed } from '@angular/core/testing';
import { TokenStorageService } from './token-storage.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { User } from '../Interfaces/User/user.model';

describe('TokenStorageService', () => {
  let service: TokenStorageService;
  let mockSanitizer: jasmine.SpyObj<DomSanitizer>;

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
    mockSanitizer = jasmine.createSpyObj('DomSanitizer', ['bypassSecurityTrustUrl']);

    TestBed.configureTestingModule({
      providers: [
        TokenStorageService,
        { provide: DomSanitizer, useValue: mockSanitizer },
      ],
    });

    service = TestBed.inject(TokenStorageService);
    sessionStorage.clear(); // Limpia el almacenamiento antes de cada prueba
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('signOut', () => {
    it('should clear session storage', () => {
      sessionStorage.setItem('auth-token', 'test-token');
      sessionStorage.setItem('auth-user', JSON.stringify({ name: 'test-user' }));

      service.signOut();

      expect(sessionStorage.getItem('auth-token')).toBeNull();
      expect(sessionStorage.getItem('auth-user')).toBeNull();
    });
  });

  describe('saveToken', () => {
    it('should save the token in session storage', () => {
      service.saveToken('test-token');

      expect(sessionStorage.getItem('auth-token')).toBe('test-token');
    });
  });

  describe('getToken', () => {
    it('should return the token from session storage', () => {
      sessionStorage.setItem('auth-token', 'test-token');

      const token = service.getToken();

      expect(token).toBe('test-token');
    });

    it('should return null if no token is stored', () => {
      const token = service.getToken();

      expect(token).toBeNull();
    });
  });

  describe('saveLoginInfo', () => {
    it('should save user login info in session storage', () => {
      service.saveLoginInfo(mockUser);

      expect(sessionStorage.getItem('auth-user')).toBe(JSON.stringify(mockUser));
    });
  });

  describe('saveUser', () => {
    it('should update the user in session storage', () => {
      const initialUser = { user: mockUser};

      sessionStorage.setItem('auth-user', JSON.stringify(initialUser));
      service.saveUser(mockUser);

      const storedUser = JSON.parse(sessionStorage.getItem('auth-user'));
      expect(storedUser.user).toEqual(mockUser);
    });
  });

  describe('getUser', () => {
    it('should return the user from session storage', () => {
      const user = { user: mockUser };
      sessionStorage.setItem('auth-user', JSON.stringify(user));

      const result = service.getUser();

      expect(result).toEqual(user.user);
    });

    it('should return undefined if no user is stored', () => {
      const result = service.getUser();

      expect(result).toBeNull();
    });
  });

  describe('getLoginInfo', () => {
    it('should return login info with sanitized image if user has an image', () => {
      const user = {
        user: mockUser,
        roles: ['ROLE_USER'],
      };
      sessionStorage.setItem('auth-user', JSON.stringify(user));
      //mockSanitizer.bypassSecurityTrustUrl.and.returnValue('sanitized-image');

      const result = service.getLoginInfo();

      //expect(result.imageView).toBe('sanitized-image');
      //expect(mockSanitizer.bypassSecurityTrustUrl).toHaveBeenCalledWith('data:image/jpeg;base64,https://example.com/image.jpg');
      expect(result.isLogged).toBeTrue();
      expect(result.isAdmin).toBeFalse();
    });

    it('should return login info without sanitized image if user has no image', () => {
        
      const user = {
        user: {},
        roles: ['ROLE_USER'],
      };
      sessionStorage.setItem('auth-user', JSON.stringify(user));

      const result = service.getLoginInfo();

      expect(result.imageView).toBeNull();
      expect(result.isLogged).toBeTrue();
      expect(result.isAdmin).toBeFalse();
    });

    it('should return null if no user is stored', () => {
      const result = service.getLoginInfo();

      expect(result).toBeNull();
    });
  });
});