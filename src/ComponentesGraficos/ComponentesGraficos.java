package ComponentesGraficos;

import Algoritmos.Algoritmos;
import clases.Caminho;
import clases.Robo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComponentesGraficos extends JPanel {

    private static final long serialVersionUID = 1L;
    private int rows;
    private int column;
    private JLabel[][] squares;

    public ComponentesGraficos(int rows, int column, List<List<Integer>> matriz, Robo robo, List<Caminho> destino) {
        this.rows = rows;
        this.column = column;
        this.setLayout(new GridLayout(this.rows, this.rows));
        this.squares = new JLabel[this.rows][this.column];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < this.column; c++) {
                SquareLabel square = new SquareLabel();
                square.setSize(1, 1);
                square.setBackground(defineColor(matriz, r, c, robo, destino));
                this.squares[r][c] = square;
                this.add(square);
            }
        }
    }

    public static Color colorOpacity(List<List<Integer>> matriz, int row, int column) {

        if (matriz.get(row).get(column) == 0) {
            return new Color(0, 128, 0, 80);
        }
        if (matriz.get(row).get(column) == 1) {
            return new Color(102, 51, 0, 95);
        }
        if (matriz.get(row).get(column) == 2) {
            return new Color(0, 0, 255, 80);
        }

        if (matriz.get(row).get(column) == 3) {
            return new Color(255, 165, 0, 80);
        }

        return Color.black;
    }

    public static Boolean contidoDestino(int row, int column, List<Caminho> destino) {
        int i=0;
        int j = destino.size();
        for(Caminho caminho : destino) {
            if(i == j -1) {
                return false;
            }
            if(caminho.getLinha().equals(row) && caminho.getColuna().equals(column)) {
                return true;
            }

            i++;
        }

        return false;
    }

    public static Color defineColor(List<List<Integer>> matriz, int row, int column, Robo robo, List<Caminho> destino) {
        int roboI = robo.getPosi();
        int roboJ = robo.getPosj();

        if (row == roboI && column == roboJ) {
            return Color.BLACK;
        }

        if(destino != null && contidoDestino(row, column, destino)) {
            return Color.darkGray;
        }

        if (matriz.get(row).get(column) == 4) {
            return Color.YELLOW;
        }

        if (matriz.get(row).get(column) == 5) {
            return Color.RED;
        }

        if (matriz.get(row).get(column) == 6) {
            return Color.DARK_GRAY;
        }

        if (matriz.get(row).get(column) == 7) {
            return Color.cyan;
        }

        if (matriz.get(row).get(column) == 8) {
            return Color.MAGENTA;
        }

        if (matriz.get(row).get(column) == 9) {
            return new Color(165, 42, 42);
        }

        if (matriz.get(row).get(column) == 10) {
            return new Color(250, 235, 215);
        }

        if (matriz.get(row).get(column) == 11) {
            return new Color(250, 182, 193);
        }

        if (matriz.get(row).get(column) == 12) {
            return new Color(147, 112, 219);
        }

        if (matriz.get(row).get(column) == 13) {
            return new Color(128, 0, 128);
        }

        if (Algoritmos.pertenceRaio(row, column, 4, roboI, roboJ)) {
            return colorOpacity(matriz, row, column);
        }

        if (matriz.get(row).get(column) == 0) {
            return Color.GREEN;
        }
        if (matriz.get(row).get(column) == 1) {
            return new Color(102, 51, 0);
        }
        if (matriz.get(row).get(column) == 2) {
            return Color.BLUE;
        }

        if (matriz.get(row).get(column) == 3) {
            return Color.ORANGE;
        }

        return Color.black;
    }
}
