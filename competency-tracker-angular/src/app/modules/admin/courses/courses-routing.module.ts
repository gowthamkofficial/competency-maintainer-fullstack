import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseListComponent } from './course-list/course-list.component';
import { CourseViewComponent } from './course-view/course-view.component';
import { CourseCreateComponent } from './course-create/course-create.component';

const routes: Routes = [

  {
    path: '',
    component: CourseListComponent
  }, {
    path: 'create',
    component: CourseCreateComponent
  },
  {
    path: 'view',
    component: CourseViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoursesRoutingModule { }
