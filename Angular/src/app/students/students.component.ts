import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { StudentService } from "../student.service";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})

export class StudentsComponent implements OnInit {
  students: Student[];
  selectedStudent: Student;
  constructor(private studentService: StudentService) { }
  ngOnInit(): void {
    this.getStudents();
  }
  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(students => this.students = students);
  }
  onSelect(student: Student): void {
    this.selectedStudent = student;
  }
  add(firstName: string, lastName: string, jmbag: string, numberOfECTS: number): void {
    firstName = firstName.trim();
    lastName = lastName.trim();
    jmbag = jmbag.trim();

    if (!firstName || !lastName || !jmbag || !numberOfECTS) { return; }
    
    const dateOfBirth = new String("23.02.1999.")
    console.log(dateOfBirth);

    this.studentService.addStudent({ firstName, lastName, jmbag, numberOfECTS, dateOfBirth } as Student)
      .subscribe(student => {
        this.students.push(student);
      });
  }

  delete(student: Student): void {
    if (!student) { return; }
    this.studentService.deleteStudent(student)
      .subscribe(s => {
        console.log(this.students.indexOf(student), 1);
        this.students.splice(this.students.indexOf(student), 1);
        console.log(this.students);
      });
  }
}
