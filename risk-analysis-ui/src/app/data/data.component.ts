import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendApiService } from '../service/backend-api.service';
import { AppComponent } from '../app.component';
import { LoaderServiceService } from '../service/loader-service.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {
companyResults=[]
  weightResults=[]
  formulasResults=[]
  riskScoreCapResults=[]
  riskScoreLevelResults=[]
  colList=new Set<string>();
  Object = Object;
  constructor(private service:BackendApiService,private route:Router,private loadService:LoaderServiceService) { }

  ngOnInit(): void {
    this.loadService.onLoaderVisibility();
    this.service.getCompanyRiskScoreList().subscribe((res:any)=>{
      res.forEach((value:any)=>{
    let obj={}
    value["dimensionScores"].forEach((scores:any)=>{
      Object.assign(obj,{ [scores["dimension"]]: scores["score"]})
      this.colList.add(scores["dimension"])
    })

    Object.assign(value,{"scores":obj})
    })
    this.companyResults=res
    })
    this.service.getWeightList().subscribe((res:any)=>{
      this.weightResults=res
    })
    this.service.getFormulaList().subscribe((res:any)=>{
      this.formulasResults=res
    })
    this.service.getRiskScoreCapList().subscribe((res:any)=>{
      this.riskScoreCapResults=res
    })
    this.service.getRiskScoreLevelList().subscribe((res:any)=>{
      this.riskScoreLevelResults=res
    })
    this.loadService.offLoaderVisibility();

    
  }
  click(id:any){
    console.log(id)
  }
  deleteCompanyRiskScore(id:number){
    this.service.deleteCompanyRiskScore(id).subscribe({
      next:(value)=>{
        alert(value);
        console.log(value);
        window.location.reload()
      },
      error:(err)=>{
        alert(err.error);
        console.log(err);
        window.location.reload()
      }
    })
  }
  deleteWeight(id:number){

    this.service.deleteWeight(id).subscribe({
      next:(value)=>{
        alert(value);
        console.log(value);
        window.location.reload()
      },
      error:(err)=>{
        alert(err.error);
        console.log(err);
        window.location.reload()
      }
    })
    ;
  }
  deleteFormula(id:number){

    this.service.deleteFormula(id).subscribe({
      next:(value)=>{
        alert(value);
        console.log(value);
        window.location.reload()
      },
      error:(err)=>{
        alert(err.error);
        console.log(err);
        window.location.reload()
      }
    })
    ;
  }
  deleteRiskScoreCap(id:number){

    this.service.deleteRiskScoreCap(id).subscribe({
      next:(value)=>{
        alert(value);
        console.log(value);
        window.location.reload()
      },
      error:(err)=>{
        alert(err.error);
        console.log(err);
        window.location.reload()
      }
    })
  }
  deleteRiskScoreLevel(id:number){
    this.service.deleteRiskScoreLevel(id).subscribe({
      next:(value)=>{
        alert(value);
        console.log(value);
        window.location.reload()
      },
      error:(err)=>{
        alert(err.error);
        console.log(err);
        window.location.reload()
      }
    })
    

  }
}
