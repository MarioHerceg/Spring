import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { Student } from '../student';
import { StudentService } from '../student.service'
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {

  students: Student[];
  courses: Course[];
  studentsUrl='http://localhost:8080/student';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      
      this.studentService.getStudentsByID(params.id).subscribe(student => this.students=student);
      
   })
  }

}
