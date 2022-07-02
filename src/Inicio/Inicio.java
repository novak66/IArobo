package Inicio;

import Aestrela.Aestrela;
import Algoritmos.Algoritmos;
import ComponentesGraficos.ComponentesGraficos;
import clases.Caminho;
import clases.Fabricas;
import clases.Robo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Inicio extends JFrame {

    final static Duration timeout = Duration.ofSeconds(30);
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ComponentesGraficos mapaGrafico = null;

    public Inicio(List<List<Integer>> matriz, Robo robo) {
        initComponents(matriz, robo);
        pack();
        setLocationRelativeTo(null);
    }

    public static Boolean acabar(Robo robo) {
        for (Fabricas fabrica : robo.getFabricas()) {
            if (fabrica.getBaterias() > 0
                    || fabrica.getBomba() > 0
                    || fabrica.getBracoPneu() > 0
                    || fabrica.getBracoSol() > 0
                    || fabrica.getRefrigeracao() > 0) {
                return false;
            }
        }

        return true;
    }

    public static Boolean caminhoJaPercorrido(Robo robo, int direcao) {
        for (Integer dir : robo.getMovimentos()) {
            if (dir.equals(direcao)) {
                return true;
            }
        }

        return false;
    }

    public static void comecar(List<List<Integer>> mat, Robo robo) {

        Inicio frame = new Inicio(mat, robo);
        frame.setVisible(true);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int tamMat = mat.get(0).size();
        int i = 0;
        int j = 0;

        while (!acabar(robo)) {

            List<Integer> pos = fabricaParaConsertar(robo);

            if (pos != null) {
                Caminho caminho = Aestrela.inicio(robo.getPosi(), robo.getPosj(), pos.get(0), pos.get(1), tamMat, mat);
                List<Caminho> destino = new ArrayList<>();

                while (caminho.getPai() != null) {
                    destino.add(caminho);
                    caminho = caminho.getPai();
                }

                Collections.reverse(destino);

                i = 0;
                j = destino.size();
                for (Caminho path : destino) {
                    i++;
                    robo.setPosi(path.getLinha());
                    robo.setPosj(path.getColuna());

                    frame.atualizarTela(mat, robo);
                    frame.setVisible(true);

                    try {
                        sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == j) {
                        consertarFabrica(robo, robo.getPosi(), robo.getPosj());
                        mat.get(robo.getPosi()).set(robo.getPosj(), 0);
                    }
                }
            } else {
                pos = Algoritmos.olharRaio(mat, robo.getPosi(), robo.getPosj(), tamMat, 4);
                if (pos != null) {
                    Caminho caminho = Aestrela.inicio(robo.getPosi(), robo.getPosj(), pos.get(0), pos.get(1), tamMat, mat);
                    List<Caminho> destino = new ArrayList<>();

                    while (caminho.getPai() != null) {
                        destino.add(caminho);
                        caminho = caminho.getPai();
                    }

                    Collections.reverse(destino);

                    i = 0;
                    j = destino.size();
                    for (Caminho path : destino) {
                        i++;
                        robo.setPosi(path.getLinha());
                        robo.setPosj(path.getColuna());

                        frame.atualizarTela(mat, robo);
                        frame.setVisible(true);

                        try {
                            sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (i == j) {
                            pegarItem(robo, mat.get(robo.getPosi()).get(robo.getPosj()));
                            mat.get(robo.getPosi()).set(robo.getPosj(), 0);
                        }
                    }
                } else {
                    escolherMovimento(robo);
                    frame.atualizarTela(mat, robo);
                    frame.setVisible(true);

                    try {
                        sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void consertarFabrica(Robo robo, int posI, int posJ) {
        int i = 0;

        for (Fabricas fabrica : robo.getFabricas()) {
            if (fabrica.getPosi() == posI && fabrica.getPosj() == posJ) {
                break;
            }
            i++;
        }

        robo.setBomba(robo.getBomba() - robo.getFabricas().get(i).getBomba());
        robo.setRefrigeracao(robo.getRefrigeracao() - robo.getFabricas().get(i).getRefrigeracao());
        robo.setBaterias(robo.getBaterias() - robo.getFabricas().get(i).getBaterias());
        robo.setBracoSol(robo.getBracoSol() - robo.getFabricas().get(i).getBracoSol());
        robo.setBracoPneu(robo.getBracoPneu() - robo.getFabricas().get(i).getBracoPneu());

        robo.getFabricas().get(i).setBomba(0);
        robo.getFabricas().get(i).setBracoPneu(0);
        robo.getFabricas().get(i).setBracoSol(0);
        robo.getFabricas().get(i).setRefrigeracao(0);
        robo.getFabricas().get(i).setBaterias(0);
    }

    public static void escolherMovimento(Robo robo) {
        if (robo.getMovimentos() != null) {
            while (true) {
                Random rand = new Random();

                int randomNum = rand.nextInt((4 - 1) + 1) + 1;

                if (randomNum == 1) {
                    robo.setPosi(robo.getPosi() - 1);
                    if (robo.getMovimentos().size() == 2) {
                        robo.getMovimentos().remove(0);
                        robo.getMovimentos().add(1);
                        break;
                    } else {
                        robo.getMovimentos().add(1);
                        break;
                    }
                } else if (randomNum == 2) {
                    robo.setPosi(robo.getPosj() + 1);
                    if (robo.getMovimentos().size() == 2) {
                        robo.getMovimentos().remove(0);
                        robo.getMovimentos().add(2);
                        break;
                    } else {
                        robo.getMovimentos().add(2);
                        break;
                    }
                } else if (randomNum == 3) {
                    robo.setPosi(robo.getPosi() + 1);
                    if (robo.getMovimentos().size() == 2) {
                        robo.getMovimentos().remove(0);
                        robo.getMovimentos().add(3);
                        break;
                    } else {
                        robo.getMovimentos().add(3);
                        break;
                    }
                } else {
                    robo.setPosi(robo.getPosj() - 1);
                    if (robo.getMovimentos().size() == 1) {
                        robo.getMovimentos().remove(0);
                        robo.getMovimentos().add(2);
                        break;
                    }
                }
            }
        } else {
            Random rand = new Random();

            Integer randomNum = rand.nextInt((4 - 1) + 1) + 1;
            List<Integer> aux = new ArrayList<>();
            aux.add(randomNum);

            robo.setMovimentos(aux);
        }
    }

    public static List<Integer> fabricaParaConsertar(Robo robo) {
        for (Fabricas fabrica : robo.getFabricas()) {
            if (fabricaPronta(fabrica, robo)) {
                List<Integer> lista = new ArrayList<>();
                lista.add(fabrica.getPosi());
                lista.add(fabrica.getPosj());

                return lista;
            }
        }

        return null;
    }

    public static Boolean fabricaPronta(Fabricas fabrica, Robo robo) {
        return robo.getBaterias() >= fabrica.getBaterias()
                && robo.getBomba() >= fabrica.getBomba()
                && robo.getBracoPneu() >= fabrica.getBracoPneu()
                && robo.getBracoSol() >= fabrica.getBracoSol()
                && robo.getRefrigeracao() >= fabrica.getRefrigeracao();
    }

    public static void pegarItem(Robo robo, int item) {

        if (item == 9) {
            robo.setBaterias(robo.getBaterias() + 1);
        }
        if (item == 10) {
            robo.setBracoSol(robo.getBracoSol() + 1);
        }

        if (item == 11) {
            robo.setBomba(robo.getBomba() + 1);
        }

        if (item == 12) {
            robo.setRefrigeracao(robo.getRefrigeracao() + 1);
        }

        if (item == 13) {
            robo.setBracoPneu(robo.getBracoPneu() + 1);
        }
    }

    private void atualizarTela(List<List<Integer>> matriz, Robo robo) {
        this.mapaGrafico = new ComponentesGraficos(matriz.get(0).size(), matriz.get(0).size(), matriz, robo);
        this.contentPane.add(this.mapaGrafico);
    }

    private void initComponents(List<List<Integer>> matriz, Robo robo) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 500));
        setResizable(false);

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        this.mapaGrafico = new ComponentesGraficos(42, 42, matriz, robo);
        this.contentPane.add(this.mapaGrafico);
    }
}
