import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartmentsRoutingModule } from './departments-routing.module';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentCreateComponent } from './department-create/department-create.component';
import { DepartmentUpdateComponent } from './department-update/department-update.component';

@NgModule({
  declarations: [
    DepartmentListComponent,
    DepartmentCreateComponent,
    DepartmentUpdateComponent,
  ],
  imports: [CommonModule, DepartmentsRoutingModule],
})
export class DepartmentsModule {}
