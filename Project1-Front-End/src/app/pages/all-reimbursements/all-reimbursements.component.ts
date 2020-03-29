import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Reimbursement } from 'src/app/models/reimbursement';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-reimbursements',
  templateUrl: './all-reimbursements.component.html',
  styleUrls: ['./all-reimbursements.component.css']
})
export class AllReimbursementsComponent implements OnInit {

  reimbursementObs: Observable<Reimbursement[]>;
  selectedReimb: Reimbursement;
  status: string;

  constructor(private rs: ReimbursementService, private router: Router) { }

  ngOnInit(): void {
    this.reimbursementObs = this.rs.findAll();
  }

  goToDetails(id: number): void {
    console.log(id);
    this.router.navigateByUrl("/reimbdetail/" + id);
  }

}
