import { CommonModule } from '@angular/common';
import { Component, ViewChild, OnInit } from '@angular/core';
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
  styleUrls: ['./main-layout.component.css'],
})
export class MainLayoutComponent implements OnInit {
  userData: any;
  menuItems: any[] = [];
  @ViewChild('rightDrawer') rightDrawer!: MatDrawer;

  constructor(public drawerService: DrawerService, private router: Router) {
    const storeUser: string | null = sessionStorage.getItem('SESSION_USER');
    this.userData = storeUser
      ? JSON.parse(storeUser)
      : { firstName: 'Gowtham', lastName: 'K' };

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

  ngOnInit() {
    this.setMenuItems(this.router.url);

    // Optional: update menu when route changes
    this.router.events.subscribe(() => {
      this.setMenuItems(this.router.url);
    });
  }

  setMenuItems(url: string) {
    if (url.startsWith('/employee/')) {
      this.menuItems = [
        { label: 'Dashboard', icon: 'dashboard', route: '/employee/dashboard' },
        { label: 'Quarters', icon: 'calendar_view_month', route: '/employee/quarters' },
        { label: 'Courses', icon: 'menu_book', route: '/employee/courses' },
        { label: 'My Profile', icon: 'person', route: '/employee/my-profile' },
      ];
    } else {
      this.menuItems = [
        { label: 'Dashboard', icon: 'dashboard', route: '/dashboard' },
        { label: 'Users', icon: 'group', route: '/users' },
        { label: 'Quarters', icon: 'calendar_view_month', route: '/quarters' },
        { label: 'Courses', icon: 'menu_book', route: '/courses' },
        { label: 'Role', icon: 'admin_panel_settings', route: '/roles' },
        { label: 'Department', icon: 'apartment', route: '/departments' },
      ];
    }
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
