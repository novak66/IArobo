package Aestrela;

import clases.Caminho;
import clases.Lista;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Aestrela {

    //h é pos atual até o destino
    //g é a pos atual até o pai
    //f a soma dos dois

    public static Caminho acharCaminho(Caminho caminho, Integer linha, Integer coluna) {
        caminho = tudoPraEsquerda(caminho);

        if (caminho.getLinha().equals(linha) && caminho.getColuna().equals(coluna)) {
            return caminho;
        }

        Integer linhaC = caminho.getLinha();
        Integer colunaC = caminho.getColuna();

        caminho = caminho.getPai();

        while (true) {

            if (caminho.getLinha().equals(linha) && caminho.getColuna().equals(coluna)) {
                return caminho;
            }

            int k = 0;
            if (caminho.getFilhos() != null) {
                for (Caminho caminhos : caminho.getFilhos()) {
                    if (caminhos.getLinha().equals(linhaC) && caminhos.getColuna().equals(colunaC)) {
                        break;
                    }
                    k++;
                }
            }

            if (caminho.getFilhos() != null && caminho.getFilhos().size() > k + 1) {
                caminho = caminho.getFilhos().get(k + 1);
                caminho = tudoPraEsquerda(caminho);
            } else {
                linhaC = caminho.getLinha();
                colunaC = caminho.getColuna();
                caminho = caminho.getPai();
            }
        }
    }

    public static Boolean acharElementoLista(List<Lista> lista, Integer linha, Integer coluna) {
        for (Lista list : lista) {
            if (list.getColuna().equals(coluna) && list.getLinha().equals(linha)) {
                return true;
            }
        }

        return false;
    }

    public static int acharMenor(List<Lista> lista) {
        int menor = lista.get(0).getF();
        int i = 0;
        int j = 0;
        for (Lista list : lista) {
            if (list.getF() < menor) {
                menor = list.getF();
                j = i;
            }
            i++;
        }

        return j;
    }

    public static int acharPosAtual(List<Lista> lista, Integer linha, Integer coluna) {
        int i = 0;
        for (Lista list : lista) {
            if (list.getLinha().equals(linha) && list.getColuna().equals(coluna)) {
                return i;
            }
            i++;
        }

        return i;
    }

    public static int adicionarLista(Integer linha,
                                     Integer coluna,
                                     Integer linhaDestino,
                                     Integer colunaDestino,
                                     List<Lista> listaAberta,
                                     Caminho caminho,
                                     List<List<Integer>> mat) {

        if (caminho.getLinha() == null) {
            Lista lista = new Lista();
            lista.setLinha(linha);
            lista.setColuna(coluna);
            lista.setH(0);
            lista.setG(0);
            lista.setF(0);

            listaAberta.add(lista);

            caminho.setColuna(lista.getColuna());
            caminho.setLinha(lista.getLinha());
            caminho.setH(lista.getH());
            caminho.setG(lista.getG());
            caminho.setF(lista.getF());
            return 0;
        }
        int custo = custoAtual(mat, caminho.getLinha().intValue(), caminho.getColuna().intValue());

        Lista lista = contidoListaAberta(listaAberta, linha, coluna);

        if (lista != null) {
            int posAtual = acharPosAtual(listaAberta, linha, coluna);

            lista = listaAberta.get(posAtual);

            if (lista.getG().intValue() > custo + caminho.getG().intValue()) {
                lista.setG(custo + caminho.getG().intValue());
                lista.setF(custo + lista.getH().intValue());

                Caminho caminhoPai = caminho;

                while (caminhoPai.getPai() != null) {
                    caminhoPai = caminhoPai.getPai();
                }

                Caminho caminhoAntigo = acharCaminho(caminhoPai, lista.getLinha(), lista.getColuna());

                caminhoAntigo.getPai().getFilhos().remove(caminhoAntigo);
                caminhoAntigo.setPai(caminho);
                caminhoAntigo.setG(lista.getG());
                caminhoAntigo.setF(lista.getF());

                if(caminho.getFilhos() == null) {
                    List<Caminho> c = new ArrayList<>();
                    c.add(caminhoAntigo);
                    caminho.setFilhos(c);
                } else {
                    caminho.getFilhos().add(caminhoAntigo);
                }
            }
        } else {
            Integer distanciaDestino;

            distanciaDestino = java.lang.Math.abs(linha.intValue() - linhaDestino.intValue()) * 2;
            distanciaDestino += java.lang.Math.abs(coluna.intValue() - colunaDestino.intValue()) * 3;

            lista = new Lista();
            lista.setLinha(linha);
            lista.setColuna(coluna);
            lista.setH(distanciaDestino);
            lista.setG(custo + caminho.getG().intValue());
            lista.setF(lista.getG().intValue() + lista.getH().intValue());

            listaAberta.add(lista);
            popularCaminho(caminho, lista);
        }
        return 0;
    }

    public static Boolean caminhoEqualDestino(Caminho caminho, Integer linhaDestino, Integer colunaDestino) {
        return caminho.getLinha().equals(linhaDestino) && caminho.getColuna().equals(colunaDestino);
    }

    public static Lista contidoListaAberta(List<Lista> listaAberta, Integer linha, Integer coluna) {
        Lista list = null;
        for (Lista lista : listaAberta) {
            if (lista.getLinha().equals(linha) && lista.getColuna().equals(coluna)) {
                list = lista;
            }
        }

        return list;
    }

    public static int custoAtual(List<List<Integer>> mat, int i, int j) {
        if (mat.get(i).get(j) == 0) {
            return 1;
        }

        return mat.get(i).get(j) * 5;
    }

    public static Boolean filhoNaoVisitado(Caminho caminhos) {
        for (Caminho caminho : caminhos.getFilhos()) {
            if (!caminho.getVisitado()) {
                return true;
            }
        }

        return false;
    }

    public static Caminho inicio(Integer linhaInicial,
                                 Integer colunaInicial,
                                 Integer linhaDestino,
                                 Integer colunaDestino,
                                 Integer tamMat,
                                 List<List<Integer>> mat) {

        List<Lista> listaAberta = new ArrayList<>();
        List<Lista> listaFechada = new ArrayList<>();

        Integer posListaAtual = 0;

        Caminho caminho = new Caminho();

        adicionarLista(linhaInicial, colunaInicial, linhaDestino, colunaDestino, listaAberta, caminho, mat);
        Caminho caminhoPai = caminho;
        while (true) {
            int linha = listaAberta.get(posListaAtual).getLinha();
            int coluna = listaAberta.get(posListaAtual).getColuna();

            removerListaAberta(linha, coluna, listaAberta, listaFechada);

            int ultimoElemento = listaFechada.size() - 1;
            Caminho caminhoAux = caminhoPai;

            caminho = acharCaminho(caminhoPai, linha, coluna);

            if (caminhoEqualDestino(caminho, linhaDestino, colunaDestino)) {
                return caminho;
            }
            caminhoPai = caminhoAux;

            linha = listaFechada.get(ultimoElemento).getLinha() - 1;
            coluna = listaFechada.get(ultimoElemento).getColuna();

            if (linha >= 0
                    && coluna >= 0
                    && !acharElementoLista(listaFechada, linha, coluna)) {

                adicionarLista(linha, coluna, linhaDestino, colunaDestino, listaAberta, caminho, mat);
            }

            linha = listaFechada.get(ultimoElemento).getLinha();
            coluna = listaFechada.get(ultimoElemento).getColuna() + 1;

            if (linha >= 0
                    && coluna < tamMat
                    && !acharElementoLista(listaFechada, linha, coluna)) {

                adicionarLista(linha, coluna, linhaDestino, colunaDestino, listaAberta, caminho, mat);
            }

            linha = listaFechada.get(ultimoElemento).getLinha() + 1;
            coluna = listaFechada.get(ultimoElemento).getColuna();

            if (linha < tamMat
                    && coluna < tamMat
                    && !acharElementoLista(listaFechada, linha, coluna)) {
                adicionarLista(linha, coluna, linhaDestino, colunaDestino, listaAberta, caminho, mat);
            }

            linha = listaFechada.get(ultimoElemento).getLinha();
            coluna = listaFechada.get(ultimoElemento).getColuna() - 1;

            if (linha >= 0
                    && coluna >= 0
                    && !acharElementoLista(listaFechada, linha, coluna)) {

                adicionarLista(linha, coluna, linhaDestino, colunaDestino, listaAberta, caminho, mat);
            }

            posListaAtual = acharMenor(listaAberta);
        }
    }

    public static void popularCaminho(Caminho caminho, Lista lista) {
        Caminho caminhoFilho = new Caminho();

        caminhoFilho.setLinha(lista.getLinha());
        caminhoFilho.setColuna(lista.getColuna());
        caminhoFilho.setG(lista.getG());
        caminhoFilho.setH(lista.getH());
        caminhoFilho.setF(lista.getF());

        caminhoFilho.setPai(caminho);

        List<Caminho> caminhos = new ArrayList<>();
        caminhos.add(caminhoFilho);

        if (caminho.getFilhos() != null) {
            caminho.getFilhos().add(caminhoFilho);
        } else {
            caminho.setFilhos(caminhos);
        }
    }

    public static Caminho proximoCaminho(Caminho caminhos) {
        caminhos.setVisitado(true);

        if (filhoNaoVisitado(caminhos.getPai())) {
            for (Caminho caminho : caminhos.getPai().getFilhos()) {
                if (!caminho.getVisitado()) {
                    return caminho;
                }
            }
        }

        while (true) {
            Integer linha = caminhos.getLinha();
            Integer coluna = caminhos.getColuna();

            caminhos = caminhos.getPai();

            int k = 0;

            for (Caminho caminho : caminhos.getFilhos()) {
                if (caminho.getLinha().equals(linha) && caminho.getColuna().equals(coluna)) {
                    break;
                }
                k++;
            }

            if (caminhos.getFilhos().size() > k + 1) {
                return tudoPraEsquerda(caminhos.getFilhos().get(k + 1));
            }

            if (caminhos.getPai() == null) {
                return tudoPraEsquerda(caminhos);
            }
        }
    }

    public static void removerListaAberta(Integer linha,
                                          Integer coluna,
                                          List<Lista> listaAberta,
                                          List<Lista> listaFechada) {
        Lista list = new Lista();
        for (Lista lista : listaAberta) {
            if (lista.getLinha().equals(linha) && lista.getColuna().equals(coluna)) {
                list = lista;
            }
        }

        listaAberta.remove(list);
        listaFechada.add(list);
    }

    public static Caminho tudoPraEsquerda(Caminho caminho) {
        while (caminho.getFilhos() != null && caminho.getFilhos().size() > 0) {
            caminho = caminho.getFilhos().get(0);
        }

        return caminho;
    }
}
