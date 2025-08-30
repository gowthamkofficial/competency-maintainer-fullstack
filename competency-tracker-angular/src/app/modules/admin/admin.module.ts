import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { SessionModule } from './session/session.module';
import { DashboardModule } from './dashboard/dashboard.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, AdminRoutingModule, SessionModule, DashboardModule],
})
export class AdminModule {}
