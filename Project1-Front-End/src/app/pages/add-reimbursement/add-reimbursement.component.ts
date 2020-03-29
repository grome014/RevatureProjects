import { Component, OnInit, Input } from '@angular/core';
import { Reimbursement } from 'src/app/models/reimbursement';
import { ActivatedRoute } from '@angular/router';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { Location } from '@angular/common';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-add-reimbursement',
  templateUrl: './add-reimbursement.component.html',
  styleUrls: ['./add-reimbursement.component.css']
})
export class AddReimbursementComponent implements OnInit {

  @Input() reimb: Reimbursement;
  emp: Employee;

  constructor(
    private route: ActivatedRoute,
    private reimbService: ReimbursementService,
    private location: Location,
    private empService: EmployeeService
  ) { }

  ngOnInit(): void {
    this.initializeReimb();
    this.getEmp();
  }

  initializeReimb(): void {
    // this.reimb.reimb_id = 0;
    // this.reimb.reimb_amount = 0;
    // this.reimb.reimb_description = "";
    // this.reimb.reimb_type = "";
    this.reimb = new Reimbursement();
  }

  getEmp(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
    this.empService.getEmployee(id).subscribe(emp => {this.emp = emp});
  }

  goBack(): void {
    this.location.back();
  }

  addReimb(): void {
    console.log(this.emp);
    this.reimb.reimb_author = this.emp;
    this.reimb.reimb_submitted = new Date();
    this.reimb.reimb_status = "PENDING";
    console.log(this.reimb);
    this.reimbService.insertReimb(this.reimb).subscribe(() => this.goBack());
  }

}
