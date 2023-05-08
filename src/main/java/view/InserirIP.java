package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import game.ControllerJogo;

public class InserirIP extends JFrame {
    private JLabel labelInsert = new JLabel("Insira o IP do servidor: ");
    private JTextField textField = new JTextField();
    private JButton button = new JButton("Entrar");
    private ControllerJogo controllerJogo;

    public InserirIP(ControllerJogo controllerJogo) {
        setSize(480, 200);
        labelInsert.setBounds(130, 10, 200, 40);
        getContentPane().add(labelInsert);
        textField.setBounds(130, 50, 200, 40);
        getContentPane().add(textField);
        button.setBounds(130, 100, 200, 40);
        button.addActionListener(this::insertIP);
        getContentPane().add(button);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.controllerJogo = controllerJogo;
    }

    private void insertIP(ActionEvent evt) {
        try {
            String ip = textField.getText();
            InetAddress address = InetAddress.getByName(ip);
            controllerJogo.joinMatch(address.getHostAddress());
            this.dispose();
            JOptionPane.showMessageDialog(this, "Conexão estabelecida com o host", "Sucesso!",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e, "Erro ao conectar", JOptionPane.ERROR_MESSAGE);
        }
    }
}
