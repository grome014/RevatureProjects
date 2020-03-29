import { Component, OnInit, Input } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { Login } from 'src/app/models/login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Input() login: Login;

  employee: Employee;

  constructor(private empService: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.login = new Login();
  }

  loginEmp(): void {
    this.empService.getLogin(this.login).subscribe(employee => {this.employee = employee;
      console.log(this.employee);
      this.verifyRole(this.employee);
      
    });
  }

  verifyRole(emp: Employee): void {
    if(this.employee.role === "EMPLOYEE") {
      this.router.navigateByUrl(`/employeeReimb/${this.employee.id}`);
    }
    else if(this.employee.role === "MANAGER") {
      this.router.navigateByUrl("/allreimbursements");
    }
    else {
      this.router.navigateByUrl("/login");
    }
  }

}
