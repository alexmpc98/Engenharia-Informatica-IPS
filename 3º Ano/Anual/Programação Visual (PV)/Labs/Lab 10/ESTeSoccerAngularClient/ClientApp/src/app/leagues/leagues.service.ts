import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";
import { League } from "./leagues";

@Injectable({
  providedIn: 'root'
})
export class LeaguesService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private httpClient: HttpClient) { }

  getLeagues(): Observable<League[]> {
    return this.httpClient.get<League[]>('api/leaguesapi/').pipe(catchError(this.errorHandler));
  }

  getLeague(id : number): Observable<League>{
    return this.httpClient.get<League>('api/leaguesapi/' + id).pipe(catchError(this.errorHandler));
  }

  createLeague(league: League): Observable<League> {
    return this.httpClient.post<League>('api/leaguesapi/', JSON.stringify(league), this.httpOptions).pipe(catchError(this.errorHandler));
  }

  updateLeague(id: number, league: League): Observable<League> {
    return this.httpClient.put<League>('api/leaguesapi/' + id, JSON.stringify(league), this.httpOptions).pipe(catchError(this.errorHandler));
  }

  deleteLeague(id: number) {
    return this.httpClient.delete<League>('api/leaguesapi/' + id, this.httpOptions).pipe(catchError(this.errorHandler));
  }

  errorHandler(error: { error: { message: string; }; }) {
    let errorMsg = "";
    if (error.error instanceof ErrorEvent) {
      errorMsg = error.error.message;
    } else {
      errorMsg = 'Error Code: ${error.status}\nMessage: ${error.message}';
    }
    return throwError(errorMsg);
  }
}
