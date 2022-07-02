package clases;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Robo {

    int posi;
    int posj;
    int baterias;
    int bracoSol;
    int bomba;
    int refrigeracao;
    int bracoPneu;
    List<Fabricas> fabricas;
    List<Integer> movimentos;
}
