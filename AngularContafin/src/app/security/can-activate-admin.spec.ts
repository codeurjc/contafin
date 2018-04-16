import { TestBed, inject } from '@angular/core/testing';

import { CanActivateAdmin } from './can-activate-admin';

describe('CanActivateAdmin', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CanActivateAdmin]
    });
  });

  it('should be created', inject([CanActivateAdmin], (service: CanActivateAdmin) => {
    expect(service).toBeTruthy();
  }));
});
