import { Component, OnInit } from '@angular/core';
import { BackendApiService } from '../service/backend-api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.css']
})
export class CallbackComponent implements OnInit {

  constructor(private route: ActivatedRoute,private backend: BackendApiService,private router:Router){}
  ngOnInit(): void {
    const code = this.route.snapshot.queryParamMap.get('code') || "";
    this.backend.getToken(code).subscribe({next:(value:any)=>{
        localStorage.setItem('token',value["token"])
        localStorage.setItem('email',value['email'])
        this.router.navigate(['/'])
      },
    error:err=>{
      alert(JSON.stringify(err.error))
      this.router.navigate(['/login'])
    }
    })
  }
}
