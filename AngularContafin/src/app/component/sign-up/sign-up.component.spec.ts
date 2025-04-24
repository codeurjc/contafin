import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { SignUpComponent } from './sign-up.component';
import { ErrorService } from '../../services/error.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SignUpService } from '../../services/sign-up.service';
import { RouterTestingModule } from '@angular/router/testing';

let component: SignUpComponent;
let fixture: ComponentFixture<SignUpComponent>;

describe('SignUpComponent', () => {

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule,HttpClientTestingModule],
      providers: [SignUpService, TokenStorageService, ErrorService],
      declarations: [SignUpComponent],
            schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));


  beforeEach(() => {
    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    const element = document.createElement('input');
    element.setAttribute('id', 'signup_button');
    document.body.appendChild(element);
  });

  afterEach(() => {
    const element = document.getElementById('signup_button');
    if (element) {
      element.remove();
    }
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
describe('SignUpComponent - signup method', () => {
  let mockSignUpService: jasmine.SpyObj<SignUpService>;
  let mockTokenStorage: jasmine.SpyObj<TokenStorageService>;
  let mockRouter: jasmine.SpyObj<Router>;


  beforeEach(() => {
    mockSignUpService = jasmine.createSpyObj('SignUpService', ['signup']);
    mockTokenStorage = jasmine.createSpyObj('TokenStorageService', ['saveToken', 'saveLoginInfo']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.overrideComponent(SignUpComponent, {
      set: {
        providers: [
          { provide: SignUpService, useValue: mockSignUpService },
          { provide: TokenStorageService, useValue: mockTokenStorage },
          { provide: Router, useValue: mockRouter },
        ],
      },
    });

    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should call SignUpService.signup with correct data', async () => {
    const mockResponse = { accessToken: 'token', user: {} };
    mockSignUpService.signup.and.returnValue(Promise.resolve(mockResponse));
    component.userData = { name: 'John', email: 'john@example.com', pass: 'password' };

    await component.signup();

    expect(mockSignUpService.signup).toHaveBeenCalledWith(component.userData);
  });

  it('should save token, save login info, and navigate on success', async () => {
    const mockResponse = { accessToken: 'token', user: {} };
    mockSignUpService.signup.and.returnValue(Promise.resolve(mockResponse));

    await component.signup();

    expect(mockTokenStorage.saveToken).toHaveBeenCalledWith('token');
    expect(mockTokenStorage.saveLoginInfo).toHaveBeenCalledWith(mockResponse);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/home']);
  });

  it('should show alert and re-enable button on error', async () => {
    spyOn(window, 'alert');
    mockSignUpService.signup.and.returnValue(Promise.reject('Error'));

    await component.signup();

    expect(window.alert).toHaveBeenCalledWith('Invalid email');
  });
});
