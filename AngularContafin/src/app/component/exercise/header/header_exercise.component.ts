import {Component, Input} from '@angular/core';
import {NgbModal, ModalDismissReasons, NgbProgressbarModule} from '@ng-bootstrap/ng-bootstrap';
import {Router, ActivatedRoute} from '@angular/router';


@Component({
  selector: 'header_exercise',
  templateUrl: './header_exercise.component.html',
  styleUrls: ['../../app.component.css']
})

export class HeaderExerciseComponent {

  @Input()
  complete_bar;

  progress2 = 100;

  closeResult: string;


  constructor(private router: Router, private modalService: NgbModal) {
    
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
