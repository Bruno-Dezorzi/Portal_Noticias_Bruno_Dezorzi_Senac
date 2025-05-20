import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Noticia } from '../model/noticia';
import { HttpBackend } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NoticiaService extends GenericService<Noticia> {

  constructor(handler: HttpBackend) {
    let url = "http://localhost:8080/noticia";
    super(handler, url);
  }
}
