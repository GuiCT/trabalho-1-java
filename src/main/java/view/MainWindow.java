/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author guilherme
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        panelTabuleiro = new javax.swing.JPanel();
        panelJogadas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaJogadas = new javax.swing.JTextArea();
        buttonDado = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuJogar = new javax.swing.JMenu();
        menuSerHost = new javax.swing.JMenuItem();
        menuEntrarPartida = new javax.swing.JMenuItem();
        menuRegras = new javax.swing.JMenu();
        menuVisualizarRegras = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelTabuleiroLayout = new javax.swing.GroupLayout(panelTabuleiro);
        panelTabuleiro.setLayout(panelTabuleiroLayout);
        panelTabuleiroLayout.setHorizontalGroup(
            panelTabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );
        panelTabuleiroLayout.setVerticalGroup(
            panelTabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        textAreaJogadas.setEditable(false);
        textAreaJogadas.setColumns(20);
        textAreaJogadas.setRows(5);
        jScrollPane1.setViewportView(textAreaJogadas);

        buttonDado.setText("Dado (D6)");
        buttonDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJogadasLayout = new javax.swing.GroupLayout(panelJogadas);
        panelJogadas.setLayout(panelJogadasLayout);
        panelJogadasLayout.setHorizontalGroup(
            panelJogadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJogadasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJogadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonDado, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJogadasLayout.setVerticalGroup(
            panelJogadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJogadasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDado)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        menuJogar.setText("Jogar");

        menuSerHost.setText("Ser host");
        menuJogar.add(menuSerHost);
        menuSerHost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "Host: " + Inet4Address.getLocalHost().getHostAddress());
                } catch (UnknownHostException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        menuEntrarPartida.setText("Entrar em partida");
        menuJogar.add(menuEntrarPartida);
        menuEntrarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame a = new JFrame("Host");
                a.setSize(480, 200);

                JLabel l = new JLabel("Insira o IP do servidor");
                l.setBounds(130, 10, 200, 40);
                a.add(l);

                JTextField t = new JTextField();
                t.setBounds(130, 50, 200, 40);
                a.add(t);

                // Adds a simple button
                JButton b = new JButton("Host");
                b.setBounds(130,100,200,40);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String ip = t.getText();
                            InetAddress address = InetAddress.getByName(ip);
                            String regex = "(\\d{1,3})"; // Regular expression to match 1-3 digits
//                            if (!ip.matches(regex + "\\." + regex + "\\." + regex + "\\." + regex)) {
//                                // Adds the current exception to the stack trace
//                            }
                            JOptionPane.showMessageDialog(null, "IP inserido: " + address.getHostAddress());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao pegar o IP");
                        }
                    }
                });
                a.add(b);

                // Takes as input the IP of the server
                // It also treats the exceptions



                a.setLayout(null);
                a.setVisible(true);
                a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        menuBar.add(menuJogar);

        menuRegras.setText("Regras");

        menuVisualizarRegras.setText("Visualizar");
        menuVisualizarRegras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVisualizarRegrasActionPerformed(evt);
            }
        });
        menuRegras.add(menuVisualizarRegras);

        menuBar.add(menuRegras);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJogadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabuleiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJogadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuVisualizarRegrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVisualizarRegrasActionPerformed
        String listaRegras = """
1 - Cada jogador possui 4 peões.
2 - Os peões de um determinado jogador podem ocupar o mesmo espaço.
3 - Peões de jogadores diferentes não podem ocupar o mesmo espaço.
  3.1 - Se o peão (A) de um determinado jogador ocupar o mesmo espaço que um peão (B) de outro, o peão B volta para o início.
4 - O jogo acaba quando um jogador colocar todos os peões no centro do tabuleiro.""";
        JOptionPane.showMessageDialog(rootPane, listaRegras);
    }//GEN-LAST:event_menuVisualizarRegrasActionPerformed

    private void buttonDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDadoActionPerformed
        int generatedNumber = 1 + (int)(Math.random() * 6);
        String currentText = textAreaJogadas.getText();
        currentText += Integer.toString(generatedNumber) + "\n";
        textAreaJogadas.setText(currentText);
    }//GEN-LAST:event_buttonDadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the System look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDado;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuEntrarPartida;
    private javax.swing.JMenu menuJogar;
    private javax.swing.JMenu menuRegras;
    private javax.swing.JMenuItem menuSerHost;
    private javax.swing.JMenuItem menuVisualizarRegras;
    private javax.swing.JPanel panelJogadas;
    private javax.swing.JPanel panelTabuleiro;
    private javax.swing.JTextArea textAreaJogadas;
    // End of variables declaration//GEN-END:variables
}
