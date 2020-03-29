import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllReimbursementsComponent } from './pages/all-reimbursements/all-reimbursements.component';
import { ReimbursementDetailComponent } from './pages/reimbursement-detail/reimbursement-detail.component';
import { EmployeeReimbursementComponent } from './pages/employee-reimbursement/employee-reimbursement.component';
import { AddReimbursementComponent } from './pages/add-reimbursement/add-reimbursement.component';
import { LoginComponent } from './pages/login/login.component';


const routes: Routes = [
  { path:'login', component: LoginComponent},
  { path:'allreimbursements', component: AllReimbursementsComponent },
  { path:'reimbdetail/:id', component: ReimbursementDetailComponent },
  { path:'employeeReimb/:id', component: EmployeeReimbursementComponent },
  { path:'addReimb/:id', component: AddReimbursementComponent },

  {
    path:'**',
    pathMatch: 'full',
    redirectTo: 'login'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
