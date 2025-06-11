import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericServiceService } from './generic.service';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends GenericServiceService<Usuario>{

  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/usuario');
  }
}
