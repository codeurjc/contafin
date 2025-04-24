import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminUserDataComponent } from './admin-user-data.component';
import { AdminService } from '../../../services/admin.service';
import { of } from 'rxjs';
import { User } from '../../../Interfaces/User/user.model';
import { ErrorService } from '../../../services/error.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('AdminUserDataComponent', () => {
  let component: AdminUserDataComponent;
  let fixture: ComponentFixture<AdminUserDataComponent>;
  let mockAdminService: jasmine.SpyObj<AdminService>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;

  beforeEach(async () => {
    mockAdminService = jasmine.createSpyObj('AdminService', ['getUserData', 'exporData']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['handleError']);

    await TestBed.configureTestingModule({
      declarations: [AdminUserDataComponent],
      providers: [
        { provide: AdminService, useValue: mockAdminService },
        { provide: ErrorService, useValue: mockErrorService },
      ],
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(AdminUserDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should call getUsers on initialization', () => {
      spyOn(component, 'getUsers');
      component.ngOnInit();
      expect(component.getUsers).toHaveBeenCalled();
    });
  });

  describe('getUsers', () => {
    it('should fetch users and set them to the users property', async () => {
      const mockUsers: User[] = [
        { id: 1, name: 'User 1', email: 'user1@example.com' } as User,
        { id: 2, name: 'User 2', email: 'user2@example.com' } as User,
      ];
      mockAdminService.getUserData.and.returnValue(Promise.resolve(mockUsers));

      await component.getUsers();

      expect(mockAdminService.getUserData).toHaveBeenCalled();
      expect(component.users).toEqual(mockUsers);
      expect(component.spinner).toBeFalse();
    });

    it('should handle errors when fetching users', async () => {
      mockAdminService.getUserData.and.returnValue(Promise.reject('Error occurred'));

      await component.getUsers();

      expect(mockAdminService.getUserData).toHaveBeenCalled();
      expect(component.users).toBeUndefined();
      expect(component.spinner).toBeFalse();
    });
  });

  describe('getUsersExcel', () => {
    it('should call exporData and set spinner to false after completion', async () => {
      mockAdminService.exporData.and.returnValue(Promise.resolve());

      await component.getUsersExcel();

      expect(mockAdminService.exporData).toHaveBeenCalled();
      expect(component.spinner).toBeFalse();
    });

    it('should handle errors when exporting data', async () => {
      mockAdminService.exporData.and.returnValue(Promise.reject('Error occurred'));

      await component.getUsersExcel();

      expect(mockAdminService.exporData).toHaveBeenCalled();
      expect(component.spinner).toBeFalse();
    });
  });
});