import { Injectable } from '@angular/core';
import { Student } from './student';
import { Course } from './course';
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class StudentService {
    private studentsUrl = 'http://localhost:8080/student';
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    constructor(
        private http: HttpClient
    ) { }
    getStudents(): Observable<Student[]> {
        return this.http.get<Student[]>(this.studentsUrl)
            .pipe(
                tap(_ => console.log('fetched students')),
                catchError(this.handleError<Student[]>('getStudents', []))
            );
    }
    getStudentsByJmbag(jmbag: String): Observable<Student> {
        return this.http.get<Student>(this.studentsUrl+'/?JMBAG='+jmbag)
          .pipe(
            tap(_ => console.log('fetched students')),
            catchError(this.handleError<Student>('getStudentsByJmbag', null))
          );
      }
      getStudentsByID(id: number): Observable<Student[]> {
        return this.http.get<Student[]>(this.studentsUrl+'/?ID='+id)
          .pipe(
            tap(_ => console.log('fetched students')),
            catchError(this.handleError<Student[]>('getStudentsByID', null))
          );
      }
    addStudent(student: Student): Observable<Student> {
        return this.http.post<Student>(this.studentsUrl, student, this.httpOptions).pipe(
            tap((newStudent: Student) => console.log(`added student w/ JMBAG=${newStudent.jmbag}`)),
            catchError(this.handleError<Student>('addStudent'))
        );
    }
    updateStudent(student: Student): Observable<any> {
        const url = `${this.studentsUrl}/${student.jmbag}`;
        return this.http.put(url, student, this.httpOptions).pipe(
            tap(_ => console.log(`updated student jmbag=${student.jmbag}`)),
            catchError(this.handleError<any>('updateStudent'))
        );
    }
    deleteStudent(student: Student | string): Observable<Student> {
        const jmbag = typeof student === 'string' ? student : student.jmbag;
        const url = `${this.studentsUrl}/${jmbag}`;
        return this.http.delete<Student>(url, this.httpOptions).pipe(
            tap(_ => console.log(`deleted student JMBAG=${jmbag}`)),
            catchError(this.handleError<Student>('deleteStudent'))
        );
    }
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(operation);
            console.error(error);
            return of(result as T);
        };
    }
}