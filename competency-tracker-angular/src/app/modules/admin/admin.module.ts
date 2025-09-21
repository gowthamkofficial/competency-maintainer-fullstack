import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { SessionModule } from './session/session.module';
import { DashboardModule } from './dashboard/dashboard.module';
import { UsersModule } from './users/users.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, AdminRoutingModule, SessionModule, DashboardModule,UsersModule],
})
export class AdminModule {}
