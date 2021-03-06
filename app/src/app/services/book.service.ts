import { Injectable } from '@angular/core';
import { Book } from '../representations/book';
import { Observable} from 'rxjs';
import { MessageService } from './message.service';
import { BookApiService } from './api/book.api.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  books: Book [] = [];

  constructor(private messageService: MessageService, private apiService: BookApiService) { }
  addBook(book: Book): Observable<Book> {
    return this.apiService.addBook(book);
  }
  getAllBooks(): Observable<Book[]> {
    this.messageService.add(`Fetched all books`);
    return this.apiService.getAllBooks();
  }
  getBook(id: number): Observable<Book> {
    this.messageService.add(`BookService: fetched book with id ${id}`);
    return this.apiService.getBookById(id);
  }
  // TODO ability to set book read status by clicking on the tick icon instead of going to the form
  setBookReadStatus(book: Book) {
    // TODO take a deep copy of this object to prevent it from being mutated
    return this.apiService.updateBook(book);
  }
  // TODO ability to mark book as favourite by clicking on the star icon instead of going to the form
  setBookFavouriteStatus(book: Book) {
    book.favourite = !book.favourite;
    return this.apiService.updateBook(book);
  }
  deleteBook(id: string): Observable<any> {
    return this.apiService.deleteBookById(id);
  }
  updateBook(book: Book): Observable<Book> {
    return this.apiService.updateBook(book);
  }

}
