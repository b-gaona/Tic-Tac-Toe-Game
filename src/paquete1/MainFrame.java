package paquete1;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import controlador.TextPrompt;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends javax.swing.JFrame {

    TextPrompt placeHolder;
    public boolean tachita;

    public MainFrame() {
        initComponents();
        this.setSize(750, 450);
        this.setLocationRelativeTo(null);
        placeHolder = new TextPrompt("Type your name", insertarPlayer1);
        placeHolder = new TextPrompt("Type your name", insertarPlayer2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principalPanel = new javax.swing.JPanel();
        botonStartToPlay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        insertarPlayer2 = new javax.swing.JTextField();
        insertarPlayer1 = new javax.swing.JTextField();
        seleccionPlayer = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        simboloAJugar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TIC TAC TOE");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        principalPanel.setBackground(new java.awt.Color(153, 153, 153));
        principalPanel.setMaximumSize(new java.awt.Dimension(600, 350));
        principalPanel.setMinimumSize(new java.awt.Dimension(600, 350));
        principalPanel.setPreferredSize(new java.awt.Dimension(600, 350));
        principalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonStartToPlay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        botonStartToPlay.setText("START TO PLAY");
        botonStartToPlay.setFocusPainted(false);
        botonStartToPlay.setMaximumSize(new java.awt.Dimension(200, 30));
        botonStartToPlay.setMinimumSize(new java.awt.Dimension(200, 30));
        botonStartToPlay.setPreferredSize(new java.awt.Dimension(200, 30));
        botonStartToPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonStartToPlayActionPerformed(evt);
            }
        });
        principalPanel.add(botonStartToPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 220, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageOfPlayer.png"))); // NOI18N
        principalPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 120, 120));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageOfPlayer.png"))); // NOI18N
        principalPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 120, 120));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("PLAYER 2 (P2)");
        principalPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("PLAYER 1 (P1)");
        principalPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setText("Who starts the game?");
        principalPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        insertarPlayer2.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
        insertarPlayer2.setBorder(BorderFactory.createCompoundBorder(
            insertarPlayer2.getBorder(), 
            BorderFactory.createEmptyBorder(5, 8, 5, 5)));
    insertarPlayer2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            insertarPlayer2KeyTyped(evt);
        }
    });
    principalPanel.add(insertarPlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 240, 50));

    insertarPlayer1.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
    insertarPlayer1.setBorder(BorderFactory.createCompoundBorder(
        insertarPlayer1.getBorder(), 
        BorderFactory.createEmptyBorder(5, 8, 5, 5)));
insertarPlayer1.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyTyped(java.awt.event.KeyEvent evt) {
        insertarPlayer1KeyTyped(evt);
    }
    });
    principalPanel.add(insertarPlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 240, 50));

    seleccionPlayer.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
    seleccionPlayer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "      PLAYER 1", "      PLAYER 2" }));
    principalPanel.add(seleccionPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 275, 240, 40));

    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vs.png"))); // NOI18N
    principalPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 64, 64));

    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
    jLabel7.setText("Starts with:");
    principalPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, -1));

    simboloAJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redLittleStrike.png"))); // NOI18N
    simboloAJugar.setFocusPainted(false);
    tachita= true;
    simboloAJugar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            simboloAJugarActionPerformed(evt);
        }
    });
    principalPanel.add(simboloAJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 70, 70));

    getContentPane().add(principalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 450));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonStartToPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonStartToPlayActionPerformed
        secondAttempt seconsPanel = new secondAttempt(insertarPlayer1.getText(), insertarPlayer2.getText(), tachita, seleccionPlayer.getSelectedIndex() == 0 ? 1 : 5);
        try {
            this.setSize(1200, 600);
            this.setLocationRelativeTo(null);
            seconsPanel.setSize(this.getWidth(), this.getHeight());
            this.setContentPane(seconsPanel);
            this.revalidate();
            this.repaint();
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_botonStartToPlayActionPerformed

    private void simboloAJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simboloAJugarActionPerformed
        if (tachita) {
            simboloAJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blueLittleCircle.png")));
            tachita = false;
        } else {
            simboloAJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redLittleStrike.png")));
            tachita = true;
        }
    }//GEN-LAST:event_simboloAJugarActionPerformed

    private void insertarPlayer1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_insertarPlayer1KeyTyped
        if (insertarPlayer1.getText().length() >= 8) {
            evt.consume();
        }
        if (Character.isSpaceChar(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_insertarPlayer1KeyTyped

    private void insertarPlayer2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_insertarPlayer2KeyTyped
        if (insertarPlayer2.getText().length() >= 8) {
            evt.consume();
        }
        if (Character.isSpaceChar(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_insertarPlayer2KeyTyped

    private static void lookAndFeel() {
        Properties props = new Properties();
        props.put("logoString", " Menú");
        GraphiteLookAndFeel.setCurrentTheme(props);
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            lookAndFeel();
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonStartToPlay;
    private javax.swing.JTextField insertarPlayer1;
    private javax.swing.JTextField insertarPlayer2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel principalPanel;
    private javax.swing.JComboBox<String> seleccionPlayer;
    private javax.swing.JButton simboloAJugar;
    // End of variables declaration//GEN-END:variables
}
