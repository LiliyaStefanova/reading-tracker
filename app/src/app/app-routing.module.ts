import { NgModule } from '@angular/core';
import { RouterModule, Routes, provideRoutes } from '@angular/router';
import { BooksComponent} from './books/books.component';
import { LandingComponent } from './landing/landing.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import {GenresComponent} from './genres/genres.component';

const routes: Routes = [
  { path: 'books', component: BooksComponent },
  { path: 'landing', component: LandingComponent },
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'detail/:id', component: BookDetailsComponent },
  { path: 'genres', component: GenresComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
