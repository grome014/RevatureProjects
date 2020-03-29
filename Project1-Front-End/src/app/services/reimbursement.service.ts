import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Reimbursement } from '../models/reimbursement';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from '../message.service';


@Injectable({
  providedIn: 'root'
})
export class ReimbursementService {

  private reimbUrl = 'http://localhost:8080/Project1/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }

  findAll(): Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(`${this.reimbUrl}reimbursement`);
  }

  insertReimb (reimb: Reimbursement): Observable<Reimbursement> {
    return this.http.post<Reimbursement>(`${this.reimbUrl}insertreimbursement`, reimb, this.httpOptions).pipe(
      tap((newReimb: Reimbursement) => this.log(`added reimbursement w/ id=${newReimb.reimb_id}`)),
      catchError(this.handleError<Reimbursement>('inserReimb'))
    );
  }

  updateReimb (reimb: Reimbursement): Observable<Reimbursement> {
    return this.http.post<Reimbursement>(`${this.reimbUrl}updatereimbursement`, reimb, this.httpOptions).pipe(
      tap((newReimb: Reimbursement) => this.log(`added reimbursement w/ id=${newReimb.reimb_id}`)),
      catchError(this.handleError<Reimbursement>('updateReimb'))
    );
  }

  getReimb (id: number): Observable<Reimbursement> {
    const url = `${this.reimbUrl}getreimbursement/${id}`;
    return this.http.get<Reimbursement>(url).pipe(
      tap(_ => this.log(`fetched reimb id=${id}`)),
      catchError(this.handleError<Reimbursement>(`getReimb id=${id}`))
    );
  }

  reimbOfEmployee(empId: number): Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(`${this.reimbUrl}employeereimbursement/${empId}`);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`ReimbService: ${message}`);
  }
}
