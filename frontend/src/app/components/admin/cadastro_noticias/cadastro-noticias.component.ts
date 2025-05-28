import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Interfaces
interface Categoria {
  id: number;
  nome: string;
  descricao?: string;
}

interface Autor {
  id: number;
  nome: string;
  email?: string;
  cargo?: string;
  biografia?: string;
}

interface Noticia {
  id?: number;
  titulo: string;
  subtitulo?: string;
  dataPublicacao: Date;
  resumo: string;
  conteudo: string;
  destaque: boolean;
  tags?: string;
  categoria?: Categoria;
  autor?: Autor;
  status?: string;
}

@Component({
  selector: 'app-cadastro-noticia',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './cadastro-noticias.component.html',
  styleUrls: ['./cadastro-noticias.component.css']
})
export class CadastroNoticiaComponent implements OnInit {

  newsForm: FormGroup;
  imagePreview: string | null = null;
  termoBusca: string = '';
  isNavbarCollapsed: boolean = true;
  mostrarNovaCategoria: boolean = false;
  mostrarNovoAutor: boolean = false;

  // Dados fictícios para categorias e autores
  categorias: Categoria[] = [
    { id: 1, nome: 'Esportes' },
    { id: 2, nome: 'Lazer' },
    { id: 3, nome: 'Política' },
    { id: 4, nome: 'Economia' },
    { id: 5, nome: 'Internacional' },
    { id: 6, nome: 'Tecnologia' },
    { id: 7, nome: 'Saúde' },
    { id: 8, nome: 'Educação' }
  ];

  autores: Autor[] = [
    { id: 1, nome: 'João Silva', cargo: 'Repórter Esportivo' },
    { id: 2, nome: 'Maria Santos', cargo: 'Jornalista Política' },
    { id: 3, nome: 'Pedro Oliveira', cargo: 'Correspondente Internacional' },
    { id: 4, nome: 'Ana Costa', cargo: 'Editora de Economia' },
    { id: 5, nome: 'Carlos Mendes', cargo: 'Repórter Geral' }
  ];

  constructor(private formBuilder: FormBuilder) {
    this.newsForm = this.createForm();
  }

