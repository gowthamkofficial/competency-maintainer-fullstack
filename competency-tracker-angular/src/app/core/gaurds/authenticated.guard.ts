import { CanActivateFn, Router } from '@angular/router';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const authenticatedGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (isPlatformBrowser(platformId)) {
    const isLoggedIn = sessionStorage.getItem('LOGGED_IN') === 'true';

    if (isLoggedIn) {
      router.navigate(['/blogs/list']); // redirect to blogs/list
      return false; // block current route since we redirected
    }
    return true; // allow navigation (e.g. login/register page)
  }

  // SSR side - default allow (or block, depending on your requirement)
  return true;
};
