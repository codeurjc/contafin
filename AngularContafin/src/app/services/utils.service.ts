import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import * as moment from 'moment';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

const BASE_URL = environment.apiBase;

declare global {
  interface Array<T> {
    multipleSortNatural(data, columns);
  }
}

if (!Array.prototype.multipleSortNatural) {
  Array.prototype.multipleSortNatural = (data, columns) => {
    const NUMBER_GROUPS = /(-?\d*\.?\d+)/g;

    const naturalSort = (a, b, columnname, reverse) => {
      let aField1 = a[columnname];
      let bField1 = b[columnname];
      let aa;
      let bb;

      const collator = Intl.Collator(['es-ES'], {
        numeric: true,
        sensitivity: 'base',
        usage: 'sort'
      });


      // undefined, null and white values are the same
      if (typeof aField1 === 'undefined') {
        aField1 = a;
      } else if (aField1 === null || aField1 === '') {
        aField1 = '-1';
      }

      if (typeof bField1 === 'undefined') {
        bField1 = b;
      } else if (bField1 === null || bField1 === '') {
        bField1 = '-1';
      }

      aa = String(aField1).split(NUMBER_GROUPS);
      bb = String(bField1).split(NUMBER_GROUPS);

      if (reverse === 1) {
        return collator.compare(aa, bb) * (-1 * reverse);
      } else {
        return collator.compare(aa, bb);
      }
    };

    data.sort((a, b) => {
      let result;
      let reverse = -1;

      for (let column of columns) {
        reverse = -1;

        if (column[0] === '-'){
            column = column.substring(1);
            reverse = 1;
        }

        result = naturalSort(a, b, column, reverse);
        if (result !== 0) {
          return result;
        }
      }
      // If both are exactly the same
      return 0;
    });
  };
}

interface Entity { label: string; id: string; }

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  public params = null;
  public contingencies = null;
  public timeZone = 'Europe/Madrid';
  public keycloak;
  public calendarES: any;
  public newAppointment = null;
  public modifyAppointment = false;
  public restError = false;

  /**
   * Variable tipo model Driver encargada de gestionar la data entre componente de driver
   */
  public datadriver : any;
  /**
  * Variable que controla el estado de modificar, true: modificar
  */
  public statusDriverModify : boolean;
  /**
  * Variable que controla el estado de ver , true: ver
  */
  public statusDriverView : boolean;
  /**
  * Variable que controla el estado de ver , true: ver
  */
  public statusDriverCreate : boolean;



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

      this.restError = false;
      return this.http[method]<any>(url, DATA).pipe(
        map((data: any) => {
          if (typeof data !== 'undefined' && data !== null && !this.restError) {
            if (data.header && data.header.code && (data.header.code !== 0 && data.header.code !== '0') ) {
              if (config.error && ( data.header.code === -1 || data.header.code === '-1' ) ) {
                console.log('--------------');
                console.log('ERROR ON ', name, 'SERVICE: ', data.header.description);
                console.log('--------------');
                config.error(data);
                return of(data);
              }
            } else {
              return data;
            }
          }
        }),
        catchError((err) => {
          this.restError = true;
          if (config.error) {
            console.log('--------------');
            console.log('ERROR ON', name, 'SERVICE: ', err);
            console.log('--------------');

            config.error(err);
          }
          return of(err);
        })
      );
    }
  }

  public restServiceHeaders(name: string, config: any): Observable<any> {
    let url = BASE_URL+name;
    if (typeof config !== 'undefined') {
      const headers = config.headers || {};
      let method = config.method;

      if (typeof config.queryString !== 'undefined') {
        url = url + config.queryString;
      }

      /** TEMP */

      this.restError = false;
      return this.http[method]<any>(url, {headers: headers}).pipe(
        map((data: any) => {
          if (typeof data !== 'undefined' && data !== null && !this.restError) {
            if (data.header && data.header.code && (data.header.code !== 0 && data.header.code !== '0') ) {
              if (config.error && ( data.header.code === -1 || data.header.code === '-1' ) ) {
                console.log('--------------');
                console.log('ERROR ON ', name, 'SERVICE: ', data.header.description);
                console.log('--------------');
                config.error(data);
                return of(data);
              }
            } else {
              return data;
            }
          }
        }),
        catchError((err) => {
          this.restError = true;
          if (config.error) {
            console.log('--------------');
            console.log('ERROR ON', name, 'SERVICE: ', err);
            console.log('--------------');

            config.error(err);
          }
          return of(err);
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
    const FOOTER_HEIGHT = 30;
    const PAGE_HEIGHT = window.innerHeight - HEADER_HEIGHT - FOOTER_HEIGHT;
    return PAGE_HEIGHT;
  }
}
