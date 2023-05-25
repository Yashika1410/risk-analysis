import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  constructor(private http:HttpClient) { }
  headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
  getAnalysiedDataList(){
    return this.http.get(`${environment.apiUrl}/v1/risk-analysis/get-data`);

  }
  triggerJobForDataAnalysis(){
    return this.http.post(`${environment.apiUrl}/v1/risk-analysis/start-process`,{});
    
  }
  getCompanyRiskScoreList(){
    return this.http.get(`${environment.apiUrl}/v1/company-risk-scores`);
  }
  getCompanyRiskScore(id: number){
    return this.http.get(`${environment.apiUrl}/v1/company-risk-scores/${id}`);
  }
  createCompanyRiskScore(data:any){
    return this.http.post(`${environment.apiUrl}/v1/company-risk-scores`,data);
  }
  editCompanyRiskScore(data:any,id:number){
    return this.http.patch(`${environment.apiUrl}/v1/company-risk-scores/${id}`,data);
  }
  deleteCompanyRiskScore(id:number){
    return this.http.delete(`${environment.apiUrl}/v1/company-risk-scores/${id}`,{
      headers: this.headers, responseType: 'text'});
  }

  getFormulaList() {
    return this.http.get(`${environment.apiUrl}/v1/formulas`);
  }
  getFormula(id: number) {
    return this.http.get(`${environment.apiUrl}/v1/formulas/${id}`);
  }
  createFormula(data: any) {
    return this.http.post(`${environment.apiUrl}/v1/formulas`, data);
  }
  editFormula(data: any, id: number) {
    return this.http.patch(`${environment.apiUrl}/v1/formulas/${id}`, data);
  }
  deleteFormula(id: number) {
    return this.http.delete(`${environment.apiUrl}/v1/formulas/${id}`,{
      headers: this.headers, responseType: 'text'});
  }

  getWeightList() {
    return this.http.get(`${environment.apiUrl}/v1/weights`);
  }
  getWeight(id: number) {
    return this.http.get(`${environment.apiUrl}/v1/weights/${id}`);
  }
  createWeight(data: any) {
    return this.http.post(`${environment.apiUrl}/v1/weights`, data);
  }
  editWeight(data: any, id: number) {
    return this.http.patch(`${environment.apiUrl}/v1/weights/${id}`, data);
  }
  deleteWeight(id: number) {
    return this.http.delete(`${environment.apiUrl}/v1/weights/${id}`,{
      headers: this.headers, responseType: 'text'});
  }

  getRiskScoreLevelList() {
    return this.http.get(`${environment.apiUrl}/v1/risk-score-levels`);
  }
  getRiskScoreLevel(id: number) {
    return this.http.get(`${environment.apiUrl}/v1/risk-score-levels/${id}`);
  }
  createRiskScoreLevel(data: any) {
    return this.http.post(`${environment.apiUrl}/v1/risk-score-levels`, data);
  }
  editRiskScoreLevel(data: any, id: number) {
    return this.http.patch(`${environment.apiUrl}/v1/risk-score-levels/${id}`, data);
  }
  deleteRiskScoreLevel(id: number) {
    return this.http.delete(`${environment.apiUrl}/v1/risk-score-levels/${id}`,{
      headers: this.headers, responseType: 'text'});
  }

  getRiskScoreCapList() {
    return this.http.get(`${environment.apiUrl}/v1/risk-score-caps`);
  }
  getRiskScoreCap(id: number) {
    return this.http.get(`${environment.apiUrl}/v1/risk-score-caps/${id}`);
  }
  createRiskScoreCap(data: any) {
    return this.http.post(`${environment.apiUrl}/v1/risk-score-caps`, data);
  }
  editRiskScoreCap(data: any, id: number) {
    return this.http.patch(`${environment.apiUrl}/v1/risk-score-caps/${id}`, data);
  }
  deleteRiskScoreCap(id: number) {
    return this.http.delete(`${environment.apiUrl}/v1/risk-score-caps/${id}`,{
      headers: this.headers, responseType: 'text'});
  }
   getTrasactionList(skip:number,limit:number) {
    return this.http.get(`${environment.apiUrl}/v1/trasactions?skip=${skip}&limit=${limit}`);
  }
  login(loginData:any){
    return this.http.post(`${environment.authUrl}/v1/sign-in`,loginData);
  }
  register(userData:any){
    return this.http.post(`${environment.authUrl}/v1/sign-up`,userData);
  }
  getToken(code: string) {
    const tokenUrl = `${environment.authUrl}/v1/github/sign-in`; // Replace with your backend API endpoint to exchange the code for a token
    const data = { code };
    return this.http.post(tokenUrl, data);
  }
}
