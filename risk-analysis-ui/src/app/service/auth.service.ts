import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
const jwtHelper = new JwtHelperService();
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private route:Router) {}
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !jwtHelper.isTokenExpired(token);
  }
  public logout(){
    localStorage.removeItem('token')
    this.route.navigate(['/login']);
  }
}
