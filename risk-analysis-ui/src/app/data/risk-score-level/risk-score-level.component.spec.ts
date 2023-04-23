import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskScoreLevelComponent } from './risk-score-level.component';

describe('RiskScoreLevelComponent', () => {
  let component: RiskScoreLevelComponent;
  let fixture: ComponentFixture<RiskScoreLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RiskScoreLevelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RiskScoreLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
