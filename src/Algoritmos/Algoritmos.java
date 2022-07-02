package Algoritmos;

import java.util.ArrayList;
import java.util.List;

public class Algoritmos {

    public static Boolean pertenceRaio(int posDesejadaI,
                                       int posDesejadaJ,
                                       int raio,
                                       int posAtualL,
                                       int posAtualC) {
        int limiteInferior = posAtualL + raio;
        int limiteSuperior = posAtualL - raio;
        int limiteDireita = posAtualC + raio;
        int limiteEsquerda = posAtualC - raio;

        return posDesejadaI <= limiteInferior
                && posDesejadaI >= limiteSuperior
                && posDesejadaJ <= limiteDireita
                && posDesejadaJ >= limiteEsquerda
                && !mesmaLinha(posDesejadaI, posDesejadaJ, posAtualL, posAtualC);
    }

    public static Boolean dentroDaMatriz(int i, int j, int tamMat) {
        return i>=0 && i < tamMat && j>=0 && j < tamMat;
    }

    public static List<Integer> olharRaio(List<List<Integer>> mat, int posIrobo, int posJrobo, int tamMat, int raio) {
        List<Integer> pos = new ArrayList<>();
        int k=0;
        int i=0;
        int andar = 0;

        while(k < raio) {
            andar+=2;

            posIrobo--;
            posJrobo--;

            for(i=0; i<andar;i++) {
                if(dentroDaMatriz(posIrobo, posJrobo, tamMat)) {
                    if(mat.get(posIrobo).get(posJrobo).intValue() >= 9) {
                        pos.add(posIrobo);
                        pos.add(posJrobo);
                        return pos;
                    }
                }
                posJrobo++;
            }

            for(i=0; i<andar;i++) {
                if(dentroDaMatriz(posIrobo, posJrobo, tamMat)) {
                    if(mat.get(posIrobo).get(posJrobo).intValue() >= 9) {
                        pos.add(posIrobo);
                        pos.add(posJrobo);
                        return pos;
                    }
                }
                posIrobo++;
            }

            for(i=0; i<andar;i++) {
                if(dentroDaMatriz(posIrobo, posJrobo, tamMat)) {
                    if(mat.get(posIrobo).get(posJrobo).intValue() >= 9) {
                        pos.add(posIrobo);
                        pos.add(posJrobo);
                        return pos;
                    }
                }
                posJrobo--;
            }

            for(i=0; i<andar;i++) {
                if(dentroDaMatriz(posIrobo, posJrobo, tamMat)) {
                    if(mat.get(posIrobo).get(posJrobo).intValue() >= 9) {
                        pos.add(posIrobo);
                        pos.add(posJrobo);
                        return pos;
                    }
                }
                posIrobo--;
            }

            k++;
        }

        return null;
    }

    public static Boolean mesmaLinha(int i, int j, int row, int column) {
        return i == row && j == column;
    }
}
