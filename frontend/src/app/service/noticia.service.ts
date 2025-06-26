import { Injectable } from '@angular/core';
import { Noticia } from '../../app/model/noticia';
import { HttpClient } from '@angular/common/http';
import { GenericServiceService } from './generic.service';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoticiaService extends GenericServiceService<Noticia> {

  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/noticia');
  }

   public listarNoticiasPorCategoria(id_categoria: any){
    return this.http.get(this.url + '/listarNoticiasPorCategoria/' + id_categoria).pipe(map(response => response));
  }

  public findOneByUltimaNoticiaByCategoria(id_categoria: any){
    return this.http.get(this.url + '/findTopByCategoriaIdOrderByDataPublicacaoDesc/' + id_categoria).pipe(map(response => response));
  }

  public findOneByUltimaNoticiaByTodas(){
    return this.http.get(this.url + '/findTopByOrderByDataPublicacaoDesc').pipe(map(response => response));
  }
}
