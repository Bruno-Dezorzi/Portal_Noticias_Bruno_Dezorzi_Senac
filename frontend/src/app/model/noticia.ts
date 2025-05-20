import { Autor } from "./autor";
import { Categoria } from "./categoria";

export class Noticia {
  id!: number|null;
  titulo!: string|null;
  dataPublicacao!: Date|null
  categoria?: Categoria;
  autor!: Autor;
}
