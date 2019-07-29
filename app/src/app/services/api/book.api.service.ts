import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/internal/operators';
import { Observable } from 'rxjs';
import {Book} from '../../representations/book';
import {BaseApi, URL, httpOptions} from './api';

@Injectable({
  providedIn: 'root'
})
export class BookApiService extends BaseApi {

  constructor(private http: HttpClient) {
    super();
  }

  public getAllBooks(): Observable<Book[]> {
    const url = `${URL}/readingEntry/all`;
    return this.http
      .get<Book[]>(url)
      .pipe(
        tap(_ => console.log('got books')),
        catchError(this.handleErrors<Book[]>('getAllBooks', []))
      );
  }
  public getBookById(id: number): Observable<Book> {
    const url = `${URL}/readingEntry/${id}`;
    return this.http
      .get(url)
      .pipe(
        map(book => book as Book),
        tap(_ => console.log(`fetched book with id=${id}`)),
        catchError(this.handleErrors<Book>(`getBookById id=${id}`))
      );
  }
  public addBook(book: Book): Observable<Book> {
    const url = `${URL}/readingEntry`;
    return this.http
      .post(url, book, httpOptions)
      .pipe(
        tap((bk: Book) => console.log(`added book with id=${bk.id}`)),
        catchError(this.handleErrors<Book>('addBook'))
      );
  }
  // // TODO server end point here
  // public updateBook(book: Book): Observable<Book> {
  //   const url = `${URL}/readingEntry/${book.id}`;
  //   return this.http
  //     .put(url, book, httpOptions)
  //     .pipe(
  //       tap(_ => console.log(`updated book id=${book.id}`)),
  //       catchError(this.handleErrors<any>(`updateBook`))
  //     );
  // }
  // // TODO server end point here
  // public deleteBookById(id: Number): Observable<any> {
  //   const url = `${URL}/books/book/${id}`;
  //   return this.http
  //     .delete<Book>(url, httpOptions)
  //     .pipe(
  //       tap(_ => console.log(`deleted book with id: ${id}`)),
  //       catchError(this.handleErrors<any>('deleteBookById'))
  //     );
  // }
}
