import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'body_index',
    templateUrl:
        './body_index.component.html'
})
export class BodyIndexComponent {
    [x: string]: any;
    closeResult: string;
    kind = '2';
    

  constructor(private modalService: NgbModal) {}

  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  checkIn(event: any,name: string, email: string, pass: string) {

    //Aqui te registras teniendo los parametros name email y pass

    event.preventDefault();

    this.loginService.logIn(email, pass).subscribe(
      user => {
        console.log(user);
        this.router.navigate(['/Home']);
      },
      error => alert('Invalid user or password')
    );
  }
 }