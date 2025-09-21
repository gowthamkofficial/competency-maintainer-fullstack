import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuartersRoutingModule } from './quarters-routing.module';
import { QuarterListComponent } from './quarter-list/quarter-list.component';
import { QuarterCreateComponent } from './quarter-create/quarter-create.component';
import { QuarterUpdateComponent } from './quarter-update/quarter-update.component';
import { QuarterViewComponent } from './quarter-view/quarter-view.component';


@NgModule({
  declarations: [
    QuarterListComponent,
    QuarterCreateComponent,
    QuarterUpdateComponent,
    QuarterViewComponent
  ],
  imports: [
    CommonModule,
    QuartersRoutingModule
  ]
})
export class QuartersModule { }
