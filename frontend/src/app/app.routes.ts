import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadComponent: () => import('./components/home/home.component').then(c => c.HomeComponent)
  },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(c => c.LoginComponent)
  },
  {
    path: 'admin',
    loadComponent: () => import('./components/admin/dashboard-adm/dashboard-adm.component').then(c => c.DashboardAdmComponent)
  },
  {
    path: 'cadastro_categoria',
    loadComponent: () => import('./components/admin/cadastro_categorias/cadastro-categorias.component').then(c => c.CategoriaFormComponent)
  },
  {
    path: 'cadastro_noticia',
    loadComponent: () => import('./components/admin/cadastro_noticias/cadastro-noticias.component').then(c => c.NoticiaFormComponent)
  },
  {
    path: 'gerenciar_noticia',
    loadComponent: () => import('./components/admin/gerenciar-noticias/gerenciar-noticias.component').then(c => c.NoticiaComponent)
  },
  {
    path: 'gerenciar_categoria',
    loadComponent: () => import('./components/admin/gerenciar-categorias/gerenciar-categorias.component').then(c => c.CategoriaComponent)
  },
  {
    path: 'lista_publicidade',
    loadComponent: () => import('./components/publicidade-list/publicidade-list.component').then(c => c.PublicidadeListComponent)
  }
];
