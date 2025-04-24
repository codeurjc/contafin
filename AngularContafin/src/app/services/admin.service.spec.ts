import { TestBed } from '@angular/core/testing';
import { AdminService } from './admin.service';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Observable, of, throwError } from 'rxjs';
import { User } from '../Interfaces/User/user.model';
import { SafeUrl } from '@angular/platform-browser';

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

describe('AdminService', () => {
  let service: AdminService;
  let mockUtilsService: jasmine.SpyObj<UtilsService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(() => {
    mockUtilsService = jasmine.createSpyObj('UtilsService', ['restService']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AdminService,
        { provide: UtilsService, useValue: mockUtilsService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
    });

    service = TestBed.inject(AdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getUserData', () => {
    it('should fetch user data successfully', async () => {
      const mockUsers = [mockUser,mockUser];
      mockUtilsService.restService.and.returnValue(of(mockUsers));

      const result = await service.getUserData();

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Admin/UserData', {
        method: 'get',
      });
      expect(result).toEqual(mockUsers);
    });

    it('should handle errors when fetching user data', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.getUserData();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });

  describe('exporData', () => {
    it('should export data successfully and trigger file download', async () => {
      const mockData = new Blob(['mockExcelData'], { type: 'application/vnd.ms-excel' });
      spyOn(window, 'Blob').and.returnValue(mockData);
      spyOn(document, 'createElement').and.callFake(() => {
        return {
          href: '',
          download: '',
          click: jasmine.createSpy('click'),
          setAttribute: jasmine.createSpy('setAttribute'),
          remove: jasmine.createSpy('remove'),
        } as unknown as HTMLAnchorElement;
      });

      mockUtilsService.restService.and.returnValue(of(mockData));

      const result = await service.exporData();

      expect(mockUtilsService.restService).toHaveBeenCalledWith('/Admin/UserData/', {
        queryString: 'Excel',
        method: 'get',
      });
      expect(result).toBeNull(); // `exporData` no devuelve datos, solo descarga el archivo
    });

    it('should handle errors when exporting data', async () => {
      mockUtilsService.restService.and.returnValue(throwError('error'));

      const result = await service.exporData();

      expect(mockErrorService.handleError).toHaveBeenCalledWith('error');
      expect(result).toBeNull();
    });
  });
});