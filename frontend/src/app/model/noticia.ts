import { Categoria } from "./categoria";

export class Noticia {
    id!: number|null;
    titulo!: string|null;
    corpo!: string|null;
    categoria!: Categoria|null;
}