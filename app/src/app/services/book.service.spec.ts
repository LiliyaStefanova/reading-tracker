/* tslint:disable:no-unused-variable */

import { TestBed, inject } from '@angular/core/testing';

import { BookService } from './book.service';
import { BookApiService } from './api/book.api.service';
import { ApiMockService } from './api/api-mock.service';

describe('BookService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        BookService,
        {
          provide: BookApiService,
          useClass: ApiMockService
        }
      ]
    });
  });

  it('should inject correctly', inject([BookService], (service: BookService) => {
    expect(service).toBeTruthy();
  }));
});
