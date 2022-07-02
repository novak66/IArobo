package ComponentesGraficos;

import javax.swing.*;
import java.awt.*;

class SquareLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public SquareLabel() {
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
}