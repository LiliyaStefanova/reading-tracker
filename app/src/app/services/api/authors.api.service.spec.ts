import { TestBed } from '@angular/core/testing';

import { AuthorsApiService } from './authors.api.service';

describe('AuthorsApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Authors.ApiService = TestBed.get(Authors.ApiService);
    expect(service).toBeTruthy();
  });
});
