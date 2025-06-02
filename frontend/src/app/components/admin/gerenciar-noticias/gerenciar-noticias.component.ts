import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { Noticia } from '../../../model/noticia';
import { Autor } from '../../../model/autor';
import { lastValueFrom } from 'rxjs';
import { NoticiaService } from '../../../service/noticia.service';

@Component({
  selector: 'app-gerenciar-noticias',
  imports: [CommonModule, RouterModule,RouterLink],
  templateUrl: './gerenciar-noticias.component.html',
  styleUrl: './gerenciar-noticias.component.css'
})
export class GerenciarNoticiasComponent {
  noticia$: any;
  categoria: any;

  constructor(private noticiaService: NoticiaService, private router: Router){

  }
  ngOnInit(): void {
    this.getCategoria();
  }

  public async getCategoria(){
    this.noticia$ = await lastValueFrom(this.noticiaService.get());

  }


  public editar(id: number){
    //this.categoria = await lastValueFrom(this.categoriaService.getCategoriaById(id));
    this.router.navigate(['noticia/editar/', id]);
    console.log(id);
  }

  public async remover(id: number){
    let ret = await lastValueFrom(this.noticiaService.remover(id));
    this.noticia$ = await lastValueFrom(this.noticiaService.get());
  }
}
