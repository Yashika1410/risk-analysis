import { AfterContentInit, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoaderServiceService } from '../service/loader-service.service';
import { BackendApiService } from '../service/backend-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit, AfterContentInit {
  constructor(private loadService:LoaderServiceService,private fb: FormBuilder, private service:BackendApiService,private route: Router){}
  submitted = false;
  userInfo = this.fb.group({
    email: ['', [Validators.required,Validators.email]],
    password: ['', [Validators.required,Validators.minLength(5)]],
  })
  get f() { return this.userInfo.controls; }
  
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()
  }
  ngAfterContentInit(): void {
    this.loadService.offLoaderVisibility(400);
  }
   clearForm(){
    this.userInfo.reset();
  }
  submitHandler() {
    this.submitted=true
    if(this.userInfo.valid){
      this.service.register(this.userInfo.value).subscribe({next:(value:any)=>{
        localStorage.setItem('token',value["token"])
        localStorage.setItem('email',value['email'])
        this.route.navigate(['/'])
      },
    error:(err)=>{alert(JSON.stringify(err.error))},
    complete:()=>console.log("signup done")
    })
    }
  }
}
