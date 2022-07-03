package configuracao;

import clases.Fabricas;
import clases.Robo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ConfiguracaoInicial {

    public static List<List<Integer>> criarMatriz() throws IOException {
        FileInputStream file = new FileInputStream("arquivo.txt");

        int k = file.read();

        List<List<Integer>> mat = new ArrayList<>();
        List<Integer> lista = new ArrayList<>();

        while (k != -1) {

            if (k >= 48 && k < 58) {
                lista.add((k - 48));
            }

            if (k == 13) {
                mat.add(lista);
                lista = new ArrayList<>();
            }
            k = file.read();
        }
        mat.add(lista);

        int tam = mat.get(0).size();

        int i = 0;

        int objetos = 20;

        while (i < objetos) {
            for (int j = 3; j < tam; j++) {
                for (int m = 0; m < tam; m++) {
                    if (mat.get(j).get(m).equals(0) && probabilidadeJogar()) {
                        mat.get(j).set(m, 9);
                        i++;
                    }
                    if(i>=objetos) {
                        break;
                    }
                }
            }
        }

        i=0;
        objetos = 10;

        while (i < objetos) {
            for (int j = 7; j < tam; j++) {
                for (int m = 0; m < tam; m++) {
                    if (mat.get(j).get(m).equals(0) && probabilidadeJogar()) {
                        mat.get(j).set(m, 10);
                        i++;
                    }
                    if(i>=objetos) {
                        break;
                    }
                }
            }
        }

        i=0;
        objetos = 8;

        while (i < objetos) {
            for (int j = 0; j < tam; j++) {
                for (int m = 0; m < tam; m++) {
                    if (mat.get(j).get(m).equals(0) && probabilidadeJogar()) {
                        mat.get(j).set(m, 11);
                        i++;
                    }
                    if(i>=objetos) {
                        break;
                    }
                }
            }
        }

        i=0;
        objetos = 6;

        while (i < objetos) {
            for (int j = 9; j < tam; j++) {
                for (int m = 0; m < tam; m++) {
                    if (mat.get(j).get(m).equals(0) && probabilidadeJogar()) {
                        mat.get(j).set(m, 12);
                        i++;
                    }
                    if(i>=objetos) {
                        break;
                    }
                }
            }
        }

        i=0;
        objetos = 4;

        while (i < objetos) {
            for (int j = 0; j < tam; j++) {
                for (int m = 0; m < tam; m++) {
                    if (mat.get(j).get(m).equals(0) && probabilidadeJogar()) {
                        mat.get(j).set(m, 13);
                        i++;
                    }
                    if(i>=objetos) {
                        break;
                    }
                }
            }
        }

        return mat;
    }

    public static Fabricas escolherFabrica(int nrFabrica, int i, int j) {

        switch (nrFabrica) {
            case 4:
                return fabrica1(i, j);

            case 5:
                return fabrica2(i, j);

            case 6:
                return fabrica3(i, j);

            case 7:
                return fabrica4(i, j);

            case 8:
                return fabrica5(i, j);
        }
        return null;
    }

    public static Fabricas fabrica1(int i, int j) {
        Fabricas fabrica = new Fabricas();

        fabrica.setPosi(i);
        fabrica.setPosj(j);
        fabrica.setBaterias(8);
        fabrica.setBomba(0);
        fabrica.setBracoPneu(0);
        fabrica.setTipoFabrica(4);
        fabrica.setBracoSol(0);
        fabrica.setRefrigeracao(0);
        fabrica.setConsertada(false);

        return fabrica;
    }

    public static Fabricas fabrica2(int i, int j) {
        Fabricas fabrica = new Fabricas();

        fabrica.setPosi(i);
        fabrica.setPosj(j);
        fabrica.setBaterias(0);
        fabrica.setBomba(0);
        fabrica.setBracoPneu(0);
        fabrica.setTipoFabrica(5);
        fabrica.setBracoSol(5);
        fabrica.setRefrigeracao(0);
        fabrica.setConsertada(false);

        return fabrica;
    }

    public static Fabricas fabrica3(int i, int j) {
        Fabricas fabrica = new Fabricas();

        fabrica.setPosi(i);
        fabrica.setPosj(j);
        fabrica.setBaterias(0);
        fabrica.setBomba(2);
        fabrica.setBracoPneu(0);
        fabrica.setTipoFabrica(6);
        fabrica.setBracoSol(0);
        fabrica.setRefrigeracao(0);
        fabrica.setConsertada(false);

        return fabrica;
    }

    public static Fabricas fabrica4(int i, int j) {
        Fabricas fabrica = new Fabricas();

        fabrica.setPosi(i);
        fabrica.setPosj(j);
        fabrica.setBaterias(0);
        fabrica.setBomba(0);
        fabrica.setBracoPneu(0);
        fabrica.setTipoFabrica(7);
        fabrica.setBracoSol(0);
        fabrica.setRefrigeracao(5);
        fabrica.setConsertada(false);

        return fabrica;
    }

    public static Fabricas fabrica5(int i, int j) {
        Fabricas fabrica = new Fabricas();

        fabrica.setPosi(i);
        fabrica.setPosj(j);
        fabrica.setBaterias(0);
        fabrica.setBomba(0);
        fabrica.setBracoPneu(2);
        fabrica.setTipoFabrica(0);
        fabrica.setBracoSol(0);
        fabrica.setRefrigeracao(0);
        fabrica.setConsertada(false);

        return fabrica;
    }

    public static Robo inciarRobo(List<Fabricas> fabricas, int i, int j) {
        Robo robo = new Robo();

        robo.setPosi(i);
        robo.setPosj(j);
        robo.setBaterias(0);
        robo.setBracoSol(0);
        robo.setBomba(0);
        robo.setRefrigeracao(0);
        robo.setBracoPneu(0);
        robo.setFabricas(fabricas);

        return robo;
    }

    public static List<Fabricas> popularFabricas(List<List<Integer>> mat) {
        List<Fabricas> fabricas = new ArrayList<>();
        int i = 0;
        int j=0;
        for (List<Integer> matrizI : mat) {
            j=0;
            for (Integer matrizJ : matrizI) {
                if (matrizJ.intValue() > 3 && matrizJ.intValue() < 9) {
                    Fabricas fabrica = escolherFabrica(matrizJ.intValue(), i, j);
                    fabricas.add(fabrica);
                }
                j++;
            }

            i++;
        }
        return fabricas;
    }

    private static Boolean probabilidadeJogar() {
        Random rand = new Random();

        int randomNum = rand.nextInt((1000 - 1) + 1) + 1;

        rand.setSeed(1000);

        if (randomNum == 1) {
            try {
                sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }
}
