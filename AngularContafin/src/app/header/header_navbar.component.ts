import { Component, Input } from '@angular/core';

@Component({
    selector: 'header_navbar',
    templateUrl:
        './header_navbar.component.html'
})
export class HeaderNavbarComponent {
    
    @Input()
    private user: boolean;

    @Input()
    private admin: boolean;
 }