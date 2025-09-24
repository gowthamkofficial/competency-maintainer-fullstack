import { Component } from '@angular/core';

@Component({
  selector: 'app-department-list',
  standalone: false,
  templateUrl: './department-list.component.html',
  styleUrl: './department-list.component.css'
})
export class DepartmentListComponent {
departments = [
    {
      departmentId: 1,
      departmentName: 'Human Resources',
      createdOn: '2025-09-23T06:04:36.593Z',
      updatedOn: '2025-09-23T06:04:36.593Z'
    },
    {
      departmentId: 2,
      departmentName: 'Finance',
      createdOn: '2025-09-20T08:15:36.593Z',
      updatedOn: '2025-09-22T09:10:36.593Z'
    },
    {
      departmentId: 3,
      departmentName: 'IT',
      createdOn: '2025-09-10T11:30:36.593Z',
      updatedOn: '2025-09-18T12:45:36.593Z'
    }
  ];
}
