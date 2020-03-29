import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Reimbursement } from 'src/app/models/reimbursement';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee.service';
import { Employee } from 'src/app/models/employee';
import { Location } from '@angular/common';


@Component({
  selector: 'app-employee-reimbursement',
  templateUrl: './employee-reimbursement.component.html',
  styleUrls: ['./employee-reimbursement.component.css']
})
export class EmployeeReimbursementComponent implements OnInit {

  reimbursementObs: Observable<Reimbursement[]>;
  employee: Employee;

  constructor(private rs: ReimbursementService,
    private route: ActivatedRoute,
    private es: EmployeeService,
    private location: Location) { }

  ngOnInit(): void {
    this.getEmployee();
  }

  getEmployee(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.es.getEmployee(id).subscribe(employee => {this.employee = employee;
      this.reimbursementObs = this.rs.reimbOfEmployee(this.employee.id);
    });
  }

}
