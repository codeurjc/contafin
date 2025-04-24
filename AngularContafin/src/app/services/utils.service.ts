import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import * as moment from 'moment';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ErrorService } from './error.service';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

const BASE_URL = environment.apiBase;

@Injectable({
  providedIn: 'root'
})

export class UtilsService {

  private errorService : ErrorService;

  constructor(
    public http: HttpClient
  ) {}

  public restService(name: string, config: any): Observable<any> {
    let url = BASE_URL+name;
    if (typeof config !== 'undefined') {
      const DATA = config.params || {};
      let method = config.method;

      if (typeof config.queryString !== 'undefined') {
        url = url + config.queryString;
      }

      /** TEMP */

    
      return this.http[method]<any>(url, DATA).pipe(
        map((data: any) => {
          if (typeof data !== 'undefined' && data !== null) {
              return data;
          }
        }),
        catchError((err) => {
          console.log('--------------');
          console.log('ERROR ON', name, 'SERVICE: ', err);
          console.log('--------------');
          
          return this.errorService.handleError(err);
        })
      );
    }
  }

  public getPageWith() {
    const MENU_WITH = 80;
    const PAGE_WITH = window.innerWidth - MENU_WITH;
    return PAGE_WITH;
  }

  public getPageHeight() {
    const HEADER_HEIGHT = 40;
    const PAGE_HEIGHT = window.innerHeight - HEADER_HEIGHT;
    return PAGE_HEIGHT;
  }
}
