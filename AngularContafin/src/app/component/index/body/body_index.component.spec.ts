import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BodyIndexComponent } from './body_index.component';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('BodyIndexComponent', () => {
  let component: BodyIndexComponent;
  let fixture: ComponentFixture<BodyIndexComponent>;
  let mockModalService: jasmine.SpyObj<NgbModal>;

  beforeEach(async () => {
    mockModalService = jasmine.createSpyObj('NgbModal', ['open']);

    await TestBed.configureTestingModule({
      declarations: [BodyIndexComponent],
      providers: [
        { provide: NgbModal, useValue: mockModalService },
      ]
      ,
            schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(BodyIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
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
      const result = component['getDismissReason'](ModalDismissReasons.ESC);
      expect(result).toBe('by pressing ESC');
    });

    it('should return "by clicking on a backdrop" for BACKDROP_CLICK reason', () => {
      const result = component['getDismissReason'](ModalDismissReasons.BACKDROP_CLICK);
      expect(result).toBe('by clicking on a backdrop');
    });

    it('should return "with: <reason>" for other reasons', () => {
      const result = component['getDismissReason']('Other reason');
      expect(result).toBe('with: Other reason');
    });
  });
});