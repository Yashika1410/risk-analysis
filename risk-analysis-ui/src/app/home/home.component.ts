import { Component, OnInit,AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendApiService } from '../service/backend-api.service';
import { LoaderServiceService } from '../service/loader-service.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit,AfterViewInit {
  outputResults=[]
  trasactionResults=[]
  colList=new Set<string>();
  Object = Object;
  constructor(private service:BackendApiService,private route:Router,private loadService:LoaderServiceService) { }

  ngOnInit(): void {
    this.loadService.onLoaderVisibility();
    this.service.getAnalysiedDataList().subscribe((res:any)=>{
      this.outputResults=res;

      res.forEach((element:any) => {
        this.Object.keys(element["result"]).forEach(key=>{
        this.colList.add(key)
        })
      });
    })
  }

 ngAfterViewInit(): void {
    this.loadService.offLoaderVisibility();
  }
}
