import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendApiService } from '../service/backend-api.service';
import { LoaderServiceService } from '../service/loader-service.service';

@Component({
  selector: 'app-trasaction',
  templateUrl: './trasaction.component.html',
  styleUrls: ['./trasaction.component.css']
})
export class TrasactionComponent implements OnInit{
 trasactionResults=[]
  constructor(private service:BackendApiService,private route:Router,private loadService:LoaderServiceService) { }
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()

      this.service.getTrasactionList().subscribe((res:any)=>{
      this.trasactionResults=res;
    })
    this.loadService.offLoaderVisibility()

  }

}
