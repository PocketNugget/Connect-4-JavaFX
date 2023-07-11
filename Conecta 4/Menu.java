import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{

    //Se implementan Botones y el frame principal
    private JButton aboutUSButton;
    JButton getAboutUSButton = new JButton();
    private JButton scoresButton;
    JButton getScoresButton = new JButton();
    private JButton Inicio;
    JButton getInicio = new JButton();
    private JPanel INICIAL;

 public int activo =0;
    public Menu() {
        setContentPane(INICIAL);
        setTitle("Bienvenido!");
        setSize(800,590);

        setVisible(true);

        //Segmento enfocado en escuchar los botones del menu principal dando resultado a sus acciónes correspondientes
        scoresButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent u) {
                JOptionPane.showMessageDialog(null,"Se basa en las reglas normales de conecta 4\n -Coloca fichas de tal manera de que tengas 4 en linea\n -Estas lineas pueden ser verticales horizontales o en diagonal\n -El juego termina hasta que un jugador ponga 4 fichas en linea" );
            }
        });
        aboutUSButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent u) {
            JOptionPane.showMessageDialog(null,"Creadores:\n Jesus Nava\n Erick Solis Zamacona \n Goben Diego Constantino Aguirre");
            }
        });
        //Cuando se presiona el botón de inicio se llama a el juego y se cierra la pestaña
        Inicio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent u) {
               activo = +1;
                if (activo == 1)

                {
                //Se llama el constructor
                    UI ui = new UI();
                    setVisible(false);
                }
            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
