import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingComponent } from './landing.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {BookService} from '../services/book.service';
import {BookApiService} from '../services/api/book.api.service';
import {ApiMockService} from '../services/api/api-mock.service';

describe('LandingComponent', () => {
  let component: LandingComponent;
  let fixture: ComponentFixture<LandingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LandingComponent ],
      schemas: [
        NO_ERRORS_SCHEMA
      ],
      providers: [
        BookService,
        {
          provide: BookApiService,
          useClass: ApiMockService
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
