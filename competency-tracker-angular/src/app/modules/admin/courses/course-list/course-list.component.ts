import { Component } from '@angular/core';
interface Course {
  id: number;
  courseName: string;
  courseDescription: string;
  courseDurationInHours: number;
  paidCourse: boolean;
  amountPaid: number;
  courseStartedOn: string;
  courseCompletedOn: string;
  status: string;
  level: string;
  userName: string;
  quarterName: string;
  certificateName: string;
  certificateDownloadUrl: string;
}
@Component({
  selector: 'app-course-list',
  standalone: false,
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent {
courses: Course[] = [
    {
      id: 1,
      courseName: 'Introduction to Programming',
      courseDescription: 'Basics of programming concepts',
      courseDurationInHours: 10,
      paidCourse: true,
      amountPaid: 50,
      courseStartedOn: '2025-09-23',
      courseCompletedOn: '2025-10-01',
      status: 'NOT_YET_STARTED',
      level: 'BEGINNER',
      userName: 'John Doe',
      quarterName: 'Q1 2025',
      certificateName: 'Intro Certificate',
      certificateDownloadUrl: '#'
    },
    {
      id: 2,
      courseName: 'Advanced Angular',
      courseDescription: 'Deep dive into Angular',
      courseDurationInHours: 20,
      paidCourse: false,
      amountPaid: 0,
      courseStartedOn: '2025-09-25',
      courseCompletedOn: '2025-10-10',
      status: 'IN_PROGRESS',
      level: 'ADVANCED',
      userName: 'Jane Smith',
      quarterName: 'Q2 2025',
      certificateName: 'Angular Certificate',
      certificateDownloadUrl: '#'
    }
  ];
}