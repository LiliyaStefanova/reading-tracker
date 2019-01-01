import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDetailsComponent } from './book-details.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {BookService} from '../services/book.service';
import {BookApiService} from '../services/api/book.api.service';
import {ApiMockService} from '../services/api/api-mock.service';
import {ActivatedRoute, convertToParamMap} from '@angular/router';
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';

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
          provide: BookApiService,
          useClass: ApiMockService
        },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap:  convertToParamMap({ id: 1 })
            }
          }
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
