import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

const BASE_URL = 'https://localhost:8080/api/User';


@Injectable()
export class UserService {

    constructor(private http: Http) {

    }

    getProgress(id: number): Observable<number[]> {
        return this.http.get(BASE_URL + "/" + id + "/Progress").map(
            response => response.json()
        );
    }

}