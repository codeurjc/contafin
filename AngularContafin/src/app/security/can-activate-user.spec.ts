import { TestBed } from '@angular/core/testing';
import { CanActivateUser } from './can-activate-user';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';
import { ErrorService } from '../services/error.service';

describe('CanActivateUser', () => {
  let canActivateUser: CanActivateUser;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getLoginInfo']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['setMessage']);

    TestBed.configureTestingModule({
      providers: [
        CanActivateUser,
        { provide: Router, useValue: mockRouter },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    canActivateUser = TestBed.inject(CanActivateUser);
  });

  it('should create', () => {
    expect(canActivateUser).toBeTruthy();
  });

  describe('canActivate', () => {
    it('should return true if the user is logged in', () => {
      mockTokenStorage.getLoginInfo.and.returnValue({ isLogged: true });

      const result = canActivateUser.canActivate();

      expect(result).toBeTrue();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(mockRouter.navigate).not.toHaveBeenCalled();
      expect(mockErrorService.setMessage).not.toHaveBeenCalled();
    });

    it('should redirect to /Error and return false if the user is not logged in', () => {
      mockTokenStorage.getLoginInfo.and.returnValue({ isLogged: false });

      const result = canActivateUser.canActivate();

      expect(result).toBeFalse();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(mockErrorService.setMessage).toHaveBeenCalledWith('No tienes permiso para acceder a esta página');
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/Error']);
    });
  });
});