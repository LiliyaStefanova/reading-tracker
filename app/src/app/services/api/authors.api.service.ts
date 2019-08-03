import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import { catchError, tap, map } from 'rxjs/internal/operators';
import { Author } from '../../representations/author';
import {BaseApi, URL, httpOptions} from './api';

@Injectable({
  providedIn: 'root'
})
export class AuthorApiService extends BaseApi {

  constructor(private http: HttpClient) {
    super();
  }

  public getAllAuthors(): Observable<Author[]> {
    const url = `${URL}/authors/all`;
    return this.http
      .get<Author[]>(url)
      .pipe(
        tap(_ => console.log('got authorships')),
        catchError(this.handleErrors<Author[]>('getAllAuthors', []))
      );
  }

  public getAuthor(id: number): Observable<Author> {
    const url = `${URL}/authors/author/${id}`;
    return this.http
      .get(url)
      .pipe(
        map(author => author as Author),
        tap(_ => console.log(`fetched author with id=${id}`)),
        catchError(this.handleErrors<Author>(`getAuthorById id=${id}`))
      );
  }

  public addAuthor(author: Author): Observable<Author> {
    const url = `${URL}/authors/author`;
    return this.http
      .post(url, author, httpOptions)
      .pipe(
        tap((auth: Author) => console.log(`added book with id=${auth.id}`)),
        catchError(this.handleErrors<Author>('addAuthor'))
      );
  }

  public updateAuthor(author: Author): Observable<Author> {
    const url = `${URL}/authors/${author.id}`;
    return this.http
      .put(url, author, httpOptions)
      .pipe(
        tap(_ => console.log(`updated book id=${author.id}`)),
        catchError(this.handleErrors<any>(`updateAuthor`))
      );
  }

  public deleteAuthor(id: number): Observable<any> {
    const url = `${URL}/authors/author/${id}`;
    return this.http
      .delete<Author>(url, httpOptions)
      .pipe(
        tap(_ => console.log(`deleted author with id: ${id}`)),
        catchError(this.handleErrors<any>('deleteAuthorById'))
      );
  }

}
