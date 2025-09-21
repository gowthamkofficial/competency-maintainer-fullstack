import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuarterCreateComponent } from './quarter-create.component';

describe('QuarterCreateComponent', () => {
  let component: QuarterCreateComponent;
  let fixture: ComponentFixture<QuarterCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuarterCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuarterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
