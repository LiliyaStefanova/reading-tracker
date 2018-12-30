import { TestBed, inject } from '@angular/core/testing';
import { BookApiService } from './book.api.service';
import { HttpClient, HttpResponse} from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { Book } from '../../representations/book';

describe('BookApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClient, HttpClientTestingModule, HttpTestingController],
      providers: [
        BookApiService,
        HttpTestingController
      ]
    });
  });

});

it('should inject', inject([HttpTestingController, BookApiService],
  (httpMock: HttpTestingController, service: BookApiService) => {
  expect(service).toBeTruthy();
}));

it('should work', inject([HttpTestingController, BookApiService],
  (httpMock: HttpTestingController, service: BookApiService) => {
    service.getAllBooks().subscribe(result => {
        expect(result).toBe([]);
    });
    httpMock.expectOne('/books/').event(
      new HttpResponse<Book[]>({body: []})
    );
}));
