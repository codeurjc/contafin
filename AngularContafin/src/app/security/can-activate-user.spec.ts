import { TestBed, inject } from '@angular/core/testing';

import { CanActivateUser } from './can-activate-user';

describe('CanActivateUser', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CanActivateUser]
    });
  });

  it('should be created', inject([CanActivateUser], (service: CanActivateUser) => {
    expect(service).toBeTruthy();
  }));
});
