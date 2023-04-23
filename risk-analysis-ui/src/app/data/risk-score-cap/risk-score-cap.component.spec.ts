import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskScoreCapComponent } from './risk-score-cap.component';

describe('RiskScoreCapComponent', () => {
  let component: RiskScoreCapComponent;
  let fixture: ComponentFixture<RiskScoreCapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RiskScoreCapComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RiskScoreCapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
