import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { CategoriaComponent } from './components/admin/gerenciar-categorias/gerenciar-categorias.component';
import { CategoriaFormComponent } from './components/admin/cadastro_categorias/cadastro-categorias.component';
import { NoticiaComponent } from './components/admin/gerenciar-noticias/gerenciar-noticias.component';
import { NoticiaFormComponent } from './components/admin/cadastro_noticias/cadastro-noticias.component';
import { DashboardAdmComponent } from './components/admin/dashboard-adm/dashboard-adm.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redireciona raiz para login
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sair', component: LoginComponent }, // (sugestão: implementar lógica de logout aqui)
  { path: 'admin', component: DashboardAdmComponent },
  { path: 'categoria', component: CategoriaComponent },
  { path: 'categoria/novo', component: CategoriaFormComponent },
  { path: 'categoria/editar/:id', component: CategoriaFormComponent},
  { path: 'noticia', component: NoticiaComponent },
  { path: 'noticia/novo', component: NoticiaFormComponent },
  { path: 'noticia/editar/:id', component: NoticiaFormComponent}
];
