import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RolesRoutingModule } from './roles-routing.module';
import { RoleCreateComponent } from './role-create/role-create.component';
import { RoleListComponent } from './role-list/role-list.component';
import { RoleUpdateComponent } from './role-update/role-update.component';
import { SharedModule } from '../../../shared/shared.module';


@NgModule({
  declarations: [
        RoleListComponent,
    RoleCreateComponent,
    RoleUpdateComponent
  ],
  imports: [
    CommonModule,
    RolesRoutingModule,
    SharedModule
  ]
})
export class RolesModule { }
