import { environment} from '../../../environments/environment';
import {HttpHeaders} from '@angular/common/http';
import { Observable, of } from 'rxjs';


export class BaseApi {
  public handleErrors<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed with error message: ${error.message}`);
      return of(result as T);
    };
  }
}

export const URL = environment.apiUrl;

export const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'}),
};
