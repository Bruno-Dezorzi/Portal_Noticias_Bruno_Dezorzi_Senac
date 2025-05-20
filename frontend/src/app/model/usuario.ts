import { Pessoa } from "./pessoa";

export class Usuario extends Pessoa{
  login!: string|null;
  senha!: string;
}
