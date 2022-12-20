import {Component} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Router, ActivatedRoute} from '@angular/router';


@Component({
  selector: 'header_exercise',
  templateUrl: './header_exercise.component.html',
  styleUrls: ['../app.component.css']
})

export class HeaderExerciseComponent {

  public progress: number;

  closeResult: string;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private modalService: NgbModal) {
    this.progress = 10;
  }


  open(modal) {
    this.modalService.open(modal).result.then((result) => {
      this.closeResult = '${result}';
      if (result=='Exit click') {
        this.goHome();
      }
    }, (reason) => {
      this.closeResult = '$[reason}';
    });
  }

  goHome(){
    this.router.navigate(['/home']);
  }

}
