import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicidade } from './../model/publicidade';
import { GenericServiceService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class PublicidadeService extends GenericServiceService<Publicidade> {

  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/publicidade');
  }
}
