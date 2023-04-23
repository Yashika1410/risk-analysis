import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoaderServiceService } from 'src/app/service/loader-service.service';
import { BackendApiService } from 'src/app/service/backend-api.service';

@Component({
  selector: 'app-risk-score-level',
  templateUrl: './risk-score-level.component.html',
  styleUrls: ['./risk-score-level.component.css']
})
export class RiskScoreLevelComponent implements OnInit {
 constructor(private route: ActivatedRoute, private service: BackendApiService, private fb: FormBuilder,private loadService:LoaderServiceService) { }
  public id!: number;
  resFlag=false
  resultInfo = this.fb.group({
    id: [''],
    score: ['', Validators.required],
    level: ['', Validators.required]
  })
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()
    this.id = this.route.snapshot.params['id'];
    if (this.id != undefined){
      this.service.getRiskScoreLevel(this.id).subscribe({
        next:(value:any)=>{ this.resultInfo.setValue(value)
        this.resFlag=true
      },
        error:(err)=>{
          alert(JSON.stringify(err.error));
           window.location.replace("/data");
        }
      })
    }
    this.loadService.offLoaderVisibility()
  }
 
  addRiskScoreLevel(){
    this.loadService.onLoaderVisibility()
    if (this.resultInfo.status === 'VALID') {
    this.service.createRiskScoreLevel(this.resultInfo.value).subscribe({
      next:(value)=>{
        alert(JSON.stringify({"message":"Successfully Created Risk Score Level Data","data":value}));
        window.location.replace('/data');
      },
      error:(err)=>alert(JSON.stringify(err.error))
    })
  }else {
   if(this.resultInfo.controls["score"].invalid){
     alert("Please enter Score")
   }
   else if (this.resultInfo.controls["level"].invalid){
     alert("Please enter Level")
   }
 }
 this.loadService.offLoaderVisibility()
 }
  editRiskScoreLevel(){
    this.loadService.onLoaderVisibility()
    if (this.resultInfo.status === 'VALID') {
      this.service.editRiskScoreLevel(this.resultInfo.value,this.id).subscribe({

     next:(value)=>{alert(JSON.stringify({"message":"Successfully Updated Risk Score Level Data","data":value}));
    window.location.replace('/data');
  },
      error:(err)=>alert(JSON.stringify(err.error))
      })
    }
    else {
     if(this.resultInfo.controls["score"].invalid){
     alert("Please enter Score")
   }
   else if (this.resultInfo.controls["level"].invalid){
     alert("Please enter Level")
   }
   }
   this.loadService.offLoaderVisibility()
   }
}
