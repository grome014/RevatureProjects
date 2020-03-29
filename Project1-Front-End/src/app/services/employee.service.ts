import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from '../message.service';
import { Employee } from '../models/employee';
import { Login } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private empUrl = 'http://localhost:8080/Project1/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getLogin(login: Login): Observable<Employee> {
    return this.http.post<Employee>(`${this.empUrl}login`, login, this.httpOptions).pipe(
      tap((newEmp: Employee) => this.log(`sent login ingo w/ id=${newEmp.id}`)),
      catchError(this.handleError<Employee>('login'))
    );
  }

  getEmployee (id: number): Observable<Employee> {
    const url = `${this.empUrl}getemployee/${id}`;
    return this.http.get<Employee>(url).pipe(
      tap(_ => this.log(`fetched employee id=${id}`)),
      catchError(this.handleError<Employee>(`getEmployee id=${id}`))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`EmployeeService: ${message}`);
  }
}
