import { HttpBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericService } from './generic.service';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends GenericService<Usuario>{

  constructor(handler: HttpBackend) {
    let url = "http://localhost:8080/usuario";
    super(handler, url);
  }
}
