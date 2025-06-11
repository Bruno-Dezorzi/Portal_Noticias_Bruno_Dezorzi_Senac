
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
  title = 'meu-noticiario';

  constructor(public router: Router) {}

  isLoginPage(): boolean {
    return this.router.url === '/login' || this.router.url === '/sair';
  }
}
