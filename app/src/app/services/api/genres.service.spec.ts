import { TestBed } from '@angular/core/testing';

import { GenresApiService } from './genres.api.service';

describe('CategoriesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GenresApiService = TestBed.get(GenresApiService);
    expect(service).toBeTruthy();
  });
});
