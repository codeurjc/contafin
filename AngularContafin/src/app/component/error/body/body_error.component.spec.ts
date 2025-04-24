import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyErrorComponent } from './body_error.component';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ErrorService } from '../../../services/error.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { User } from '../../../Interfaces/User/user.model';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('BodyErrorComponent', () => {
  let component: BodyErrorComponent;
  let fixture: ComponentFixture<BodyErrorComponent>;
  let mockModalService: jasmine.SpyObj<NgbModal>;
  let mockErrorService: jasmine.SpyObj<ErrorService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;

  beforeEach(async () => {
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);
    mockErrorService = jasmine.createSpyObj('ErrorService', ['getMessage']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['getToken', 'getUser']);

    await TestBed.configureTestingModule({
      declarations: [BodyErrorComponent],
      providers: [
        { provide: NgbModal, useValue: mockModalService },
        { provide: ErrorService, useValue: mockErrorService },
        { provide: TokenStorageService, useValue: mockTokenStorage },
      ],
                  schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(BodyErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('Constructor', () => {
    it('should initialize loggedUser if token exists', () => {
      const mockUser: User = { id: 1, name: 'Test User' } as User;
      mockTokenStorage.getToken.and.returnValue('mockToken');
      mockTokenStorage.getUser.and.returnValue(mockUser);

      const newComponent = new BodyErrorComponent(mockModalService, mockErrorService, mockTokenStorage);

      expect(newComponent.loggedUser).toEqual(mockUser);
    });

    it('should not initialize loggedUser if token does not exist', () => {
      mockTokenStorage.getToken.and.returnValue(null);

      const newComponent = new BodyErrorComponent(mockModalService, mockErrorService, mockTokenStorage);

      expect(newComponent.loggedUser).toBeUndefined();
    });

    it('should call setMessage in the constructor', () => {
      spyOn(BodyErrorComponent.prototype, 'setMessage');
      new BodyErrorComponent(mockModalService, mockErrorService, mockTokenStorage);
      expect(BodyErrorComponent.prototype.setMessage).toHaveBeenCalled();
    });
  });

  describe('setMessage', () => {
    it('should set errorMessage from ErrorService', () => {
      mockErrorService.getMessage.and.returnValue('Test error message');

      component.setMessage();

      expect(mockErrorService.getMessage).toHaveBeenCalled();
      expect(component.errorMessage).toBe('Test error message');
    });
  });

  describe('open', () => {
    it('should open a modal and set closeResult on success', async () => {
      const mockModalRef = { result: Promise.resolve('Closed') };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockContent');

      mockModalRef.result.then(() => {
        expect(component.closeResult).toBe('Closed with: Closed');
      });
    });

    it('should set closeResult on dismiss', async () => {
      const mockModalRef = { result: Promise.reject(ModalDismissReasons.ESC) };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockContent');

      mockModalRef.result.catch(() => {
        expect(component.closeResult).toBe('Dismissed by pressing ESC');
      });
    });
  });

  describe('getDismissReason', () => {
    it('should return "by pressing ESC" for ESC reason', () => {
      const result = component.getDismissReason(ModalDismissReasons.ESC);
      expect(result).toBe('by pressing ESC');
    });

    it('should return "by clicking on a backdrop" for BACKDROP_CLICK reason', () => {
      const result = component.getDismissReason(ModalDismissReasons.BACKDROP_CLICK);
      expect(result).toBe('by clicking on a backdrop');
    });

    it('should return "with: <reason>" for other reasons', () => {
      const result = component.getDismissReason('Other reason');
      expect(result).toBe('with: Other reason');
    });
  });
});