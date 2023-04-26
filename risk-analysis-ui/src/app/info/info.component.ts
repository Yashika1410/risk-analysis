import { Component, OnInit } from '@angular/core';
import { LoaderServiceService } from '../service/loader-service.service';
import {faGithub} from '@fortawesome/free-brands-svg-icons'

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {
  constructor (private loadService:LoaderServiceService){}
  githubIcon=faGithub;
  ngOnInit(): void {
    this.loadService.onLoaderVisibility()

    this.loadService.offLoaderVisibility(200)

  }

}
