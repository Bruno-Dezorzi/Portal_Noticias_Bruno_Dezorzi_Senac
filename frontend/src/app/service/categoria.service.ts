import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Categoria } from '../model/categoria';
import { HttpBackend } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends GenericService<Categoria> {

  constructor(handler: HttpBackend) {
    let url = "http://localhost:8080/categoria";
    super(handler, url);
  }
}
