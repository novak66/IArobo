package Main;

import Inicio.Inicio;
import clases.Fabricas;
import clases.Robo;
import configuracao.ConfiguracaoInicial;

import java.io.IOException;
import java.util.List;

class main {

    public static void main(String args[]) throws IOException {

        //Caminho caminho = Aestrela.inicio(2, 3, 2, 4, 6, );

        //Caminho caminho = Aestrela.inicio(2, 3, 2, 4, 6);

        //System.out.println(caminho);

        List<List<Integer>> mat = ConfiguracaoInicial.criarMatriz();

        List<Fabricas> fabricas = ConfiguracaoInicial.popularFabricas(mat);

        Robo robo = ConfiguracaoInicial.inciarRobo(fabricas, 3, 4);

        Inicio.comecar(mat, robo);
    }
}
