import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DrawerService {
  private currentDrawer: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);
  public drawer$: Observable<string | null> = this.currentDrawer.asObservable();

  private currentBlog: BehaviorSubject<number | null> = new BehaviorSubject<number | null>(null);
  public currentBlog$: Observable<number | null> = this.currentBlog.asObservable();

  constructor() {}

  // Drawer controls
  openDrawer(componentName: string) {
    this.currentDrawer.next(componentName);
  }

  closeDrawer() {
    this.currentDrawer.next(null);
  }

  // Blog ID controls
  setBlogId(id: number) {
    this.currentBlog.next(id);
  }

  clearBlogId() {
    this.currentBlog.next(null);
  }
}
