import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoaderServiceService } from 'src/app/service/loader-service.service';
import { BackendApiService } from 'src/app/service/backend-api.service';

@Component({
  selector: 'app-risk-score-cap',
  templateUrl: './risk-score-cap.component.html',
  styleUrls: ['./risk-score-cap.component.css']
})
export class RiskScoreCapComponent implements OnInit {
constructor(private route: ActivatedRoute, private service: BackendApiService, private fb: FormBuilder,
  private loadService:LoaderServiceService) { }
  public id!: number;
  resFlag=false
  newRiskScoreCap = this.fb.group({
    conditionCnt: ['', Validators.required],
    cappedScore: ['', Validators.required],
    riskScoreLevelId:['',Validators.required]
  })
  existingRiskScoreCap = this.fb.group({
    id: [''],
    conditionCnt: ['', Validators.required],
    cappedScore: ['', Validators.required],
    riskScoreLevel:this.fb.group({
        id:[''],
        score:['',Validators.required],
        level:['',Validators.required]
      })
  })
  ngOnInit(): void {
    this.loadService.onLoaderVisibility();
    this.id = this.route.snapshot.params['id'];
    if (this.id != undefined){
      this.service.getRiskScoreCap(this.id).subscribe({
        next:(value:any)=>{ this.existingRiskScoreCap.setValue(value)
        this.resFlag=true
        console.log(value)
      },
        error:(err)=>{
          alert(JSON.stringify(err.error));
           window.location.replace("/data");
        }
      })
    }
    this.loadService.offLoaderVisibility();
  }
 
  addRiskScoreCap(){
    if (this.newRiskScoreCap.status === 'VALID') {
    this.service.createRiskScoreCap(this.newRiskScoreCap.value).subscribe({
      next:(value)=>{
        alert(JSON.stringify({"message":"Successfully Created Risk Score Cap Data","data":value}));
        window.location.replace('/data');
      },
      error:(err)=>alert(JSON.stringify(err.error))
    })
  }else {
  if(this.newRiskScoreCap.controls["conditionCnt"].invalid){
     alert("Please enter Condition Count")
   }
   else if (this.newRiskScoreCap.controls["cappedScore"].invalid){
     alert("Please enter Capped Score")
   }
   else if (this.newRiskScoreCap.controls["riskScoreLevelId"].invalid){
     alert("Please enter valid risk score level id")
   }
 }
 }
  editRiskScoreCap(){
    if (this.existingRiskScoreCap.status === 'VALID') {
      this.service.editRiskScoreCap(this.existingRiskScoreCap.value,this.id).subscribe({

     next:(value)=>{alert(JSON.stringify({"message":"Successfully Updated Risk Score Cap Data","data":value}));
    window.location.replace('/data');
  },
      error:(err)=>alert(JSON.stringify(err.error))
      })
    }
    else {
     if(this.existingRiskScoreCap.controls["conditionCnt"].invalid){
     alert("Please enter Condition Count")
   }
   else if (this.existingRiskScoreCap.controls["cappedScore"].invalid){
     alert("Please enter Capped Score")
   }
   else if (this.existingRiskScoreCap.controls["riskScoreLevel"].invalid){
     alert("Please enter valid risk score level")
   }
   }
   }
}
