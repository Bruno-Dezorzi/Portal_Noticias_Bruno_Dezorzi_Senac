import { Injectable } from '@angular/core';
import { Noticia } from '../../app/model/noticia';
import { HttpClient } from '@angular/common/http';
import { GenericServiceService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class NoticiaService extends GenericServiceService<Noticia> {

  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/noticia');
  }
}
