import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { MessageService } from './message.service';
import {Genre} from '../representations/genre';
import {GenresApiService} from './api/genres.api.service';

@Injectable({
    providedIn: 'root'
})
export class GenresService {
    genres: Genre [] = [];

    constructor(private messageService: MessageService, private apiService: GenresApiService) { }
    addGenre(genre: Genre): Observable<Genre> {
        return this.apiService.addGenre(genre);
    }
    getAllGenres(): Observable<Genre[]> {
        this.messageService.add(`Fetched all genres`);
        return this.apiService.getAllGenres();
    }
    getGenre(id: string): Observable<Genre> {
        this.messageService.add(`GenreService: fetched genre with id ${id}`);
        return this.apiService.getGenreById(id);
    }
    deleteGenre(id: string): Observable<any> {
        return this.apiService.deleteGenre(id);
    }
    updateGenre(genre: Genre): Observable<Genre> {
        return this.apiService.updateGenre(genre);
    }
    // TODO this needs a dedicated endpoint
    findAllBooksInGenre(id: string): Observable<Genre> {
        return this.apiService.getGenreById(id);
    }

}
