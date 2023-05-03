import { Component, OnInit,AfterContentInit } from '@angular/core';
import { LoaderServiceService } from '../service/loader-service.service';
import { FormBuilder, Validators } from '@angular/forms';
import { BackendApiService } from '../service/backend-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterContentInit {
  constructor(private loadService:LoaderServiceService,private fb: FormBuilder, private service:BackendApiService,private route:Router){}
  submitted = false;
  loginInfo = this.fb.group({
    email: ['', [Validators.required,Validators.email]],
    password: ['', Validators.required],
  })
  get f() { return this.loginInfo.controls; }
 
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()
  }
  ngAfterContentInit(): void {
    this.loadService.offLoaderVisibility(400);
  }
   
   clearForm(){
    this.loginInfo.reset();
  }
  submitHandler() {
    this.submitted=true
    if(this.loginInfo.valid){
      this.service.login(this.loginInfo.value).subscribe({next:(value:any)=>{
        localStorage.setItem('token',value["token"])
        localStorage.setItem('email',value['email'])
        this.route.navigate(['/'])
      },
    error:err=>alert(err)
    })
    }
  }

}
