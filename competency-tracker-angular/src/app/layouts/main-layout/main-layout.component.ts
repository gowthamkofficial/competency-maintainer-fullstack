import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MatDrawer } from '@angular/material/sidenav';
import Swal from 'sweetalert2';
import { DrawerService } from '../../core/services/drawer.service';
import { checkNull } from '../../core/helper/checknull';
import { environment } from '../../../environments/environment.development';
import { SharedModule } from '../../shared/shared.module';

@Component({
  selector: 'app-main-layout',
  imports: [CommonModule, RouterModule, SharedModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css',
})
export class MainLayoutComponent {
  userData: any;

  menuItems = [
    {
      label: 'Home',
      icon: 'home',
      route: '/blogs/list',
    },
    {
      label: 'My Blogs',
      icon: 'article',
      route: '/my-blogs',
    },
    {
      label: 'My Profile',
      icon: 'person',
      route: '/my-profile',
    },
  ];
  @ViewChild('rightDrawer') rightDrawer!: MatDrawer;
  constructor(public drawerService: DrawerService, private router: Router) {
    const storeUser: string | null = sessionStorage.getItem('SESSION_USER');
    const userData: any = storeUser
      ? JSON.parse(storeUser)
      : { firstName: 'Gowtham', lastName: 'K' };
    this.userData = userData;

    this.drawerService.drawer$.subscribe({
      next: (comp: String | null) => {
        if (checkNull(comp)) {
          this.rightDrawer.open();
        } else {
          this.rightDrawer.close();
        }
      },
    });
  }

  getImageURI(uri: string) {
    return checkNull(uri) ? environment.apiURL + uri : 'assets/images/user.png';
  }

  logout() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will be logged out of your session.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#6267a9',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, logout!',
    }).then((result) => {
      if (result.isConfirmed) {
        sessionStorage.clear();
        this.router.navigate(['/']);
      }
    });
  }
}
