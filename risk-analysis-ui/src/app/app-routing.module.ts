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
import { AuthGuard } from './service/auth.guard';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
   {path:"login",component:LoginComponent},
   {path:"sign-up",component:SignUpComponent},
   {path:"info",component:InfoComponent},
   {path:"",
   canActivate:[AuthGuard],
   component:HomeComponent},
   {path:"data",
   canActivate:[AuthGuard],
   component:DataComponent},
   {path:"trasaction",
   canActivate:[AuthGuard],
   component:TrasactionComponent},
   {path:"data/company",
   canActivate:[AuthGuard],
   component:CompanyScoreComponent},
   {path:"data/company/:companyId",
   canActivate:[AuthGuard],
   component:CompanyScoreComponent},
   {path:"data/weight",
   canActivate:[AuthGuard],
   component:WeightComponent},
   {path:"data/weight/:id",
   canActivate:[AuthGuard],
   component:WeightComponent},
   {path:"data/formula",
   canActivate:[AuthGuard],
   component:FormulaComponent},
   {path:"data/formula/:id",
   canActivate:[AuthGuard],
   component:FormulaComponent},
   {path:"data/risk-score-cap",
   canActivate:[AuthGuard],
   component:RiskScoreCapComponent},
   {path:"data/risk-score-cap/:id",
   canActivate:[AuthGuard],
   component:RiskScoreCapComponent},
   {path:"data/risk-score-level",
   canActivate:[AuthGuard],
   component:RiskScoreLevelComponent},
   {path:"data/risk-score-level/:id",
   canActivate:[AuthGuard],
   component:RiskScoreLevelComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
