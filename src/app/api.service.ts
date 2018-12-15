import { Injectable } from '@angular/core';
import { environment} from '../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/internal/operators';
import { Observable, of } from 'rxjs';
import {Book} from './book';

const URL = environment.apiUrl;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'}),
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  public getAllBooks(): Observable<Book[]> {
    const url = URL + '/books';
    return this.http
      .get<Book[]>(url)
      .pipe(
        tap(_ => console.log('got books')),
        catchError(this.handleErrors<Book[]>('getAllBooks', []))
      );
  }
  public getBookById(id: number): Observable<Book> {
    const url = `${URL}/books/${id}`;
    return this.http
      .get(url)
      .pipe(
        map(book => book as Book),
        tap(_ => console.log(`fetched book with id=${id}`)),
        catchError(this.handleErrors<Book>(`getBookById id=${id}`))
      );
  }
  public addBook(book: Book): Observable<Book> {
    const url = `${URL}/books`;
    return this.http
      .post(url, book, httpOptions)
      .pipe(
        tap((bk: Book) => console.log(`added book with id=${bk.id}`)),
        catchError(this.handleErrors<Book>('addBook'))
      );
  }
  public updateBook(book: Book): Observable<Book> {
    const url = `${URL}/books/${book.id}`;
    return this.http
      .put(url, book, httpOptions)
      .pipe(
        tap(_ => console.log(`updated book id=${book.id}`)),
        catchError(this.handleErrors<any>(`updateBook`))
      );
  }
  public deleteBookById(id: Number): Observable<any> {
    const url = `${URL}/books/${id}`;
    return this.http
      .delete<Book>(url, httpOptions)
      .pipe(
        tap(_ => console.log(`deleted book with id: ${id}`)),
        catchError(this.handleErrors<any>('deleteBookById'))
      );
  }
  private handleErrors<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed with error message: ${error.message}`);
      return of(result as T);
    };
  }
}
