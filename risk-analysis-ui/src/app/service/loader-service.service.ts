import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoaderServiceService {
 isLoaderVisible: boolean=true;

  LoaderVisibilityChange: Subject<boolean> = new Subject<boolean>();
  constructor() {
    this.LoaderVisibilityChange.subscribe((value:boolean) => {
            this.isLoaderVisible = value
        });
   }
   onLoaderVisibility() {
        this.LoaderVisibilityChange.next(this.isLoaderVisible=true);
    }
    offLoaderVisibility() {
        this.LoaderVisibilityChange.next(this.isLoaderVisible=false);
    }
}
