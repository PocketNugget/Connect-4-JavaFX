import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends Logic {
    public UI ()//constructor de la clase
    {
        //Se llama el tablero para empezar a construir la interfaz
        JFrame FrameConecta = new JFrame("Conecta 4");

        FrameConecta.setLayout(new BorderLayout(100, 200));


        jl1 = new JLabel();//creacion jlabel que muestra el numero pulsado



        mostrarBot();
        //Se empieza a construir la interfaz con todas las indicaciónes
        FrameConecta.add(jl1, BorderLayout.CENTER);
        FrameConecta.add(jp1, BorderLayout.CENTER);


        FrameConecta.setSize(1000, 800);
        FrameConecta.setLocation(300, 200);
        FrameConecta.setVisible(true);
        FrameConecta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end conect4Juego

}