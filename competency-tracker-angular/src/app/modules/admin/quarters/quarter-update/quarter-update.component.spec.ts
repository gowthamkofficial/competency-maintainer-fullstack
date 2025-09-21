import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuarterUpdateComponent } from './quarter-update.component';

describe('QuarterUpdateComponent', () => {
  let component: QuarterUpdateComponent;
  let fixture: ComponentFixture<QuarterUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuarterUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuarterUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
