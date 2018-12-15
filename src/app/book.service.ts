import { Injectable } from '@angular/core';
import { Book } from './book';
import { Observable} from 'rxjs';
import { MessageService } from './message.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  books: Book [] = [];

  constructor(private messageService: MessageService, private apiService: ApiService) { }
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
  setBookReadStatus(book: Book) {
    book.read = !book.read;
    // TODO take a deep copy of this object to prevent it from being mutated
    return this.apiService.updateBook(book);
  }
  setBookFavouriteStatus(book: Book) {
    book.star = !book.star;
    return this.apiService.updateBook(book);
  }
  deleteBook(id: number): Observable<any> {
    return this.apiService.deleteBookById(id);
  }
  updateBook(book: Book): Observable<Book> {
    return this.apiService.updateBook(book);
  }

}
