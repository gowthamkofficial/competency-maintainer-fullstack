import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuarterListComponent } from './quarter-list/quarter-list.component';
import { QuarterCreateComponent } from './quarter-create/quarter-create.component';
import { QuarterUpdateComponent } from './quarter-update/quarter-update.component';
import { QuarterViewComponent } from './quarter-view/quarter-view.component';

const routes: Routes = [
  {
    path: '',
    component: QuarterListComponent
  },
  {
    path: 'create',
    component: QuarterCreateComponent
  },
  {
    path: 'edit/:id',
    component: QuarterUpdateComponent
  },
  {
    path: 'view/:id',
    component: QuarterViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuartersRoutingModule { }
