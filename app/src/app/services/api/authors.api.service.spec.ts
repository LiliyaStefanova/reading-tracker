import { TestBed } from '@angular/core/testing';

import { AuthorApiService } from './authors.api.service';

describe('AuthorsApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthorApiService = TestBed.get(AuthorApiService);
    expect(service).toBeTruthy();
  });
});
