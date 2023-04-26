import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendApiService } from '../service/backend-api.service';
import { LoaderServiceService } from '../service/loader-service.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-trasaction',
  templateUrl: './trasaction.component.html',
  styleUrls: ['./trasaction.component.css']
})
export class TrasactionComponent implements OnInit, AfterViewInit{
 trasactionResults=[]
 skip=0;
 limit=10;
 total=0;
  constructor(private service:BackendApiService,private route:Router,private loadService:LoaderServiceService) { }
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()

      this.service.getTrasactionList(this.skip,this.limit).subscribe((res:any)=>{
      this.trasactionResults=res["list"];
      this.total=res["totalCount"];
    })
  }
  ngAfterViewInit(): void {
       this.loadService.offLoaderVisibility()
  }

  getNextPage(event : PageEvent){
    this.loadService.onLoaderVisibility()
    this.trasactionResults=[]
    this.skip=(this.limit*event.pageIndex);
    this.limit=event.pageSize;
    this.service.getTrasactionList(this.skip,this.limit).subscribe((res:any)=>{
      this.trasactionResults=res["list"];
      this.total=res["totalCount"];
       this.loadService.offLoaderVisibility(0)
    }
      );
  }

}
