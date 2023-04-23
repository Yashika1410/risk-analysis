import { Component, OnInit } from '@angular/core';
import {   FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoaderServiceService } from 'src/app/service/loader-service.service';
import { BackendApiService } from 'src/app/service/backend-api.service';

@Component({
  selector: 'app-company-score',
  templateUrl: './company-score.component.html',
  styleUrls: ['./company-score.component.css']
})
export class CompanyScoreComponent implements OnInit {
constructor(private route: ActivatedRoute, private service: BackendApiService,
  private fb: FormBuilder,private loadService:LoaderServiceService) { }
  public companyId!: number;
  resFlag=false
  
  companyFormGroup = this.fb.group({
    companyName: ['', Validators.required],
    companyId: [''],
    dimensionScores: this.fb.array([
      this.addDimensionScore()
    ])
  });
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()
    this.companyId = this.route.snapshot.params['companyId'];
    
    if (this.companyId != undefined){
      this.service.getCompanyRiskScore(this.companyId).subscribe({
        next:(res:any)=>{
        this.companyFormGroup.controls.companyId.setValue(res["companyId"])
        this.companyFormGroup.controls.companyName.setValue(res["companyName"])
        this.companyFormGroup.controls.dimensionScores.clear()
        res["dimensionScores"].forEach((element:any) => {
          this.companyFormGroup.controls.dimensionScores.push(this.addDimensionScoreWithData(element))
        });
        this.resFlag=true
      },
      error:(err)=>{alert(JSON.stringify(err.error));
      window.location.replace("/data")
      }
    });
  }
  this.loadService.offLoaderVisibility();
}
    addDimensionScore():FormGroup{
      return this.fb.group({
        id:[''],
        dimension:['',Validators.required],
        score:['',Validators.required]
      })
    }
    addDimensionScoreWithData(data:any):FormGroup{
      let fg= this.fb.group({
        id:[''],
        dimension:['',Validators.required],
        score:['',Validators.required]
      })
      fg.setValue(data);
      return fg;
    }
    addDimensionScoreForm(){
      (<FormArray><unknown>this.companyFormGroup.get('dimensionScores')).push(this.addDimensionScore());
    }
    removeDimensionScoreForm(id:any){
      (<FormArray><unknown>this.companyFormGroup.get('dimensionScores')).removeAt(id);
    }
  addCompanyData(){
    this.loadService.onLoaderVisibility();
    if (this.companyFormGroup.status === 'VALID') {
    this.service.createCompanyRiskScore(this.companyFormGroup.value).subscribe({
      next:(value)=>{alert(JSON.stringify({"message":"Successfully Created Company Data","data":value}));
    window.location.replace('/data');},
      error:(err)=>alert(JSON.stringify(err.error))
    })
    
  }else {
    if(this.companyFormGroup.controls.companyName.invalid){
      alert("Please enter Company Name")
    }
    else if(this.companyFormGroup.controls.dimensionScores.invalid){
     alert("Please enter dimension")
   }
 }
 this.loadService.offLoaderVisibility();
 }
  editCompanyData(){
    this.loadService.onLoaderVisibility()
    if (this.companyFormGroup.status === 'VALID') {
      this.service.editCompanyRiskScore(this.companyFormGroup.value,this.companyId).subscribe({
        next:(value)=>{alert(JSON.stringify({"message":"Successfully Updated Company Data","data":value}));
    window.location.replace('/data');
  },
      error:(err)=>alert(JSON.stringify(err.error))
      })
    }
    else {
      if(this.companyFormGroup.controls.companyName.invalid){
      alert("Please enter Company Name")
    }
    else if(this.companyFormGroup.controls.dimensionScores.invalid){
     alert("Please enter dimension")
   }
   }
   this.loadService.offLoaderVisibility()
   }
   get refDimensionScores(){
  return  this.companyFormGroup.get("dimensionScores") as unknown as FormArray;
  }
}
