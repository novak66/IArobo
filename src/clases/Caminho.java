package clases;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Caminho implements Cloneable {

    private Integer linha;
    private Integer coluna;
    private Integer qtdFilhos;
    private List<Caminho> filhos;
    private Caminho pai;
    private Boolean visitado;
    private Integer f;
    private Integer h;
    private Integer g;


    @Override
    public Caminho clone() throws CloneNotSupportedException {
        return (Caminho) super.clone();
    }

}
