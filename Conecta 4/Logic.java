import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Logic  {
    static boolean ganador;
    JButton [][]arrBtn = new JButton[6][7];//matriz de botones
    int matriz2[][]= new int[6][7]; // se creo una 2 matriz para en una tener la matriz de puros botones y combinar otra
    // con la pura lógica

    JPanel jp1;
    JLabel jl1;
    int turno = 1; //asignamos 1 al contador, sirve para diferenciar entre el turno del jugador

    public void mostrarBot() {//esta función permite mostrar la matriz de botones
        jp1 = new JPanel(new GridLayout(6, 7, 1, 3));


        //los for que están por presentarse tienen como función el crear un total de 42 botones
        //en donde haya 7 columnas (j) y 6 filas (i).

        for(int i=0 ; i <6; i++) //ciclo para crear la matriz de botones (vertical)
        {
            for (int j =0  ; j < 7; j++) //ciclo para crear la matriz de botones (horizontal)
            {
                Handler handler = new Handler(); //declaramos handler para escuchar eventos

                arrBtn[i][j] = new JButton();
                arrBtn[i][j].setBackground(Color.gray); // por default todos los botones son grises
                arrBtn[i][j].setMargin(new Insets(15, 15, 15, 15));
                arrBtn[i][j].addActionListener(handler);
                jp1.add(arrBtn[i][j]);
                matriz2[i][j]=0;
            }//end for

        }//end for

    }//fin ciclo

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = 0; //variables que usamos posteriormente en la clase ganador
            int y = 0;


            //el for que está por presentarse tiene la función de detectar cuando el usuario de click a cualquier boton dentro
            // del tablero y manadarlo en este caso a la ultima posición de la columna en que el boton es clickeado
            // que es 6. La lógica de esto es que se recorre k hasta llegar a la posición número 6 de cada columna.

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (e.getSource() == arrBtn[i][j]) {

                        int k = 6;

                        do {
                            k--;

                        }//end doWhile
                        while (matriz2[k][j] != 0 & k != 6);

                        // la lógica de los "if" que estan por presentarse es determinar la condición para cuando es turno
                        // del jugador 1 y del jugador 2.
                        if (matriz2[k][j] == 0) {
                            if (turno % 2 == 0) {
                                arrBtn[k][j].setBackground(Color.yellow);

                                matriz2[k][j] = 1;

                            }//end if
                            else {
                                arrBtn[k][j].setBackground(Color.red);

                                matriz2[k][j] = 2;
                            } //end else
                            turno++;

                            //estas variables nuevas se usan para mantener las mismas variables pero dentro de otra clase
                            x = j;
                            y = k;

                            Ganador n = new Ganador();
                            n.add(y, x); // agregamos varialbles usadas en la clase ganador

                        }//end if

                    }//end if

                }//end for

            } //end for

        }//end action performed
    }


    public void EmpezardeNuevo() //función para poder reiniciar una partida
    {
        //este for se utiliza para determinar que cuando exista un ganador y los botones pintados que existan dentro del tablero,
        // en lugar de abrir una nueva ventana, los mismos botones dentro de la misma matriz de botones se pintan
        for(int i=0 ; i <6; i++)
        {
            for (int j=0 ; j <7; j++)
            {
                arrBtn[i][j].setBackground(Color.gray);
                matriz2[i][j]=0;
                ganador=false;

            }//end for

        }//end for
        turno=1;
    }//end EmpezardeNuevo

    class Ganador
    {



        void add(int y, int x) //void .add sirve para poder ocupar otras variables de otras clases
        {
            int ganar1=0;
            int ganar2=0;

            // comienza ganador horizontal
            //tomamos la coordenada Y (altura) y escaneamos boton por boton de izquierda a derecha con un contador de llega hasta 4 para cada jugador
            for(int j=0;j<7;j++)
            {
                if(matriz2[y][j]!=0)
                {
                    if(matriz2[y][j]==1)
                    {
                        ganar1++;
                    }//end if
                    else ganar1=0;

                    if(ganar1==4)
                    {
                        ganador=true;
                        int input = JOptionPane.showConfirmDialog(null, "Gana Amarillo \n Desea seguir jugando", "Select an Option...",
                                JOptionPane.YES_NO_OPTION);
                        if (input == JOptionPane.YES_OPTION)
                        {
                            EmpezardeNuevo();
                        }//end if
                        if(input == JOptionPane.NO_OPTION)
                        {
                            System.exit(0);
                        }//end if

                    }//end if
                    if(ganador!=true)
                    {
                        if(matriz2[y][j]==2)
                        {
                            ganar2++;
                        }//end if
                        else ganar2=0;
                        if(ganar2==4)
                        {
                            ganador=true;

                            int input = JOptionPane.showConfirmDialog(null, "Gana Rojo \n Desea seguir jugando",
                                    "Select an Option...",
                                    JOptionPane.YES_NO_OPTION);
                            if(input == JOptionPane.YES_OPTION)
                            {
                                EmpezardeNuevo();
                            }//end if
                            if(input == JOptionPane.NO_OPTION)
                            {
                                System.exit(0);
                            }//end if

                        }//end if

                    }//end if

                }//end if
                else
                {
                    ganar1=0;
                    ganar2=0;
                }//end else
            }//end for

            // end ganador horizontal


            // ----comienza ganador vertical

            ///La lógica dentro del ganador vertical es que tomamos la coordenada X (columna) y escaneamos boton por boton de arriba
            // hacia abajo con un contador de llega hasta 4 para cada jugador. En este caso solo se toma en cuenta de abajo hacia arriba,
            //si se juntan 4 fichas de un mismo color, el jugador gana.

            ganar1=0;
            ganar2 = 0;

            for(int i=0;i<6;i++)
            {
                //esto sirve para que cuando se tira una ficha y la de arriba del mismo color y así hasta lograr 4 colors/fichas iguales
                //En dado caso que se ponga una ficha la de arriba sea diferente, ganar = 0 lo que es que se reinicia la cuenta de juntar 4
                if(matriz2[i][x]!=0)
                {
                    if(matriz2[i][x]==1)
                    {
                        ganar1++;
                    }//end if
                    else ganar1=0;

                    if(ganar1==4)
                    {
                        ganador=true;
                        int input = JOptionPane.showConfirmDialog(null, "Gana Amarillo \n Desea seguir jugando", "Select an Option...",
                                JOptionPane.YES_NO_OPTION);
                        if (input == JOptionPane.YES_OPTION)
                        {
                            EmpezardeNuevo();
                        }//end if
                        if(input == JOptionPane.NO_OPTION)
                        {
                            System.exit(0);
                        }//end if

                    }//end if
                    if(ganador!=true)
                    {
                        if(matriz2[i][x]==2)
                        {
                            ganar2++;
                        }//end if
                        else ganar2=0;
                        if(ganar2==4)
                        {
                            ganador=true;
                            int input = JOptionPane.showConfirmDialog(null, "Gana Rojo \n Desea seguir jugando", "Select an Option...",
                                    JOptionPane.YES_NO_OPTION);
                            if(input == JOptionPane.YES_OPTION)
                            {
                                EmpezardeNuevo();
                            }//end if
                            if(input == JOptionPane.NO_OPTION)
                            {
                                System.exit(0);
                            }//end if

                        }//end if

                    }//end if

                }//end if
                else
                {
                    ganar1=0;
                    ganar2=0;
                }//end else

            } // end for

            // end ganador en vertical

            //comienza ganador en diagonales
            //
            // Para lograr determinar el ganador en diagonal, tenemos que empezar desde la coordenada 0,0 a escanear de iquierda
            // a derecha todas las diagonales. Si se junntan 4 fichas de arriba hacia abajo o viceversa en forma diagonal, existe un ganador


            ganar1 = 0;
            ganar2 = 0;

            int a=y;
            int b=x;
            while(b>0 && a>0)
            {
                a--;
                b--;
            }//end while
            while(b<7 && a<6)
            {
                if(matriz2[a][b]!=0)
                {
                    if(matriz2[a][b]==1)
                    {
                        ganar1++;
                    }//end if
                    else ganar1=0;
                    if(ganar1==4)
                    {
                        ganador=true;
                        int input = JOptionPane.showConfirmDialog(null, "Gana Amarillo \n Desea seguir jugando", "Select an Option...",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (input == JOptionPane.YES_OPTION)
                        {
                            EmpezardeNuevo();
                        }//end if
                    }//end if
                    if(ganador!=true)
                    {
                        if(matriz2[a][b]==2)
                        {
                            ganar2++;
                        }//end if

                        else ganar2=0;
                        if(ganar2==4)
                        {
                            ganador=true;
                            int input = JOptionPane.showConfirmDialog(null, "Gana Rojo \n Desea seguir jugando", "Select an Option...",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if(input == JOptionPane.YES_OPTION)
                            {
                                EmpezardeNuevo();
                            }//end if

                        }//end if
                    }//end if
                }
                else
                {
                    ganar1=0;
                    ganar2=0;
                }//end else
                a++;
                b++;
            }// end while ganador diagonal de izquierda a derecha


            //Ganar de derecha a izquierda en Diagonal


            // Para lograr determinar el ganador en diagonal, tenemos que empezar desde la coordenada 0,0 a escanear de iquierda
            // a derecha todas las diagonales. Si se junntan 4 fichas de arriba hacia abajo o viceversa en forma diagonal, existe un ganador
            ganar1 = 0;
            ganar2 = 0;
            int a2=y;
            int b2=x;
            while(b2<6 && a2>6)
            {
                a2--;
                b2++;
            }//end while
            while(b2>-1 && a2<6)
            {
                if(matriz2[a2][b2]!=0)
                {
                    if(matriz2[a2][b2]==1)
                    {
                        ganar1++;
                    }//end if
                    else ganar1=0;
                    if(ganar1==4)
                    {
                        ganador=true;
                        int input = JOptionPane.showConfirmDialog(null, "Gana Amarillo \n Desea seguir jugando", "Select an Option...",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (input == JOptionPane.YES_OPTION)
                        {
                            EmpezardeNuevo();
                        }//end if
                    }//end if
                    if(ganador!=true)
                    {
                        if(matriz2[a2][b2]==2)
                        {
                            ganar2++;
                        }//end if

                        else ganar2=0;
                        if(ganar2==4)
                        {
                            ganador=true;
                            int input = JOptionPane.showConfirmDialog(null, "Gana Rojo \n Desea seguir jugando", "Select an Option...",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if(input == JOptionPane.YES_OPTION)
                            {
                                EmpezardeNuevo();
                            }//end if

                        }//end if
                    }//end if
                }//end if
                else
                {
                    ganar1=0;
                    ganar2=0;
                }//end else
                a2++;
                b2--;
            }// end while ganador diagonal de  derecha a izquierda

            //Determinar empate

            // Esta función tiene como objetivo determinar cuando los 42 botones del tablero fueron clickeados, aparece un 
            // mensaje de que el marcador fue EMPATE, esto se logra siguiendo la lógica del juego. 
            if(turno==43)
            {
                int input = JOptionPane.showConfirmDialog(null, "EMPATE \n Desea seguir jugando", "Select an Option...",
                        JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION)
                {
                    EmpezardeNuevo();
                }//end if
                if(input == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }//end if

            }//end if
            ganador=false;






        }//end void.add
    }//end class ganador

}//end class