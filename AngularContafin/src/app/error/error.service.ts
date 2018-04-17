import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ErrorService {
    message: string = "The web page doesn't exist or you don't have permission.";
    constructor() { }

    setMessage(message: string) {
        this.message = message;
    }
    getMessage() {
        return this.message;
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}
