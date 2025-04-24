import { Injectable, OnInit } from '@angular/core';
import { User } from '../Interfaces/User/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { TokenStorageService } from './token-storage.service';
import { ErrorService } from './error.service';

const URL = environment.apiBase;


@Injectable()
export class LoginService {

    constructor(public utils: UtilsService, private errorService : ErrorService) { }

    async logIn(user: string, pass: string) {

        let useData = null;
			 await this.utils.restService('/login', {
				method: 'post',
                params: {
                    name : user,
                    pass : pass
                  }
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		  return useData;
    }

    /*async logOut() {

        let useData = null;
			 await this.utils.restService('/logout', {
				    method: 'post'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					useData = data;
          this.tokenStorage.signOut();
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
    }*/
}