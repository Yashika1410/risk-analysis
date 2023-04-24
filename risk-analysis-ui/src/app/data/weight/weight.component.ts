import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoaderServiceService } from 'src/app/service/loader-service.service';
import { BackendApiService } from 'src/app/service/backend-api.service';

@Component({
  selector: 'app-weight',
  templateUrl: './weight.component.html',
  styleUrls: ['./weight.component.css']
})
export class WeightComponent implements OnInit {
  constructor(private route: ActivatedRoute, private service: BackendApiService, private fb: FormBuilder,private loadService:LoaderServiceService) { }
public id!: number;
  resFlag=false
  resultInfo = this.fb.group({
    id: [''],
    dimension: ['', Validators.required],
    weight: ['', Validators.required],
  })
  ngOnInit(): void {
    this.loadService.onLoaderVisibility();
    this.id = this.route.snapshot.params['id'];
    if (this.id != undefined){
      this.service.getWeight(this.id).subscribe({
        next:(value:any)=>{ 
        this.resultInfo.setValue(value)
        this.resFlag=true
      },
        error:(err)=>{
          alert(JSON.stringify(err.error));
           window.location.replace("/data");
        }
      })
    }
    this.loadService.offLoaderVisibility();
  }
 
  addWeight(){
    if (this.resultInfo.status === 'VALID') {
    this.service.createWeight(this.resultInfo.value).subscribe(
      {
      next:(value)=>{
        alert(JSON.stringify({"message":"Successfully Created Weight Data","data":value}));
        window.location.replace('/data');
      },
      error:(err)=>alert(JSON.stringify(err.error))
    }
    )
  }else {
   if(this.resultInfo.controls["dimension"].invalid){
     alert("Please enter Dimension")
   }
   else if(this.resultInfo.controls["weight"].invalid){
     alert("Please enter Weight")
   }
 }
 }
  editWeight(){
    if (this.resultInfo.status === 'VALID') {
      this.service.editWeight(this.resultInfo.value,this.id).subscribe(
        {
      next:(value)=>{
        alert(JSON.stringify({"message":"Successfully Update Weight Data","data":value}));
        window.location.replace('/data');
      },
      error:(err)=>alert(JSON.stringify(err.error))
    }
      )
    }
    else {
     if(this.resultInfo.controls["dimension"].invalid){
     alert("Please enter Dimension")
   }
   else if(this.resultInfo.controls["weight"].invalid){
     alert("Please enter Weight")
   }
   }
   }

}
