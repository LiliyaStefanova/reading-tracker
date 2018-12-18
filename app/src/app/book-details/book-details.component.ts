import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { BookService } from '../services/book.service';
import { Book } from '../book';
import { genre } from '../constants';


@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  @Input() book: Book;
  genres = genre;
  constructor(private route: ActivatedRoute,
              private bookService: BookService,
              private location: Location) { }

  ngOnInit() {
    this.getBook();
  }
  getBook(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.bookService.getBook(id)
      .subscribe(book => this.book = book);
  }
  goBack(): void {
    this.location.back();
  }
  save(): void {
    this.bookService.updateBook(this.book)
      .subscribe(() => this.goBack());
  }

}
