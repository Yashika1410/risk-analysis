import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable()
export class HttpInterceptorInterceptor implements HttpInterceptor {

  constructor(public auth: AuthService,public route:Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let idToken=localStorage.getItem('token')?localStorage.getItem('token'):'';
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${idToken}`
      }
    });
    return next.handle(request).pipe(catchError((err)=>{
      if(err instanceof HttpErrorResponse){
        if(err.status == 401){
          this.auth.logout()
        }

      }
      return throwError(err);
    }));
  }
}
