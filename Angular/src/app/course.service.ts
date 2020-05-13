import { Injectable } from '@angular/core';
import { Course } from './course';
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CourseService {
    private coursesUrl = 'http://localhost:8080/course';
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    constructor(
        private http: HttpClient
    ) { }
      getCoursesByJmbag(jmbag: String): Observable<Course[]> {
        return this.http.get<Course[]>(this.coursesUrl+'/?JMBAG='+jmbag)
          .pipe(
            tap(_ => console.log('fetched courses')),
            catchError(this.handleError<Course[]>('getCoursesByJmbag', null))
          );
      }
      getCoursesByEcts(ects: Number): Observable<Course[]> {
        return this.http.get<Course[]>(this.coursesUrl+'/?ECTS='+ects)
          .pipe(
            tap(_ => console.log('fetched courses')),
            catchError(this.handleError<Course[]>('getCoursesByEcts', null))
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