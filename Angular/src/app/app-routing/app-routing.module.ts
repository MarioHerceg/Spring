import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentsComponent } from '../students/students.component';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { CourseComponent } from '../course/course.component';
import { CourseDetailComponent } from '../course-detail/course-detail.component';

const routes: Routes = [
  { path: 'students', component: StudentsComponent },
  { path: 'detail/:jmbag', component: StudentDetailComponent },
  { path: 'courses', component: CourseComponent },
  { path: 'detailC/:id', component: CourseDetailComponent }
];
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
