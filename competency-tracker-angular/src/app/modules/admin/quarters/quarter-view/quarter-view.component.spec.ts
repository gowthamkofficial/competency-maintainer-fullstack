import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuarterViewComponent } from './quarter-view.component';

describe('QuarterViewComponent', () => {
  let component: QuarterViewComponent;
  let fixture: ComponentFixture<QuarterViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuarterViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuarterViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
