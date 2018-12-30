import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Category} from '../../representations/category';
import { catchError, tap, map } from 'rxjs/internal/operators';
import { Observable } from 'rxjs/index';
import {BaseApi, URL, httpOptions} from './api';

@Injectable({
  providedIn: 'root'
})
export class CategoriesApiService extends BaseApi {

  constructor(private http: HttpClient) {
    super();
  }

  public getAllCategories(): Observable<Category[]> {
    const url = `${URL}/categories/all`;
    return this.http
      .get<Category[]>(url)
      .pipe(
        tap(_ => console.log('got categories')),
        catchError(this.handleErrors<Category[]>('getAllCategories', []))
      );
  }
  public getCategoryById(id: number): Observable<Category> {
    const url = `${URL}/categories/category/${id}`;
    return this.http
      .get(url)
      .pipe(
        map(category => category as Category),
        tap(_ => console.log(`fetched category with id=${id}`)),
        catchError(this.handleErrors<Category>(`getCategoryById id=${id}`))
      );
  }
  public addCategory(category: Category): Observable<Category> {
    const url = `${URL}/categories/category`;
    return this.http
      .post(url, category, httpOptions)
      .pipe(
        tap((cat: Category) => console.log(`added category with id=${cat.id}`)),
        catchError(this.handleErrors<Category>('addCategory'))
      );
  }
  public updateCategory(category: Category): Observable<Category> {
    const url = `${URL}/categories/${category.id}`;
    return this.http
      .put(url, category, httpOptions)
      .pipe(
        tap(_ => console.log(`updated category id=${category.id}`)),
        catchError(this.handleErrors<any>(`updateCategory`))
      );
  }
  public deleteCategory(id: number): Observable<any> {
    const url = `${URL}/categories/category/${id}`;
    return this.http
      .delete<Category>(url, httpOptions)
      .pipe(
        tap(_ => console.log(`deleted category with id: ${id}`)),
        catchError(this.handleErrors<any>('deleteCategory'))
      );
  }
}
