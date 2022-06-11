
package paquete1;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;

public class secondAttempt extends javax.swing.JPanel {

    //Creamos el oyente de click. Este sirve para saber cuando se hace click sobre el objeto y cuando se suelta
    secondAttempt.ClickListener clickListener = new secondAttempt.ClickListener();
    //Creamos el oyente de drag listener. Este sirve para saber la ubicacion mientras el mouse esta presionado
    secondAttempt.DragListener dragListener = new secondAttempt.DragListener();
    Point prevPtLabel; //Indica la parte donde estaba antes de deslizar la etiqueta
    Point labelLocation; //Indica la posicion en X y Y de la etiqueta
    boolean dragValid = false; //Esta variable nos sirve para saber si fue presionado el click dentro de la imagen
    boolean areaAllowed = true; //Esta variable nos sirve para saber si esta dentro de los rangos de area permitidos
    boolean pressedImage = false; //Esta variable indica si ya se ha presionado en la imagen
    int maxWidth = 1200 - 13;
    int maxHeight = 600 - 36;
    Point locationOfAux; //Localizacion de la etiqueta auxiliar que es fija
    boolean spaceSelected = false; //Para saber si el espacio donde se dejo el mouse es permitido o no
    boolean tachita; //Para saber el simbolo, ya sea tacha o circulo
    public String namePlayer1, namePlayer2; //Guardamos los nombres de los jugadores
    public int playerThatStarts; //Para el constructor
    public ImageIcon firstIcon, secondIcon; //Guardamos la primer y segunda imagen (tacha y circulo)
    public boolean winning = false; //Tenemos un flag para indicar si se ha ganado o no
    static int winP1=0, winP2=0, draw=0; //Para los marcadores
    
    public secondAttempt(String player1, String player2, boolean redStrike, int playerWhoStarts) {
        initComponents(); //Inicializamos los componentes
        this.setSize(maxWidth, maxHeight); //Igualamos el tamaño del panel al de la ventana
        this.setBackground(null); //Hacemos el panel sin fondo
        this.addMouseListener(clickListener); //Agregamos al panel el Mouse listener con el objeto clickListener
        this.addMouseMotionListener(dragListener); //Agregamos al panel el MouseMotionListener con el objeto dragListener
        //Recibimos las variables pasadas por parametro para poder jugar
        this.namePlayer1 = player1;
        this.namePlayer2 = player2;
        this.tachita = redStrike;
        this.playerThatStarts = playerWhoStarts;
        //CAMBIAMOS LAS ETIQUETAS SI ES QUE HAY NOMBRES
        if(namePlayer1.isEmpty()){
            etiquetaNombrePlayer1.setText("Player 1:");
            etiquetaNombrePlayer1.setBounds(1015, 140, 120, 40);
            indicadorTurno.setBounds(80, 100, 450, 70);
        }else{
            etiquetaNombrePlayer1.setText(namePlayer1+":");
            etiquetaNombrePlayer1.setFont(new Font("Tahoma", 0, 18));
        }
        if(namePlayer2.isEmpty()){
            etiquetaNombrePlayer2.setText("Player 2:");
            etiquetaNombrePlayer2.setBounds(1015, 200, 120, 40);
            indicadorTurno.setBounds(80, 100, 450, 70);
        }else{
            etiquetaNombrePlayer2.setText(namePlayer2+":");
            etiquetaNombrePlayer2.setFont(new Font("Tahoma", 0, 18));
        }
        vecesGanadasP1.setText("  "+String.valueOf(winP1));
        vecesGanadasP2.setText("  "+String.valueOf(winP2));
        vecesEmpatadas.setText("  "+String.valueOf(draw));
        
        if (tachita) {
            etiquetaAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png")));
            etiquetaMovible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png")));
            checkTurnOfPlayer();
        } else {
            etiquetaAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png")));
            etiquetaMovible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png")));
            checkTurnOfPlayer();
        }
    }

