import { TestBed, inject } from '@angular/core/testing';
import { ApiService } from './api.service';
import { HttpClient, HttpResponse} from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { Book } from './book';

describe('ApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClient, HttpClientTestingModule, HttpTestingController],
      providers: [
        ApiService,
        HttpTestingController
      ]
    });
  });

});

it('should inject', inject([HttpTestingController, ApiService],
  (httpMock: HttpTestingController, service: ApiService) => {
  expect(service).toBeTruthy();
}));

it('should work', inject([HttpTestingController, ApiService],
  (httpMock: HttpTestingController, service: ApiService) => {
    service.getAllBooks().subscribe(result => {
        expect(result).toBe([]);
    });
    httpMock.expectOne('/books/').event(
      new HttpResponse<Book[]>({body: []})
    );
}));
