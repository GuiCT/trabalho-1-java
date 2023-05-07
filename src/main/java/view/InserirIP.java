package view;

import java.awt.event.ActionEvent;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InserirIP extends JFrame {
    JLabel labelInsert = new JLabel("Insira o IP do servidor: ");
    JTextField textField = new JTextField();
    JButton button = new JButton("Entrar");

    public InserirIP() {
        setSize(480, 200);
        labelInsert.setBounds(130, 10, 200, 40);
        getContentPane().add(labelInsert);
        textField.setBounds(130, 50, 200, 40);
        getContentPane().add(textField);
        button.setBounds(130,100,200,40);
        button.addActionListener(this::insertIP);
        getContentPane().add(button);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void insertIP(ActionEvent evt) {
        try {
            String ip = textField.getText();
            InetAddress address = InetAddress.getByName(ip);
            JOptionPane.showMessageDialog(this, "IP inserido: " + address.getHostAddress());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao pegar o IP");
        }
    }
}
