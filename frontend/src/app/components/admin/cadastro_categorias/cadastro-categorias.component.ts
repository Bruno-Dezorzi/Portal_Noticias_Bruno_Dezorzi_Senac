import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormsModule } from '@angular/forms';

// Interface para a categoria
interface Categoria {
  id: number;
  nome: string;
  categoria?: Categoria | null;
}

// Interface para estatísticas
interface Statistics {
  total: number;
  main: number;
}

@Component({
  selector: 'app-admin-categorias',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './cadastro-categorias.component.html',
  styleUrls: ['./cadastro-categorias.component.css']
})
export class AdminCategoriasComponent implements OnInit {
  categoryForm: FormGroup;
  categorias: Categoria[] = [];
  filteredCategories: Categoria[] = [];
  mainCategories: Categoria[] = [];
  searchTerm: string = '';
  isEditing: boolean = false;
  formTitle: string = 'Adicionar Nova Categoria';

  // Alert properties
  alertMessage: string = '';
  alertType: 'success' | 'warning' | 'danger' = 'success';

  // Modal properties
  categoryToDelete: Categoria | null = null;
  subcategoriesCount: number = 0;

  // Statistics
  statistics: Statistics = { total: 0, main: 0 };

  private nextId: number = 11;

  constructor(private fb: FormBuilder) {
    this.categoryForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      categoriaId: ['']
    });
  }

  ngOnInit(): void {
    this.initializeData();
    this.loadCategories();
    this.updateStatistics();
  }

  private initializeData(): void {
    // Dados iniciais simulando banco de dados
    this.categorias = [
      { id: 1, nome: "Esportes", categoria: null },
      { id: 2, nome: "Futebol", categoria: { id: 1, nome: "Esportes" } },
      { id: 3, nome: "Basquete", categoria: { id: 1, nome: "Esportes" } },
      { id: 4, nome: "Política", categoria: null },
      { id: 5, nome: "Política Nacional", categoria: { id: 4, nome: "Política" } },
      { id: 6, nome: "Política Internacional", categoria: { id: 4, nome: "Política" } },
      { id: 7, nome: "Tecnologia", categoria: null },
      { id: 8, nome: "Inteligência Artificial", categoria: { id: 7, nome: "Tecnologia" } },
      { id: 9, nome: "Programação", categoria: { id: 7, nome: "Tecnologia" } },
      { id: 10, nome: "Economia", categoria: null }
    ];
  }

  onSubmit(): void {
    if (this.categoryForm.valid) {
      const formValue = this.categoryForm.value;
      const categoryName = formValue.nome.trim();
      const parentId = formValue.categoriaId;

      // Verificar se já existe categoria com o mesmo nome
      const existingCategory = this.categorias.find(cat =>
        cat.nome.toLowerCase() === categoryName.toLowerCase() &&
        (!this.isEditing || cat.id !== this.getEditingId())
      );

      if (existingCategory) {
        this.showAlert('Já existe uma categoria com esse nome.', 'warning');
        return;
      }

      const parentCategory = parentId ? this.categorias.find(cat => cat.id == parentId) || null : null;

      if (this.isEditing) {
        this.updateCategory(categoryName, parentCategory);
      } else {
        this.createCategory(categoryName, parentCategory);
      }

      this.resetForm();
      this.loadCategories();
      this.updateStatistics();
    }
  }

  private createCategory(nome: string, categoria: Categoria | null): void {
    const newCategory: Categoria = {
      id: this.nextId++,
      nome,
      categoria
    };

    this.categorias.push(newCategory);
    this.showAlert(`Categoria "${nome}" adicionada com sucesso!`, 'success');
  }

  private updateCategory(nome: string, categoria: Categoria | null): void {
    const editingId = this.getEditingId();
    const categoryIndex = this.categorias.findIndex(cat => cat.id === editingId);

    if (categoryIndex !== -1) {
      this.categorias[categoryIndex] = {
        ...this.categorias[categoryIndex],
        nome,
        categoria
      };
      this.showAlert(`Categoria "${nome}" atualizada com sucesso!`, 'success');
    }
  }

  private getEditingId(): number {
    // Implementar lógica para obter ID da categoria sendo editada
    // Por simplicidade, usando uma propriedade adicional
    return (this.categoryForm as any).editingId || 0;
  }

  editCategory(categoria: Categoria): void {
    this.isEditing = true;
    this.formTitle = 'Editar Categoria';

    // Armazenar ID da categoria sendo editada
    (this.categoryForm as any).editingId = categoria.id;

    this.categoryForm.patchValue({
      nome: categoria.nome,
      categoriaId: categoria.categoria?.id || ''
    });

    this.loadMainCategories();

    // Scroll to form
    setTimeout(() => {
      const formElement = document.querySelector('.admin-content');
      formElement?.scrollIntoView({ behavior: 'smooth' });
    }, 100);
  }

  openDeleteModal(categoria: Categoria): void {
    this.categoryToDelete = categoria;
    this.subcategoriesCount = this.categorias.filter(cat => cat.categoria?.id === categoria.id).length;

    // Abrir modal do Bootstrap
    const modal = document.getElementById('deleteModal');
    if (modal) {
      // Usando Bootstrap modal diretamente
      const bootstrapModal = new (window as any).bootstrap.Modal(modal);
      bootstrapModal.show();
    }
  }

  confirmDelete(): void {
    if (this.categoryToDelete) {
      const categoryName = this.categoryToDelete.nome;
      const categoryId = this.categoryToDelete.id;

      // Remover categoria e suas subcategorias
      this.categorias = this.categorias.filter(cat => {
        return cat.id !== categoryId && cat.categoria?.id !== categoryId;
      });

      this.showAlert(`Categoria "${categoryName}" excluída com sucesso!`, 'success');

      this.loadCategories();
      this.updateStatistics();
      this.resetForm();

      // Fechar modal
      const modal = document.getElementById('deleteModal');
      if (modal) {
        const bootstrapModal = (window as any).bootstrap.Modal.getInstance(modal);
        bootstrapModal?.hide();
      }
    }
  }

  filterCategories(): void {
    if (!this.searchTerm.trim()) {
      this.filteredCategories = [...this.categorias];
    } else {
      const term = this.searchTerm.toLowerCase();
      this.filteredCategories = this.categorias.filter(cat =>
        cat.nome.toLowerCase().includes(term) ||
        cat.categoria?.nome.toLowerCase().includes(term)
      );
    }
  }

  resetForm(): void {
    this.categoryForm.reset();
    this.isEditing = false;
    this.formTitle = 'Adicionar Nova Categoria';
    (this.categoryForm as any).editingId = null;
    this.loadMainCategories();
  }

  private loadCategories(): void {
    // Ordenar categorias: principais primeiro, depois subcategorias
    this.categorias.sort((a, b) => {
      if (!a.categoria && !b.categoria) return a.nome.localeCompare(b.nome);
      if (!a.categoria) return -1;
      if (!b.categoria) return 1;

      const parentCompare = (a.categoria?.nome || '').localeCompare(b.categoria?.nome || '');
      if (parentCompare !== 0) return parentCompare;

      return a.nome.localeCompare(b.nome);
    });

    this.filteredCategories = [...this.categorias];
    this.loadMainCategories();
  }

  private loadMainCategories(): void {
    const editingId = this.isEditing ? this.getEditingId() : null;

    this.mainCategories = this.categorias
      .filter(cat => !cat.categoria && cat.id !== editingId)
      .sort((a, b) => a.nome.localeCompare(b.nome));
  }

  private updateStatistics(): void {
    this.statistics.total = this.categorias.length;
    this.statistics.main = this.categorias.filter(cat => !cat.categoria).length;
  }

  showAlert(message: string, type: 'success' | 'warning' | 'danger'): void {
    this.alertMessage = message;
    this.alertType = type;

    // Auto-hide after 5 seconds
    setTimeout(() => {
      this.closeAlert();
    }, 5000);
  }

  closeAlert(): void {
    this.alertMessage = '';
  }

  logout(): void {
    // Implementar lógica de logout
    console.log('Logout clicked');
    // Redirect to home or login page
  }
}




/*import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../service/categoria.service';
import { Router, RouterModule } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-categoria',
  standalone: true,
  imports: [NgFor, RouterModule],
  templateUrl: './../cadastro_categorias/cadastro-categorias.component.html',
  styleUrl: './../cadastro_categorias/cadastro-categorias.component.css'
})
export class CategoriaComponent implements OnInit {
  categoria$: any;

  constructor(
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCategoria();
  }

  public async getCategoria(): Promise<void> {
    try {
      this.categoria$ = await lastValueFrom(this.categoriaService.get());
    } catch (erro) {
      console.error('Erro ao buscar categorias:', erro);
    }
  }

  public editar(id: number): void {
    this.router.navigate(['categoria/editar', id]);
  }

  public async remover(id: number): Promise<void> {
    try {
      await lastValueFrom(this.categoriaService.remover(id));
      this.getCategoria(); // atualiza a lista após remover
    } catch (erro) {
      console.error('Erro ao remover categoria:', erro);
    }
  }
}
*/