    private class ClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            labelLocation = etiquetaMovible.getLocation();
            prevPtLabel = e.getPoint();
            dragValid = (e.getPoint().getX() > labelLocation.getX())
                    && (e.getPoint().getX() < (labelLocation.getX() + etiquetaMovible.getWidth()))
                    && (e.getPoint().getY() > labelLocation.getY())
                    && (e.getPoint().getY() < (labelLocation.getY() + etiquetaMovible.getHeight()));
            if (dragValid) {
                pressedImage = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (tachita) {
                firstIcon = new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png"));
                secondIcon = new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png"));
            } else {
                firstIcon = new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png"));
                secondIcon = new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png"));
            }
            locationOfAux = etiquetaAux.getLocation();
            
            //Checa si esta en la primer fila
            checkPlacing(panel1, espacio00);
            checkPlacing(panel2, espacio01);
            checkPlacing(panel3, espacio02);
            //Checa si esta en la segunda fila
            checkPlacing(panel4, espacio10);
            checkPlacing(panel5, espacio11);
            checkPlacing(panel6, espacio12);
            //checa si esta en la tercer fila
            checkPlacing(panel7, espacio20);
            checkPlacing(panel8, espacio21);
            checkPlacing(panel9, espacio22);
            //Si es que no entra a nimguno, que vuelva a su estado original
            returnTheSymbolToTheInitialPlace(panel9);
            //Verifica si el juego acabo o no
            if (!winning) {
                stopTheGame();
            }
        }
    }

    private void checkColumnsAndRows(javax.swing.JLabel espacio1, javax.swing.JLabel espacio2, javax.swing.JLabel espacio3) {
        //Siempre y cuando todas las etiquetas tengan imagenes
        if (espacio1.getIcon() != null && espacio2.getIcon() != null && espacio3.getIcon() != null) {
            //Obtenemos la ruta de cada una de las etiquetas
            String ruta1 = espacio1.getIcon().toString();
            String ruta2 = espacio2.getIcon().toString();
            String ruta3 = espacio3.getIcon().toString();
            //Comparamos que todas las imagenes sean iguales
            if (ruta1.equals(ruta2) && ruta1.equals(ruta3)) {
                if (playerThatStarts == 1) {
                    if (namePlayer2.isEmpty()) {
                        indicadorTurno.setText("Player 2 WON!");
                    } else {
                        indicadorTurno.setText(namePlayer2 + " WON!");
                    }
                    winP2++;
                    if(winP2<=9){
                        vecesGanadasP2.setText("  "+String.valueOf(winP2));
                    }else{
                        vecesGanadasP2.setText(" "+String.valueOf(winP2));
                    }
                } else {
                    if (namePlayer1.isEmpty()) {
                        indicadorTurno.setText("Player 1 WON!");
                    } else {
                        indicadorTurno.setText(namePlayer1 + " WON!");
                    }
                    winP1++;
                    if(winP1<=9){
                        vecesGanadasP1.setText("  "+String.valueOf(winP1));
                    }else{
                        vecesGanadasP1.setText(" "+String.valueOf(winP1));
                    }
                }
                javax.swing.ImageIcon gifCelebration = new javax.swing.ImageIcon(getClass().getResource("/images/celebration.gif"));
                etiquetaGif.setIcon(new ImageIcon(gifCelebration.getImage().getScaledInstance(etiquetaGif.getWidth(), etiquetaGif.getHeight(), Image.SCALE_REPLICATE)));
                winning = true; //Volvemos true la variable que indica si ya se gano o no
            }
        }
    }

    private void stopTheGame() {
        //CHECAR LAS FILAS A VER SI SE GANO
        checkColumnsAndRows(espacio00, espacio01, espacio02);
        checkColumnsAndRows(espacio10, espacio11, espacio12);
        checkColumnsAndRows(espacio20, espacio21, espacio22);
        //CHECAR LAS COLUMNAS A VER SI SE GANO
        checkColumnsAndRows(espacio00, espacio10, espacio20);
        checkColumnsAndRows(espacio01, espacio11, espacio21);
        checkColumnsAndRows(espacio02, espacio12, espacio22);
        //CHECAR LAS DIAGONALES A VER SI SE GANO
        checkColumnsAndRows(espacio00, espacio11, espacio22);
        checkColumnsAndRows(espacio20, espacio11, espacio02);
        //ESTE ES PARA EMPATE
        if (espacio00.getIcon() != null && espacio01.getIcon() != null && espacio02.getIcon() != null
                && espacio10.getIcon() != null && espacio11.getIcon() != null && espacio12.getIcon() != null
                && espacio20.getIcon() != null && espacio21.getIcon() != null && espacio22.getIcon() != null && !winning) {
            javax.swing.ImageIcon gifDraw = new javax.swing.ImageIcon(getClass().getResource("/images/pleaseNo.gif"));
            etiquetaGif.setIcon(new ImageIcon(gifDraw.getImage().getScaledInstance(etiquetaGif.getWidth(), etiquetaGif.getHeight(), Image.SCALE_REPLICATE)));
            draw++;
            indicadorTurno.setText("Oh no, it's a draw!");
            if (draw <= 9) {
                vecesEmpatadas.setText("  " + String.valueOf(draw));
            } else {
                vecesEmpatadas.setText(" "+String.valueOf(draw));
            }
        }
    }

    private void returnTheSymbolToTheInitialPlace(javax.swing.JPanel panel) {
        Point locationOfPanel = panel.getLocation();
        spaceSelected = (labelLocation.getX() >= locationOfPanel.getX())
                && ((labelLocation.getX() + etiquetaMovible.getWidth()) <= (locationOfPanel.getX() + panel.getWidth()))
                && (labelLocation.getY() >= locationOfPanel.getY())
                && ((labelLocation.getY() + etiquetaMovible.getHeight()) <= (locationOfPanel.getY() + panel.getHeight()));
        if (!spaceSelected) {
            etiquetaMovible.setLocation(locationOfAux);
        }
    }

    private void checkPlacing(javax.swing.JPanel panel, javax.swing.JLabel espacio) {
        if (espacio.getIcon() == null) { //Si no contiene ninguna imagen el espacio de label es que no tiene tacha ni circulo
            Point locationOfPanel = panel.getLocation();
            spaceSelected = (labelLocation.getX() >= locationOfPanel.getX() - 50)
                    && ((labelLocation.getX() + etiquetaMovible.getWidth()) <= (locationOfPanel.getX() + panel.getWidth() + 50))
                    && (labelLocation.getY() >= locationOfPanel.getY() - 50)
                    && ((labelLocation.getY() + etiquetaMovible.getHeight()) <= (locationOfPanel.getY() + panel.getHeight() + 50));
            if (pressedImage && spaceSelected) {
                changePlayer();
                changeSymbol();
                etiquetaMovible.setIcon(null);
                espacio.setIcon(firstIcon);
                etiquetaMovible.setLocation(locationOfAux);
                etiquetaMovible.setIcon(secondIcon);
                etiquetaAux.setIcon(secondIcon);
            }
        }
    }

    private void checkTurnOfPlayer() {
        if (playerThatStarts == 1) {
            if (namePlayer1.isEmpty()) {
                indicadorTurno.setText("Turn of player 1");
            } else {
                indicadorTurno.setText("Turn of " + namePlayer1);
            }
        } else {
            if (namePlayer2.isEmpty()) {
                indicadorTurno.setText("Turn of player 2");
            } else {
                indicadorTurno.setText("Turn of " + namePlayer2);
            }
        }
    }

    private void changePlayer() {
        if (playerThatStarts == 1) {
            if (namePlayer2.isEmpty()) {
                indicadorTurno.setText("Turn of player 2");
            } else {
                indicadorTurno.setText("Turn of " + namePlayer2);
            }
            playerThatStarts = 2;
        } else {
            if (namePlayer1.isEmpty()) {
                indicadorTurno.setText("Turn of player 1");
            } else {
                indicadorTurno.setText("Turn of " + namePlayer1);
            }
            playerThatStarts = 1;
        }
    }

    private void changeSymbol() {
        tachita = !tachita;
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            labelLocation = etiquetaMovible.getLocation();
            if (dragValid && areaAllowed && !winning) { //Siempre y cuando sea valido 
                Point currentPt = e.getPoint(); //El currentPt es la variable que nos indica la posicion exacta a cada instante.
                etiquetaMovible.setLocation((int) (labelLocation.getX() + (currentPt.getX() - prevPtLabel.getX())),
                        (int) (labelLocation.getY() + (currentPt.getY() - prevPtLabel.getY())));
                prevPtLabel = currentPt;
                repaint();
            }
            
            areaAllowed = (0 <= labelLocation.getX())
                    && (maxWidth >= (labelLocation.getX() + etiquetaMovible.getWidth()))
                    && (0 <= labelLocation.getY())
                    && ((maxHeight) >= (labelLocation.getY() + etiquetaMovible.getHeight()));

            if (labelLocation.getX() <= 0) {
                etiquetaMovible.setLocation(1, (int) labelLocation.getY());
                repaint();
            }
            if ((labelLocation.getX() + etiquetaMovible.getWidth()) >= maxWidth) {
                etiquetaMovible.setLocation((maxWidth - etiquetaMovible.getWidth() - 1), (int) labelLocation.getY());
                repaint();
            }
            if (labelLocation.getY() <= 0) {
                etiquetaMovible.setLocation((int) labelLocation.getX(), 1);
                repaint();
            }
            if ((labelLocation.getY() + etiquetaMovible.getHeight()) >= (maxHeight)) {
                etiquetaMovible.setLocation((int) labelLocation.getX(), (maxHeight - etiquetaMovible.getHeight() - 1));
                repaint();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaGif = new javax.swing.JLabel();
        etiquetaMovible = new javax.swing.JLabel();
        indicadorTurno = new javax.swing.JLabel();
        etiquetaAux = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        espacio00 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        espacio01 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        espacio02 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        espacio10 = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        espacio11 = new javax.swing.JLabel();
        panel6 = new javax.swing.JPanel();
        espacio12 = new javax.swing.JLabel();
        panel7 = new javax.swing.JPanel();
        espacio20 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        espacio21 = new javax.swing.JLabel();
        panel9 = new javax.swing.JPanel();
        espacio22 = new javax.swing.JLabel();
        botonTryAgain = new javax.swing.JButton();
        etiquetaNombrePlayer1 = new javax.swing.JLabel();
        vecesGanadasP1 = new javax.swing.JTextField();
        etiquetaNombrePlayer2 = new javax.swing.JLabel();
        vecesGanadasP2 = new javax.swing.JTextField();
        etiquetaEmpate = new javax.swing.JLabel();
        vecesEmpatadas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setLayout(null);
        add(etiquetaGif);
        etiquetaGif.setBounds(40, 190, 400, 220);

        etiquetaMovible.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        etiquetaMovible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png"))); // NOI18N
        etiquetaMovible.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(etiquetaMovible);
        etiquetaMovible.setBounds(160, 210, 150, 150);

        indicadorTurno.setFont(new java.awt.Font("Tahoma", 0, 45)); // NOI18N
        indicadorTurno.setText("TURNO DEL JUGADOR");
        add(indicadorTurno);
        indicadorTurno.setBounds(30, 100, 450, 70);

        etiquetaAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png"))); // NOI18N
        add(etiquetaAux);
        etiquetaAux.setBounds(160, 210, 150, 150);

        panel1.setBackground(new java.awt.Color(153, 153, 153));

        espacio00.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio00.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel1.add(espacio00);

        add(panel1);
        panel1.setBounds(490, 10, 160, 160);

        panel2.setBackground(new java.awt.Color(153, 153, 153));

        espacio01.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio01.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel2.add(espacio01);

        add(panel2);
        panel2.setBounds(660, 10, 160, 160);

        panel3.setBackground(new java.awt.Color(153, 153, 153));

        espacio02.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio02.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel3.add(espacio02);

        add(panel3);
        panel3.setBounds(830, 10, 160, 160);

        panel4.setBackground(new java.awt.Color(153, 153, 153));

        espacio10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel4.add(espacio10);

        add(panel4);
        panel4.setBounds(490, 180, 160, 160);

        panel5.setBackground(new java.awt.Color(153, 153, 153));

        espacio11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel5.add(espacio11);

        add(panel5);
        panel5.setBounds(660, 180, 160, 160);

        panel6.setBackground(new java.awt.Color(153, 153, 153));

        espacio12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel6.add(espacio12);

        add(panel6);
        panel6.setBounds(830, 180, 160, 160);

        panel7.setBackground(new java.awt.Color(153, 153, 153));

        espacio20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel7.add(espacio20);

        add(panel7);
        panel7.setBounds(490, 350, 160, 160);

        panel8.setBackground(new java.awt.Color(153, 153, 153));

        espacio21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel8.add(espacio21);

        add(panel8);
        panel8.setBounds(660, 350, 160, 160);

        panel9.setBackground(new java.awt.Color(153, 153, 153));

        espacio22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        espacio22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel9.add(espacio22);

        add(panel9);
        panel9.setBounds(830, 350, 160, 160);

        botonTryAgain.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        botonTryAgain.setText("TRY AGAIN");
        botonTryAgain.setFocusPainted(false);
        botonTryAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTryAgainActionPerformed(evt);
            }
        });
        add(botonTryAgain);
        botonTryAgain.setBounds(90, 460, 300, 50);

        etiquetaNombrePlayer1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        etiquetaNombrePlayer1.setText("Player 1:");
        add(etiquetaNombrePlayer1);
        etiquetaNombrePlayer1.setBounds(1000, 140, 120, 40);

        vecesGanadasP1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        add(vecesGanadasP1);
        vecesGanadasP1.setBounds(1120, 140, 60, 40);

        etiquetaNombrePlayer2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        etiquetaNombrePlayer2.setText("Player 2:");
        add(etiquetaNombrePlayer2);
        etiquetaNombrePlayer2.setBounds(1000, 200, 120, 40);

        vecesGanadasP2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        add(vecesGanadasP2);
        vecesGanadasP2.setBounds(1120, 200, 60, 40);

        etiquetaEmpate.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        etiquetaEmpate.setText("DRAWS:");
        add(etiquetaEmpate);
        etiquetaEmpate.setBounds(1030, 270, 160, 40);

        vecesEmpatadas.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        add(vecesEmpatadas);
        vecesEmpatadas.setBounds(1070, 320, 60, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("WINS:");
        add(jLabel1);
        jLabel1.setBounds(1050, 70, 110, 40);
        add(jSeparator1);
        jSeparator1.setBounds(1010, 380, 170, 15);
        add(jSeparator2);
        jSeparator2.setBounds(1010, 260, 170, 15);
        add(jSeparator3);
        jSeparator3.setBounds(1010, 120, 170, 15);
        add(jSeparator4);
        jSeparator4.setBounds(1010, 60, 170, 15);
    }// </editor-fold>//GEN-END:initComponents

    private void botonTryAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTryAgainActionPerformed
        winning= false;
        if (tachita) {
            etiquetaAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png")));
            etiquetaMovible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redStrike.png")));
            checkTurnOfPlayer();
        } else {
            etiquetaAux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png")));
            etiquetaMovible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueCircle.png")));
            checkTurnOfPlayer();
        }
        espacio00.setIcon(null);
        espacio01.setIcon(null);
        espacio02.setIcon(null);
        espacio10.setIcon(null);
        espacio11.setIcon(null);
        espacio12.setIcon(null);
        espacio20.setIcon(null);
        espacio21.setIcon(null);
        espacio22.setIcon(null);
        etiquetaGif.setIcon(null);
    }//GEN-LAST:event_botonTryAgainActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonTryAgain;
    private javax.swing.JLabel espacio00;
    private javax.swing.JLabel espacio01;
    private javax.swing.JLabel espacio02;
    private javax.swing.JLabel espacio10;
    private javax.swing.JLabel espacio11;
    private javax.swing.JLabel espacio12;
    private javax.swing.JLabel espacio20;
    private javax.swing.JLabel espacio21;
    private javax.swing.JLabel espacio22;
    private javax.swing.JLabel etiquetaAux;
    private javax.swing.JLabel etiquetaEmpate;
    private javax.swing.JLabel etiquetaGif;
    private javax.swing.JLabel etiquetaMovible;
    private javax.swing.JLabel etiquetaNombrePlayer1;
    private javax.swing.JLabel etiquetaNombrePlayer2;
    private javax.swing.JLabel indicadorTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel panel9;
    private javax.swing.JTextField vecesEmpatadas;
    private javax.swing.JTextField vecesGanadasP1;
    private javax.swing.JTextField vecesGanadasP2;
    // End of variables declaration//GEN-END:variables
}