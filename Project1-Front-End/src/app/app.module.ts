import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllReimbursementsComponent } from './pages/all-reimbursements/all-reimbursements.component';
import { HttpClientModule } from '@angular/common/http';
import { ReimbursementDetailComponent } from './pages/reimbursement-detail/reimbursement-detail.component';
import { TableFilterPipe } from './table-filter.pipe';

import { FormsModule } from '@angular/forms';
import { EmployeeReimbursementComponent } from './pages/employee-reimbursement/employee-reimbursement.component';
import { AddReimbursementComponent } from './pages/add-reimbursement/add-reimbursement.component';
import { LoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AllReimbursementsComponent,
    ReimbursementDetailComponent,
    TableFilterPipe,
    EmployeeReimbursementComponent,
    AddReimbursementComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TableFilterPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
