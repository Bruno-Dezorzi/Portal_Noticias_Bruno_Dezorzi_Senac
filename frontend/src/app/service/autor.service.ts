import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Autor } from '../model/autor';
import { HttpBackend } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AutorService extends GenericService<Autor>{

  constructor(handler: HttpBackend) {
    let url = "http://localhost:8080/autor";
    super(handler, url);
  }
}
