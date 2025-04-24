import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HeaderNavbarComponent } from './header_navbar.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('HeaderNavbarComponent', () => {
  let component: HeaderNavbarComponent;
  let fixture: ComponentFixture<HeaderNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HeaderNavbarComponent],
                  schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize kind property to "1"', () => {
    expect(component.kind).toBe('1');
  });
});