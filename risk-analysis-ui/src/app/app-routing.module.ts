import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DataComponent } from './data/data.component';
import { CompanyScoreComponent } from './data/company-score/company-score.component';
import { WeightComponent } from './data/weight/weight.component';
import { FormulaComponent } from './data/formula/formula.component';
import { RiskScoreCapComponent } from './data/risk-score-cap/risk-score-cap.component';
import { RiskScoreLevelComponent } from './data/risk-score-level/risk-score-level.component';
import { TrasactionComponent } from './trasaction/trasaction.component';
import { InfoComponent } from './info/info.component';

const routes: Routes = [
   {path:"",component:HomeComponent},
   {path:"data",component:DataComponent},
   {path:"info",component:InfoComponent},
   {path:"trasaction",component:TrasactionComponent},
   {path:"data/company",component:CompanyScoreComponent},
   {path:"data/company/:companyId",component:CompanyScoreComponent},
   {path:"data/weight",component:WeightComponent},
   {path:"data/weight/:id",component:WeightComponent},
   {path:"data/formula",component:FormulaComponent},
   {path:"data/formula/:id",component:FormulaComponent},
   {path:"data/risk-score-cap",component:RiskScoreCapComponent},
   {path:"data/risk-score-cap/:id",component:RiskScoreCapComponent},
   {path:"data/risk-score-level",component:RiskScoreLevelComponent},
   {path:"data/risk-score-level/:id",component:RiskScoreLevelComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
