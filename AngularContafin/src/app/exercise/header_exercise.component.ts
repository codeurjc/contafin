import {Component, Input} from '@angular/core';
import {NgbModal, ModalDismissReasons, NgbProgressbarModule} from '@ng-bootstrap/ng-bootstrap';
import {Router, ActivatedRoute} from '@angular/router';


@Component({
  selector: 'header_exercise',
  templateUrl: './header_exercise.component.html',
  styleUrls: ['../app.component.css']
})

export class HeaderExerciseComponent {

  @Input()
  a;

  progress2 = 100;

  closeResult: string;


  constructor(private router: Router, activatedRoute: ActivatedRoute, private modalService: NgbModal) {
    console.log("Progreso:" + this.a);
    
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
