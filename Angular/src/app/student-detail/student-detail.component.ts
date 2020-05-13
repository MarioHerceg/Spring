import { Component, OnInit, Input } from '@angular/core';
import { Student } from '../student';
import { Course } from '../course';
import { StudentService } from '../student.service'
import { CourseService } from '../course.service'
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  students: Student[];
  student: Student;
  courses: Course[];
  studentsUrl='http://localhost:8080/student';
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      
      console.log(params.jmbag);
      this.studentService.getStudentsByJmbag(params.jmbag).subscribe(students => this.student=students);
      this.courseService.getCoursesByJmbag(params.jmbag).subscribe(course => this.courses=course);
      console.log(this.student);
   })
}
}
