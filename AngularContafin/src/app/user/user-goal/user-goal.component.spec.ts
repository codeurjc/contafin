import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserGoalComponent } from './user-goal.component';

describe('UserGoalComponent', () => {
  let component: UserGoalComponent;
  let fixture: ComponentFixture<UserGoalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserGoalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserGoalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
