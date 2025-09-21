import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoursesRoutingModule } from './courses-routing.module';
import { CourseListComponent } from './course-list/course-list.component';
import { CourseCreateComponent } from './course-create/course-create.component';
import { CourseUpdateComponent } from './course-update/course-update.component';
import { CourseViewComponent } from './course-view/course-view.component';


@NgModule({
  declarations: [
    CourseListComponent,
    CourseCreateComponent,
    CourseUpdateComponent,
    CourseViewComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule
  ]
})
export class CoursesModule { }
