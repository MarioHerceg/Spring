import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from "../course.service";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  courses: Course[];

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
  }

  find(numberOfECTS: number): void {

    if (!numberOfECTS) { return; }

    this.courseService.getCoursesByEcts(numberOfECTS).subscribe(course => this.courses=course);
  }

}
