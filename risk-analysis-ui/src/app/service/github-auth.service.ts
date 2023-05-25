import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GithubAuthService {
  constructor() { }

  login() {
    const url = `https://github.com/login/oauth/authorize?client_id=${environment.clientId}&scope=read:user,user:email`;
    window.location.href = url;
  }
}
