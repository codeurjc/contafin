import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from './utils.service';
import { ErrorService } from './error.service';

const BASE_URL = environment.apiBase + '/signup';

@Injectable()
export class SignUpService {

  constructor(public utils: UtilsService, private errorService : ErrorService) { }

  async signup(userData: any) : Promise<any> {
    
        let useData = null;
           await this.utils.restService('/signup', {
            params: userData,
            method: 'post'
            }).toPromise().then(
            (data) => {
              if (typeof data !== 'undefined' && data !== null) {
              console.log(data);
              useData = data;
              }
            }
            ).catch(error => this.errorService.handleError(error));
        return useData; 
  }

  
}
