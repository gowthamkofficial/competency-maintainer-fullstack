import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RoleCreateComponent } from '../role-create/role-create.component';

@Component({
  selector: 'app-role-list',
  standalone: false,
  templateUrl: './role-list.component.html',
  styleUrl: './role-list.component.css',
})
export class RoleListComponent {
  constructor(private dialog: MatDialog) {}

  openCreateDialog() {
    const dialogRef = this.dialog.open(RoleCreateComponent, {
      width: '400px',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('Role created:', result);
      }
    });
  }
  roles = [
    {
      roleId: 1,
      role: 'Admin',
      createdOn: '2025-09-23T06:01:49.832Z',
      updatedOn: '2025-09-23T06:01:49.832Z',
    },
    {
      roleId: 2,
      role: 'Manager',
      createdOn: '2025-09-20T06:01:49.832Z',
      updatedOn: '2025-09-22T06:01:49.832Z',
    },
    {
      roleId: 3,
      role: 'Employee',
      createdOn: '2025-09-10T06:01:49.832Z',
      updatedOn: '2025-09-18T06:01:49.832Z',
    },
  ];
}
