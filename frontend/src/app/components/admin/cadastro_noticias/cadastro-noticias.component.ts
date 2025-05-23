import { NgFor } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NoticiaService } from '../../../service/noticia.service';
import { lastValueFrom } from 'rxjs';
import { Noticia } from '../../../model/noticia';
import { CategoriaService } from '../../../service/categoria.service';
import { Timestamp } from '@angular/fire/firestore';
import { Autor } from '../../../model/autor';

@Component({
  selector: 'app-noticia-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, NgFor, RouterModule],
  templateUrl: '../cadastro_noticias/cadastro-noticias.component.html',
  styleUrl: '../cadastro_noticias/cadastro-noticias.component.css'
})
export class CadastroNoticiasComponent implements OnInit {
  cat$: any;
  noticia$: any;
  noticia: any;
  id: any;

  private activateRoute = inject(ActivatedRoute);

  form = new FormGroup({
    id: new FormControl<number | null>(null),
    titulo: new FormControl<string>(''),
    dataPublicacao: new FormControl<Timestamp>(Timestamp.fromDate(new Date())),
    categoria: new FormControl<number | null>(null),
    autorId: new FormControl<number | null>(null),
    autorNome: new FormControl<string>(''),
    autorEmail: new FormControl<string>(''),
    autorBiografia: new FormControl<string>('')
  });

  constructor(
    private noticiaService: NoticiaService,
    private router: Router,
    private categoriaService: CategoriaService
  ) {}

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

  public async getCategorias() {
    this.cat$ = await lastValueFrom(this.categoriaService.get());
  }

  public async getById() {
    this.noticia = await lastValueFrom(this.noticiaService.getById(this.id));
    console.log(this.noticia);

    const noticia = this.noticia;

    this.form.controls.id.setValue(noticia.id);
    this.form.controls.titulo.setValue(noticia.titulo);
    this.form.controls.dataPublicacao.setValue(
      Timestamp.fromDate(new Date(noticia.dataPublicacao))
    );
    this.form.controls.categoria.setValue(noticia.categoria?.id ?? null);

    // Set autor fields
    this.form.controls.autorId.setValue(noticia.autor?.id ?? null);
    this.form.controls.autorNome.setValue(noticia.autor?.nome ?? '');
    this.form.controls.autorEmail.setValue(noticia.autor?.email ?? '');
    this.form.controls.autorBiografia.setValue(noticia.autor?.biografia ?? '');
  }

  public salvar() {
    const id_ = this.form.controls.id.value;
    const titulo = this.form.controls.titulo.value;
    const dataPublicacao: Timestamp | null = this.form.controls.dataPublicacao.value;
    const categoriaId = this.form.controls.categoria.value;

    const autor: Autor = {
      id: this.form.controls.autorId.value,
      nome: this.form.controls.autorNome.value,
      email: this.form.controls.autorEmail.value,
      biografia: this.form.controls.autorBiografia.value
    };

    const dataPublicacaoConvertida = dataPublicacao?.toDate() ?? null;

    const noticia: Noticia = {
      id: id_,
      titulo: titulo!,
      dataPublicacao: dataPublicacaoConvertida,
      categoria: categoriaId
        ? {
            id: categoriaId,
            nome: null,
            categoria: null
          }
        : undefined,
      autor: autor
    };

    this.noticiaService.salvar(noticia).subscribe({
      next: noticia => {
        this.router.navigate(['noticia']);
        console.log(noticia);
      },
      error: erro => console.error('Erro ao salvar notícia:', erro)
    });
  }
}
