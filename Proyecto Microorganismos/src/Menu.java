import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    JFrame ventana;

    JButton botonConfig;

    JButton botonInicio;

    JPanel panelBotones;

    public Menu(){
        ventana = new JFrame("Microorganismos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        agregarComponentes();
        ventana.setSize(500,70);
    }
    public void agregarComponentes(){
        botonInicio = new JButton("INICIAR JUEGO");
        botonConfig = new JButton("OPCIONES");
        botonInicio.setBackground(Color.black);
        botonInicio.setForeground(Color.white);
        botonConfig.setBackground(Color.black);
        botonConfig.setForeground(Color.white);
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,1,10,10));
        panelBotones.add(botonInicio);
        panelBotones.add(botonConfig);

        ventana.add(panelBotones, BorderLayout.CENTER);

        }

    public void actionPerformed(ActionEvent e) {}
}
