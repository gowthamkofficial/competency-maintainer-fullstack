import { Component } from '@angular/core';

@Component({
  selector: 'app-quarter-list',
  standalone: false,
  templateUrl: './quarter-list.component.html',
  styleUrl: './quarter-list.component.css'
})
export class QuarterListComponent {
quarters = [
    {
      id: 1,
      quarterName: 'Q1 2025',
      description: 'First quarter of 2025',
      fromDate: '2025-01-01',
      toDate: '2025-03-31',
      status: 'Active',
      createdOn: '2025-09-23T06:07:33.350Z',
      updatedOn: '2025-09-23T06:07:33.350Z'
    },
    {
      id: 2,
      quarterName: 'Q2 2025',
      description: 'Second quarter of 2025',
      fromDate: '2025-04-01',
      toDate: '2025-06-30',
      status: 'Inactive',
      createdOn: '2025-09-20T08:15:36.593Z',
      updatedOn: '2025-09-22T09:10:36.593Z'
    }
  ];

  // Pagination mock
  page = 0;
  totalPages = 2;
  last = false;

  nextPage() {
    if (!this.last) {
      this.page++;
      // Load next page from API here
    }
  }

  prevPage() {
    if (this.page > 0) {
      this.page--;
      // Load previous page from API here
    }
  }
}
