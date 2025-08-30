import { CanActivateFn, Router } from '@angular/router';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const authGaurd: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  // Only check sessionStorage in browser
  if (isPlatformBrowser(platformId)) {
    const isLoggedIn = sessionStorage.getItem('LOGGED_IN') === 'true';

    if (isLoggedIn) {
      return true;
    } else {
      router.navigate(['/session/login']);
      return false;
    }
  }

  // On server, always block (or allow, depending on requirement)
  return false;
};
