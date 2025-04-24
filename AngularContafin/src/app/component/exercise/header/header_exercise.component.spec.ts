import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HeaderExerciseComponent } from './header_exercise.component';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('HeaderExerciseComponent', () => {
  let component: HeaderExerciseComponent;
  let fixture: ComponentFixture<HeaderExerciseComponent>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockModalService: jasmine.SpyObj<NgbModal>;

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);

    await TestBed.configureTestingModule({
      declarations: [HeaderExerciseComponent],
      providers: [
        { provide: Router, useValue: mockRouter },
        { provide: NgbModal, useValue: mockModalService },
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('open', () => {
    it('should open a modal and set closeResult on success', async () => {
      const mockModalRef = { result: Promise.resolve('Exit click') };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockModal');

      mockModalRef.result.then(() => {
        expect(component.closeResult).toBe('${result}');
        expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
      });
    });

    it('should set closeResult on dismiss', async () => {
      const mockModalRef = { result: Promise.reject(ModalDismissReasons.ESC) };
      mockModalService.open.and.returnValue(mockModalRef as any);

      await component.open('mockModal');

      mockModalRef.result.catch(() => {
        expect(component.closeResult).toBe('$[reason}');
      });
    });
  });

  describe('goHome', () => {
    it('should navigate to /home', () => {
      component.goHome();
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
    });
  });
});