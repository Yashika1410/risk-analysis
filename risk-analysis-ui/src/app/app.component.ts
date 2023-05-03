import { Component } from '@angular/core';
import { BackendApiService } from './service/backend-api.service';
import { LoaderServiceService } from './service/loader-service.service';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  constructor(private service:BackendApiService,private loaderService:LoaderServiceService,private authService:AuthService){
    
  }
  get isLoaderVisible(): boolean {
        return this.loaderService.isLoaderVisible;
    }

  title = 'risk-analysis-ui';
   collapsed = true;
     toggleCollapsed(): void {
       this.collapsed = !this.collapsed;
     }
     triggerJob(){
      this.loaderService.onLoaderVisibility()
      this.service.triggerJobForDataAnalysis().subscribe({
    error:(err)=>{
      if(err.error.text=="Started Process"){
        alert("Successfully Trigger the Job")
      }
      else
      alert(JSON.stringify(err.error.text))}
  })
  this.loaderService.offLoaderVisibility(100)
     }
  get loginFlag():boolean{
    return localStorage.getItem('token')?this.authService.isAuthenticated():false;
  }
  logout(){
    this.authService.logout()
  }
    
}
