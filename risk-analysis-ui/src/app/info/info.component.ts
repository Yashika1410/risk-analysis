import { Component, OnInit } from '@angular/core';
import { LoaderServiceService } from '../service/loader-service.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {
  constructor (private loadService:LoaderServiceService){}
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()

    this.loadService.offLoaderVisibility()

  }

}
