import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { DataComponent } from './data/data.component';
import { CompanyScoreComponent } from './data/company-score/company-score.component';
import { RiskScoreCapComponent } from './data/risk-score-cap/risk-score-cap.component';
import { RiskScoreLevelComponent } from './data/risk-score-level/risk-score-level.component';
import { WeightComponent } from './data/weight/weight.component';
import { FormulaComponent } from './data/formula/formula.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TrasactionComponent } from './trasaction/trasaction.component';
import { InfoComponent } from './info/info.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DataComponent,
    CompanyScoreComponent,
    RiskScoreCapComponent,
    RiskScoreLevelComponent,
    WeightComponent,
    FormulaComponent,
    TrasactionComponent,
    InfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }