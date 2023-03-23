import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Mapa{
    JFrame ventana;

    JButton botonConfig;

    JButton botonInicio;

    JPanel panelBotones;
    Mapa x = new Mapa();
    OrganismoBasico o1 = new OrganismoBasico(1,2,2,2);
    OrganismoBasico o2 = new OrganismoBasico(1,2,2,2);
    OrganismoVelocidad o3 = new OrganismoVelocidad(1,2,2,2);
    OrganismoVision o4 = new OrganismoVision(1,2,2,2);

    public Menu(){
        ventana = new JFrame("Microorganismos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        agregarComponentes(x);
        ventana.setSize(500,70);
    }
    public void iniciarMapa(){
        x.LlenarMapa();
        x.mapaEnteros();
        x.crearMapa();
    }

    public void agregarComponentes(Mapa x){
        botonInicio = new JButton("INICIAR JUEGO");
        botonInicio.setBackground(Color.black);
        botonInicio.setForeground(Color.white);
        botonInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                iniciarMapa();
            }
        });

        botonConfig = new JButton("OPCIONES");
        botonConfig.setBackground(Color.black);
        botonConfig.setForeground(Color.white);
        botonConfig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presionó el botón Opciones");
            }
        });

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,1,10,10));
        panelBotones.add(botonInicio);
        panelBotones.add(botonConfig);

        ventana.add(panelBotones, BorderLayout.CENTER);

    }
    }
