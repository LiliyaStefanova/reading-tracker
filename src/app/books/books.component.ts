import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Book } from '../book';
import { faStar, faCheck } from '@fortawesome/free-solid-svg-icons';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  addForm = new FormGroup({
    title: new FormControl(''),
    author: new FormControl(''),
    genre: new FormControl(''),
    notes: new FormControl(''),
    star: new FormControl(''),
    read: new FormControl('')
  });
  books: Book[];
  faStar = faStar;
  faCheck = faCheck;
  constructor(private bookService: BookService) { }

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
    data.read === 'on' ? data.read = true : data.read = false;
    data.star === 'on' ? data.star = true : data.star = false;
    this.bookService.addBook(data as Book)
      .subscribe(book => {
        this.books.push(book);
      });
  }
  deleteBook(id: number) {
    this.books = this.books.filter(book => book.id !== id);
    this.bookService.deleteBook(id)
      .subscribe();
  }

}
