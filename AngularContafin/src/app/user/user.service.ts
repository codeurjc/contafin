import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

const BASE_URL = 'https://localhost:8080/api/User';


@Injectable()
export class UserService {

    constructor(private http: Http) {

    }

    getProgress(id: number): Observable<number[]> {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.get(BASE_URL + '/' + id + '/Progress', options)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

}