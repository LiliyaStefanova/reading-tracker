import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Book } from '../../representations/book';

@Injectable({
  providedIn: 'root'
})
export class ApiMockService {

  constructor() { }
  public getAllBooks(): Observable<Book[]> {
   return of([new Book({id: 1, title: 'Title Many', author: 'Author Many',
     genre: 'fantasy', notes: 'notes many', star: true, read: false})]);
  }
  public getBookById(id: number): Observable<Book> {
    return of(new Book({id: 2, title: 'Title One', author: 'Author One',
      genre: 'fiction', notes: 'notes one', star: false, read: false}));
  }
  public addBook(book: Book): Observable<Book> {
    return of(new Book({id: 3, title: 'Title New', author: 'Author New',
      genre: 'mathematics', notes: 'notes new', star: true, read: true}));
  }
  public updateBook(book: Book): Observable<Book> {
    return of(new Book({id: 4, title: 'Title Update', author: 'Author Update',
      genre: 'self-help', notes: 'notes update', star: true, read: false}));
  }
  public deleteBookById(id: Number): Observable<any> {
    return null;
  }
}
