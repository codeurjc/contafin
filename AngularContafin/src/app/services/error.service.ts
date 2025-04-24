import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';

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

    handleError(error: any) {
        console.error(error);
        throwError(error)
        return null;
    }
}