  ngOnInit(): void {
    this.setDefaultDateTime();
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      id: [''],
      titulo: ['', Validators.required],
      subtitulo: [''],
      dataPublicacao: ['', Validators.required],
      resumo: ['', Validators.required],
      destaque: [false],
      categoria: [''],
      categoriaId: [''],
      categoriaNome: [''],
      categoriaDescricao: [''],
      autor: [''],
      autorId: [''],
      autorNome: [''],
      autorEmail: [''],
      autorCargo: [''],
      autorBiografia: [''],
      conteudo: ['', Validators.required],
      tags: ['']
    });
  }

  private setDefaultDateTime(): void {
    const now = new Date();
    const localDateTime = new Date(now.getTime() - now.getTimezoneOffset() * 60000)
      .toISOString().slice(0, 16);
    this.newsForm.patchValue({ dataPublicacao: localDateTime });
  }

  // Navbar methods
  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  navegarCategoria(categoria: string): void {
    console.log('Navegando para categoria:', categoria);
    // Implementar navegação para categoria
  }

  buscarNoticias(): void {
    if (this.termoBusca.trim()) {
      console.log('Buscando por:', this.termoBusca);
      // Implementar busca
    }
  }

  fazerLogin(): void {
    console.log('Redirecionando para login');
    // Implementar redirecionamento para login
  }

  // Form methods
  onImageSelected(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imagePreview = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  toggleNovaCategoria(): void {
    this.mostrarNovaCategoria = !this.mostrarNovaCategoria;

    if (this.mostrarNovaCategoria) {
      this.newsForm.get('categoria')?.setValue('');
      this.newsForm.get('categoria')?.disable();
    } else {
      this.newsForm.get('categoria')?.enable();
      // Limpar campos da nova categoria
      this.newsForm.patchValue({
        categoriaId: '',
        categoriaNome: '',
        categoriaDescricao: ''
      });
    }
  }

  toggleNovoAutor(): void {
    this.mostrarNovoAutor = !this.mostrarNovoAutor;

    if (this.mostrarNovoAutor) {
      this.newsForm.get('autor')?.setValue('');
      this.newsForm.get('autor')?.disable();
    } else {
      this.newsForm.get('autor')?.enable();
      // Limpar campos do novo autor
      this.newsForm.patchValue({
        autorId: '',
        autorNome: '',
        autorEmail: '',
        autorCargo: '',
        autorBiografia: ''
      });
    }
  }

  private criarObjetoNoticia(): Noticia {
    const formValue = this.newsForm.value;

    // Criar objeto Categoria
    let categoria: Categoria | undefined;
    if (this.mostrarNovaCategoria && formValue.categoriaNome) {
      categoria = {
        id: formValue.categoriaId || 0,
        nome: formValue.categoriaNome,
        descricao: formValue.categoriaDescricao || undefined
      };
    } else if (formValue.categoria) {
      const categoriaEncontrada = this.categorias.find(c => c.id == formValue.categoria);
      if (categoriaEncontrada) {
        categoria = categoriaEncontrada;
      }
    }

    // Criar objeto Autor
    let autor: Autor | undefined;
    if (this.mostrarNovoAutor && formValue.autorNome) {
      autor = {
        id: formValue.autorId || 0,
        nome: formValue.autorNome,
        email: formValue.autorEmail || undefined,
        cargo: formValue.autorCargo || undefined,
        biografia: formValue.autorBiografia || undefined
      };
    } else if (formValue.autor) {
      const autorEncontrado = this.autores.find(a => a.id == formValue.autor);
      if (autorEncontrado) {
        autor = autorEncontrado;
      }
    }

    // Criar objeto Noticia
    const noticia: Noticia = {
      id: formValue.id || undefined,
      titulo: formValue.titulo,
      subtitulo: formValue.subtitulo || undefined,
      dataPublicacao: new Date(formValue.dataPublicacao),
      resumo: formValue.resumo,
      conteudo: formValue.conteudo,
      destaque: formValue.destaque,
      tags: formValue.tags || undefined,
      categoria: categoria,
      autor: autor
    };

    return noticia;
  }

  publicarNoticia(): void {
    if (this.newsForm.invalid) {
      this.markFormGroupTouched();
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    try {
      const noticia = this.criarObjetoNoticia();

      // Validações adicionais
      if (!noticia.autor) {
        alert('Por favor, selecione ou cadastre um autor.');
        return;
      }

      // Aqui você enviaria os dados para o servidor
      console.log('Objeto Noticia criado:', noticia);

      // Simular envio bem-sucedido
      alert('Notícia publicada com sucesso!');

      // Opcional: limpar formulário ou redirecionar
      // this.newsForm.reset();
      // this.router.navigate(['/gerenciar-noticias']);

    } catch (error) {
      console.error('Erro ao criar notícia:', error);
      alert('Erro ao publicar notícia. Verifique os dados informados.');
    }
  }

  salvarRascunho(): void {
    try {
      const noticia = this.criarObjetoNoticia();
      noticia.status = 'rascunho';

      console.log('Rascunho salvo:', noticia);
      alert('Rascunho salvo com sucesso!');

    } catch (error) {
      console.error('Erro ao salvar rascunho:', error);
      alert('Erro ao salvar rascunho.');
    }
  }

  cancelarCadastro(): void {
    if (confirm('Tem certeza que deseja cancelar? Todos os dados não salvos serão perdidos.')) {
      // Implementar navegação de volta ou limpar formulário
      this.newsForm.reset();
      this.setDefaultDateTime();
      this.imagePreview = null;
      this.mostrarNovaCategoria = false;
      this.mostrarNovoAutor = false;
    }
  }

  private markFormGroupTouched(): void {
    Object.keys(this.newsForm.controls).forEach(key => {
      const control = this.newsForm.get(key);
      control?.markAsTouched();
    });
  }
}








/*import { NgFor } from '@angular/common';
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
}*/
