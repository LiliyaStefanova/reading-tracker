import { Injectable } from '@angular/core';
import {Category} from '../../representations/category';
import {MessageService} from '../message.service';
import {CategoriesApiService} from './categories.api.service';
import {Observable} from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  categories: Category [] = [];
  constructor(private messageService: MessageService, private apiService: CategoriesApiService) { }
  addCategory(category: Category): Observable<Category> {
    this.apiService.addCategory(category);
  }
  updateCategory(category: Category): Observable<Category> {
    this.apiService.updateCategory(category);
  }
  deleteCategory(id: number): Observable<any> {
    this.apiService.deleteCategory(id);
  }
  getCategory(id: number): Observable<Category> {
    this.apiService.getCategoryById(id);
  }
  getAllCategories(): Observable<Category[]> {
    this.apiService.getAllCategories();
  }
}
