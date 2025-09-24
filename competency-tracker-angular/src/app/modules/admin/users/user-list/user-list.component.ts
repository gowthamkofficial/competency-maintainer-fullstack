import { Component } from '@angular/core';

@Component({
  selector: 'app-user-list',
  standalone: false,
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {


   employees = [
    {
      id: 1,
      empcode: 'EMP001',
      firstName: 'John',
      lastName: 'Doe',
      email: 'john.doe@example.com',
      mobileNumber: '+91 9876543210',
      dateOfJoining: '2025-09-23',
      addressLine1: 'Street 123',
      addressLine2: 'Apt 45',
      stateName: 'Karnataka',
      districtName: 'Bangalore',
      roleName: 'Developer',
      departmentName: 'IT',
      profileImagePath: 'https://via.placeholder.com/48'
    },
    {
      id: 2,
      empcode: 'EMP002',
      firstName: 'Jane',
      lastName: 'Smith',
      email: 'jane.smith@example.com',
      mobileNumber: '+91 9123456789',
      dateOfJoining: '2024-06-15',
      addressLine1: 'Main Road',
      addressLine2: 'Flat 10',
      stateName: 'Maharashtra',
      districtName: 'Mumbai',
      roleName: 'Manager',
      departmentName: 'HR',
      profileImagePath: 'https://via.placeholder.com/48'
    }
  ];
}
