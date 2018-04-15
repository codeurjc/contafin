import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUserDataComponent } from './admin-user-data.component';

describe('AdminUserDataComponent', () => {
  let component: AdminUserDataComponent;
  let fixture: ComponentFixture<AdminUserDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminUserDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUserDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
