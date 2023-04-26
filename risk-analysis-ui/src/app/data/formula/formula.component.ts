import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoaderServiceService } from 'src/app/service/loader-service.service';
import { BackendApiService } from 'src/app/service/backend-api.service';

@Component({
  selector: 'app-formula',
  templateUrl: './formula.component.html',
  styleUrls: ['./formula.component.css']
})
export class FormulaComponent implements OnInit {
  constructor(private route: ActivatedRoute, private service: BackendApiService,
    private fb: FormBuilder,private loadService:LoaderServiceService) { }
  public id!: number;
  resFlag=false
  resultInfo = this.fb.group({
    id: [''],
    entityName: ['', Validators.required],
    formula: ['', Validators.required]
  })
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()
    this.id = this.route.snapshot.params['id'];
    if (this.id != undefined){
      this.service.getFormula(this.id).subscribe({
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
 
  addFormula(){
    if (this.resultInfo.status === 'VALID') {
    this.service.createFormula(this.resultInfo.value).subscribe({
      next:(value)=>{
        alert(JSON.stringify({"message":"Successfully Created Formula Data","data":value}));
        window.location.replace('/data');
      },
      error:(err)=>alert(JSON.stringify(err.error))
    })
  }else {
   if(this.resultInfo.controls["entityName"].invalid){
     alert("Please enter Entity Name")
   }
   else if (this.resultInfo.controls["formula"].invalid){
     alert("Please enter Formula")
   }
 }
 }
  editFormula(){
    if (this.resultInfo.status === 'VALID') {
      this.service.editFormula(this.resultInfo.value,this.id).subscribe({

     next:(value)=>{alert(JSON.stringify({"message":"Successfully Updated Formula Data","data":value}));
    window.location.replace('/data');
  },
      error:(err)=>alert(JSON.stringify(err.error))
      })
    }
    else {
     if(this.resultInfo.controls["entityName"].invalid){
     alert("Please enter Entity Name")
   }
   else if (this.resultInfo.controls["formula"].invalid){
     alert("Please enter Formula")
   }
   }
   }

}
