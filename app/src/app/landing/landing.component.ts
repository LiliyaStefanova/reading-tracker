import { Component, OnInit } from '@angular/core';
import { Book } from '../representations/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  books: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.getBooks();
  }
  getBooks(): void {
    this.bookService.getAllBooks()
      .subscribe(books => this.books = books.filter(b => b.favourite === true));
  }

}
