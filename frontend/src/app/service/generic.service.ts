import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Identificador {
  id: number | null;
}

/*@Injectable({
  providedIn: 'root'
})*/
export class GenericServiceService<T extends Identificador> {

  protected headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(
    protected http: HttpClient,
    protected url: string
  ) {}

  public get(): Observable<T[]> {
    return this.http.get<T[]>(`${this.url}/listar`, { headers: this.headers });
  }

  public getById(id: number): Observable<T> {
    return this.http.get<T>(`${this.url}/listar/${id}`, { headers: this.headers });
  }

  public salvar(object: T): Observable<T> {
    if (object.id !== null && object.id !== undefined) {
      return this.http.put<T>(`${this.url}/atualizar/${object.id}`, object, { headers: this.headers });
    }
    return this.http.post<T>(`${this.url}/novo`, object, { headers: this.headers });
  }

  public remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/remover/${id}`, { headers: this.headers });
  }
}
