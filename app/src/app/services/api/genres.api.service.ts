import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Genre} from '../../representations/genre';
import { catchError, tap, map } from 'rxjs/internal/operators';
import { Observable } from 'rxjs/index';
import {BaseApi, URL, httpOptions} from './api';

@Injectable({
  providedIn: 'root'
})
export class GenresApiService extends BaseApi {

  constructor(private http: HttpClient) {
    super();
  }

  public getAllGenres(): Observable<Genre[]> {
    const url = `${URL}/genre/all`;
    return this.http
      .get<Genre[]>(url)
      .pipe(
        tap(_ => console.log('got genres')),
        catchError(this.handleErrors<Genre[]>('getAllGenres', []))
      );
  }
  public getGenreById(id: string): Observable<Genre> {
    const url = `${URL}/genre/${id}`;
    return this.http
      .get(url)
      .pipe(
        map(genre => genre as Genre),
        tap(_ => console.log(`fetched genre with id=${id}`)),
        catchError(this.handleErrors<Genre>(`getGenreById id=${id}`))
      );
  }
  public addGenre(genre: Genre): Observable<Genre> {
    const url = `${URL}/genre`;
    return this.http
      .post(url, genre, httpOptions)
      .pipe(
        tap((cat: Genre) => console.log(`added genre with id=${cat.id}`)),
        catchError(this.handleErrors<Genre>('addGenre'))
      );
  }
  public updateGenre(genre: Genre): Observable<Genre> {
    const url = `${URL}/genre/${genre.id}`;
    return this.http
      .put(url, genre, httpOptions)
      .pipe(
        tap(_ => console.log(`updated category id=${genre.id}`)),
        catchError(this.handleErrors<any>(`updateGenre`))
      );
  }
  public deleteGenre(id: string): Observable<any> {
    const url = `${URL}/genre/${id}`;
    return this.http
      .delete<Genre>(url, httpOptions)
      .pipe(
        tap(_ => console.log(`deleted genre with id: ${id}`)),
        catchError(this.handleErrors<any>('deleteGenre'))
      );
  }
}
