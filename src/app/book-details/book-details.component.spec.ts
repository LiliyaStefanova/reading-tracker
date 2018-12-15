import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDetailsComponent } from './book-details.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {BookService} from '../book.service';
import {ApiService} from '../api.service';
import {ApiMockService} from '../api-mock.service';
import {ActivatedRoute} from '@angular/router';
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';
import { of } from 'rxjs';

describe('BookDetailsComponent', () => {
  let component: BookDetailsComponent;
  let fixture: ComponentFixture<BookDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookDetailsComponent ],
      schemas: [
        NO_ERRORS_SCHEMA
      ],
      providers: [
        BookService,
        {
          provide: ApiService,
          useClass: ApiMockService
        },
        {
          provide: ActivatedRoute,
          useValue: {snapshot: {params: {'id': '123'}}}
        },
        Location,
        {
          provide: LocationStrategy,
          useClass: PathLocationStrategy,
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
