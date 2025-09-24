import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./modules/admin/admin.module').then((m) => m.AdminModule),
  },

  {
    path: '**',
    redirectTo: '',
  },
];
