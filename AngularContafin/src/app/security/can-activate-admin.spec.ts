import { TestBed } from '@angular/core/testing';
import { CanActivateAdmin } from './can-activate-admin';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';
import { ErrorService } from '../services/error.service';

describe('CanActivateAdmin', () => {
  let canActivateAdmin: CanActivateAdmin;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getLoginInfo']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['setMessage']);

    TestBed.configureTestingModule({
      providers: [
        CanActivateAdmin,
        { provide: Router, useValue: mockRouter },
        { provide: TokenStorageService, useValue: mockTokenStorage },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    canActivateAdmin = TestBed.inject(CanActivateAdmin);
  });

  it('should create', () => {
    expect(canActivateAdmin).toBeTruthy();
  });

  describe('canActivate', () => {
    it('should return true if the user is an admin', () => {
      mockTokenStorage.getLoginInfo.and.returnValue({ isAdmin: true });

      const result = canActivateAdmin.canActivate();

      expect(result).toBeTrue();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(mockRouter.navigate).not.toHaveBeenCalled();
      expect(mockErrorService.setMessage).not.toHaveBeenCalled();
    });

    it('should redirect to /Error and return false if the user is not an admin', () => {
      mockTokenStorage.getLoginInfo.and.returnValue({ isAdmin: false });

      const result = canActivateAdmin.canActivate();

      expect(result).toBeFalse();
      expect(mockTokenStorage.getLoginInfo).toHaveBeenCalled();
      expect(mockErrorService.setMessage).toHaveBeenCalledWith('No tienes permiso para acceder a esta página');
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/Error']);
    });
  });
});