import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../representations/book';
import { faStar, faCheck } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, Validators } from '@angular/forms';
import { genre, status } from '../constants';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  addForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(4)]],
    author: ['', [Validators.required, Validators.minLength(2)]],
    category: ['', [Validators.required]],
    genre: ['', [Validators.required]],
    notes: [''],
    status: [''],
    favourite: [''],
  });
  books: Book[];
  faStar = faStar;
  faCheck = faCheck;
  genres = genre;
  constructor(private bookService: BookService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getBooks();
  }
  getBooks(): void {
    this.bookService.getAllBooks()
      .subscribe(books => this.books = books);
  }
  addBook(data: any): void {
    console.log(data);
    const title = data.title;
    if (!title) {
      return;
    }
    this.bookService.addBook(data as Book)
      .subscribe(book => {
        this.books.push(book);
      });
    this.addForm.reset();
  }
  deleteBook(id: number) {
    this.books = this.books.filter(book => book.id !== id);
    this.bookService.deleteBook(id)
      .subscribe();
  }

}
