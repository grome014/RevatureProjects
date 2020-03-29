import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Reimbursement } from 'src/app/models/reimbursement';
import { ReimbursementService } from 'src/app/services/reimbursement.service';

@Component({
  selector: 'app-reimbursement-detail',
  templateUrl: './reimbursement-detail.component.html',
  styleUrls: ['./reimbursement-detail.component.css']
})
export class ReimbursementDetailComponent implements OnInit {
  @Input() reimb: Reimbursement;
  PENDING: string = "PENDING";

  constructor(
    private route: ActivatedRoute,
    private reimbService: ReimbursementService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getReimb();
  }

  getReimb(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.reimbService.getReimb(id).subscribe(reimb => this.reimb = reimb);
  }

  approve(): void {
    this.reimb.reimb_resolved = new Date();
    this.reimb.reimb_status = "APPROVED";
    console.log(this.reimb);
    this.reimbService.updateReimb(this.reimb).subscribe(() => this.goBack());
  }

  decline(): void {
    this.reimb.reimb_resolved = new Date();
    this.reimb.reimb_status = "DENIED";
    this.reimbService.updateReimb(this.reimb).subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

}
