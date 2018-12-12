import { Component, OnInit } from '@angular/core';
import { TEST_BOOKS } from '../test-books';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  selectedBook: Book;
  books = TEST_BOOKS;
  onSelect(book: Book): void {
    this.selectedBook = book;
  }
  constructor() { }

  ngOnInit() {
  }

}
