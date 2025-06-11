import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NoticiaService } from '../../../service/noticia.service';
import { lastValueFrom } from 'rxjs';
import { Noticia } from '../../../model/noticia';
import { CategoriaService } from '../../../service/categoria.service';
import { NgFor } from '@angular/common';

@Component({
    standalone: true,
    selector: 'app-noticia-form',
    imports: [FormsModule,
        ReactiveFormsModule,
        RouterModule
      ],
    templateUrl: './cadastro-noticias.component.html',
    styleUrl: './cadastro-noticias.component.css'
})
export class NoticiaFormComponent implements OnInit {
  cat$: any;
  noticia$: any;
  noticia: any;
  private activateRoute = inject(ActivatedRoute);
  id: any;

  form = new FormGroup({
    id: new FormControl<number | null>(null),
    titulo: new FormControl<string>(''),
    corpo: new FormControl<string>(''),
    categoria: new FormControl<number | null>(null)

  });

  constructor(private noticiaService: NoticiaService, private router: Router, private categoriaService: CategoriaService) {

  }
  ngOnInit(): void {
    this.get();
    this.getCategorias();
    this.id = this.activateRoute.snapshot.params['id'];
    if (this.id) {
      this.getById();
    }

  }

  public async get() {
    this.noticia$ = await lastValueFrom(this.noticiaService.get());
  }

  public async getById() {
    this.noticia = await lastValueFrom(this.noticiaService.getById(this.id));
    console.log(this.noticia);
    this.form.controls.id.setValue(this.noticia.id);
    this.form.controls.titulo.setValue(this.noticia.titulo);
    this.form.controls.corpo.setValue(this.noticia.corpo);
    this.form.controls.categoria.setValue(this.noticia.categoria?.id);
  }

  public async getCategorias(){
    this.cat$ = await lastValueFrom(this.categoriaService.get());
  }

  public salvar() {
    //todo
    let id_ = null;
    if (this.id) {
      id_ = this.id;
    }
    let titulo = this.form.controls.titulo.value;
    let corpo = this.form.controls.corpo.value;
    let categoria = this.form.controls.categoria.value;
    let noticia: Noticia;

    noticia = {
      "id": id_,
      "titulo": titulo,
      "corpo": corpo,
      "categoria": {
        "id": categoria,
        "nome": null,
        "descricao": null,
        "categoria": null
      }
    };

    this.noticiaService.salvar(noticia).subscribe(
      noticia => {
        this.router.navigate(['noticia']);
        console.log(noticia);
      },
      erro => {
        console.log(erro);
      }
    );
  }
}