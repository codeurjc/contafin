import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { User } from '../Interfaces/User/user.model';

const BASE_URL = environment.apiBase + '/signup';

@Injectable()
export class SignUpService {

  constructor(private http: HttpClient) { }

  signup(userData: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(BASE_URL, userData, { headers })
  }

  private handleError(error: any) {
    console.error(error);
    return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
  }
}
