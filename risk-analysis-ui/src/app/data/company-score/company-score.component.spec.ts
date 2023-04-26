import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyScoreComponent } from './company-score.component';

describe('CompanyScoreComponent', () => {
  let component: CompanyScoreComponent;
  let fixture: ComponentFixture<CompanyScoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyScoreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
