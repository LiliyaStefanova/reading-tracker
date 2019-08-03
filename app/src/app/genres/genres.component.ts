import {Component, OnInit} from '@angular/core';
import {Genre} from '../representations/genre';
import {GenresService} from '../services/genres.service';
import { faMinusSquare, faEdit } from '@fortawesome/free-solid-svg-icons';
@Component({
    selector: 'app-genres',
    templateUrl: './genres.component.html',
    styleUrls: ['./genres.component.css']
})
export class GenresComponent implements OnInit {

    genres: Genre[];
    faMinusSquare = faMinusSquare;
    faEdit = faEdit;
    constructor(private genresService: GenresService) {
    }

    ngOnInit() {
        this.getGenres();
    }

    getGenres(): void {
        this.genresService.getAllGenres()
            .subscribe(genres => {
                this.genres = genres
                    .map(this.createGenre)
                    .sort((a, b) => (a.name > b.name) ? 1 : 0);
            console.log(this.genres); });
    }

    addGenre(data: any): void {
        console.log(data);
        const name = data.name;
        if (!name) {
            return;
        }
        this.genresService.addGenre(data as Genre)
            .subscribe(genre => {
                this.genres.push(genre);
            });
    }

    deleteGenre(id: string) {
        this.genres = this.genres.filter(genre => genre.id !== id);
        this.genresService.deleteGenre(id)
            .subscribe();
    }

    createGenre(payload: any): Genre {
        return new Genre({
            id: payload.id, name: payload.name, description: payload.description,
            category: payload.category, created_ts: new Date(payload.created_ts)
        });
    }

}


