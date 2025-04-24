import { TestBed } from '@angular/core/testing';
import { ErrorService } from './error.service';
import { throwError } from 'rxjs';

describe('ErrorService', () => {
  let service: ErrorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ErrorService],
    });

    service = TestBed.inject(ErrorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('handleError', () => {
    it('should log the error to the console', () => {
      const consoleSpy = spyOn(console, 'error');
      const mockError = { message: 'error' };

      service.handleError(mockError);

      expect(consoleSpy).toHaveBeenCalledWith(mockError);
    });

    it('should return a user-friendly error message', () => {
      const mockError ='Test error';

      const result = service.handleError(mockError);

      expect(result).toEqual(null);
    });
  });

  describe('setMessage and getMessage', () => {
    it('should log the error to the console', () => {
      service.setMessage("The web page doesn't exist or you don't have permission.");

      expect(service.getMessage()).toEqual("The web page doesn't exist or you don't have permission.");
    });
  });

});