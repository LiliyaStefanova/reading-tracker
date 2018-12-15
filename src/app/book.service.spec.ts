// import { TestBed, async, inject } from '@angular/core/testing';
// import {  of } from 'rxjs';
//
// import { BookService } from './book.service';
// import { ApiService } from './api.service';
// import { ApiMockService } from './api-mock.service';
// import { Book } from './book';
//
// describe('BookService', () => {
//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       providers: [
//         BookService,
//         {
//           provide: ApiService,
//           useClass: ApiMockService
//         }
//       ]
//     });
//   });
//
//   it('should inject correctly', inject([BookService], (service: BookService) =>{
//     expect(service).toBeTruthy();
//   }));
//   describe('#getAllBooks()', () => {
//     it('should return empty by default',
//       inject([BookService], (service: BookService) => {
//         expect(service.getAllBooks()).toEqual(of([]));
//       }));
//     it('should return all books',
//       inject([BookService], (service: BookService) => {
//         const book1 = new Book({title: 'Test Book A', author: 'Author A', genre: 'fantasy', notes: '', star: true, read: false});
//         const book2 = new Book({title: 'Test Book B', author: 'Author B', genre: 'non-fiction', notes: '', star: false, read: true});
//         service.addBook(book1);
//         service.addBook(book2);
//         expect(service.getAllBooks()).toEqual(of([book1, book2]));
//       }));
//   });
// });
