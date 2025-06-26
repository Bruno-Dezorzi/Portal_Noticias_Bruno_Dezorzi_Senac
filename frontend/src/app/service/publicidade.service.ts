import { Injectable } from '@angular/core';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicidade } from './../model/publicidade';
import { GenericServiceService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class PublicidadeService extends GenericServiceService<Publicidade> {

  private baseUrl = "http://localhost:8080/publicidade";

  constructor(http: HttpClient) {
    super(http, "http://localhost:8080/publicidade");
  }

  getTodas(): Observable<Publicidade[]> {
    return this.http.get<Publicidade[]>(this.baseUrl);
  }

  getPorPosicao(nome: string): Observable<Publicidade[]> {
    return this.http.get<Publicidade[]>(`${this.baseUrl}/posicao/${nome}`);
  }
}
