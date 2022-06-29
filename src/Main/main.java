package Main;

import Aestrela.Aestrela;
import clases.Caminho;

public class main {

    public static void main(String args[]) {

        Caminho caminho = Aestrela.inicio(2, 3, 2, 4, 6);

        System.out.println(caminho);
    }

    public static void printarCaminho(Caminho caminho) {
        System.out.println(caminho);
        Integer linha = caminho.getLinha();
        Integer coluna = caminho.getColuna();

        while (true) {
            if (caminho.getFilhos() != null) {
                caminho = caminho.getFilhos().get(0);
            } else {
                System.out.println(caminho.getLinha() + " " + caminho.getColuna());
                while (true) {
                    if (caminho == null) {
                        break;
                    }

                    Integer linhaAtual = caminho.getLinha();
                    Integer colunaAtual = caminho.getColuna();

                    caminho = caminho.getPai();
                    int k = 0;
                    for (Caminho caminhos : caminho.getFilhos()) {
                        if (caminhos.getColuna().equals(colunaAtual) && caminhos.getLinha().equals(linhaAtual)) {
                            k++;
                            break;
                        }

                        k++;
                    }

                    if (caminho.getFilhos().size() > k) {
                        caminho = caminho.getFilhos().get(k);
                        break;
                    } else {
                        caminho = caminho.getPai();
                    }
                }
            }

            if (caminho == null) {
                break;
            }
        }
    }
}
