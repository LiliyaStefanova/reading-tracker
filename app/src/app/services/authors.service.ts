import { Injectable } from '@angular/core';
import {MessageService} from './message.service';
import {Observable} from 'rxjs/index';
import { AuthorApiService } from './api/authors.api.service';
import { Author } from '../representations/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorsService {
  authors: Author[] = [];

  constructor(private messageService: MessageService, private apiService: AuthorApiService) {}
  addAuthor(author: Author): Observable<Author> {
    return this.apiService.addAuthor(author);
  }
  updateAuthor(author: Author): Observable<Author> {
    return this.apiService.updateAuthor(author);
  }
  deleteAuthor(id: number): Observable<any> {
    return this.apiService.deleteAuthor(id);
  }
  getAuthors(): Observable<Author[]> {
    this.messageService.add(`Fetched all authors`);
    return this.apiService.getAllAuthors();
  }
  getAuthor(id: number): Observable<Author> {
    return this.apiService.getAuthor(id);
  }
}
