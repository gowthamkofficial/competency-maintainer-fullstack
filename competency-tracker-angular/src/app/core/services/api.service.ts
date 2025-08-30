import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { checkNull } from '../helper/checknull';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly baseUrl = environment.apiURL;

  constructor(private http: HttpClient) {}

  private buildParams(params?: any): HttpParams {
    let httpParams = new HttpParams();
    if (params) {
      Object.keys(params).forEach(key => {
        if (checkNull(params[key])  && checkNull(params[key])) {
          httpParams = httpParams.set(key, params[key]);
        }
      });
    }
    return httpParams;
  }

  get<T>(endpoint: string, params?: any): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${endpoint}`, { params: this.buildParams(params) });
  }

  post<T>(endpoint: string, body?: any, params?: any): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${endpoint}`, body, { params: this.buildParams(params) });
  }

  put<T>(endpoint: string, body: any, params?: any): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${endpoint}`, body, { params: this.buildParams(params) });
  }

  delete<T>(endpoint: string, params?: any): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${endpoint}`, { params: this.buildParams(params) });
  }
}

