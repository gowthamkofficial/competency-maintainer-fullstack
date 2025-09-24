import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLayoutComponent } from '../../layouts/auth-layout/auth-layout.component';
import { MainLayoutComponent } from '../../layouts/main-layout/main-layout.component';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: AuthLayoutComponent,
    loadChildren: () =>
      import('./session/session.module').then((m) => m.SessionModule),
  },
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'users',
        loadChildren: () =>
          import('./users/users.module').then((m) => m.UsersModule),
      },
      {
        path: 'roles',
        loadChildren: () =>
          import('./roles/roles.module').then((m) => m.RolesModule),
      },
      {
        path: 'departments',
        loadChildren: () =>
          import('./departments/departments.module').then(
            (m) => m.DepartmentsModule
          ),
      },
      {
        path: 'quarters',
        loadChildren: () =>
          import('./quarters/quarters.module').then((m) => m.QuartersModule),
      },
      {
        path: 'courses',
        loadChildren: () =>
          import('./courses/courses.module').then((m) => m.CoursesModule),
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
