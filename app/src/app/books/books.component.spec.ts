import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksComponent } from './books.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {BookService} from '../services/book.service';
import {BookApiService} from '../services/api/book.api.service';
import {ApiMockService} from '../services/api/api-mock.service';
import { FormBuilder } from '@angular/forms';

describe('BooksComponent', () => {
  let component: BooksComponent;
  let fixture: ComponentFixture<BooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BooksComponent ],
      schemas: [
        NO_ERRORS_SCHEMA
      ],
      providers: [
        BookService,
        {provide: BookApiService,
         useClass: ApiMockService},
        FormBuilder
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
