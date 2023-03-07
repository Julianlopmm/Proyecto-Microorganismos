import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    JFrame ventana;

    JButton botonConfig;

    JButton botonInicio;

    JPanel panelBotones;

    JFrame mapaPrueba;

    // Mapa xd = new Mapa();



    public Menu(){
        ventana = new JFrame("Microorganismos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        agregarComponentes();
        ventana.setSize(500,70);
    }
    public void agregarComponentes(){


        botonInicio = new JButton("INICIAR JUEGO");
        botonInicio.setBackground(Color.black);
        botonInicio.setForeground(Color.white);
        botonInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presionó el botón Inicio");
                // xd.mapaEnteros();
                // xd.crearMapa(xd);
                
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
    
    // public void crearMapa(){
    //     mapaPrueba = new JFrame("Microorganismos prueba");
    //     mapaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     mapaPrueba.setVisible(true);

    //     JPanel panelBotonesJuego = new JPanel(); 
    //     panelBotonesJuego.setLayout(new GridLayout(50,50,1,1));
    //     mapaPrueba.add(panelBotonesJuego);
    //     for (int i=0; i<50; i++){
    //         for (int j = 0; j<50; j++ ){
    //             JButton botonTemporal = new JButton();
    //             if (i % 2 == 0){
    //                 botonTemporal.setBackground(Color.red);
    //             }
    //             else{
    //                 botonTemporal.setBackground(Color.black);
    //             }
                
    //             panelBotonesJuego.add(botonTemporal);
    //             panelBotonesJuego.setLocation(i,j);
    //             int posicionX = i;
    //             int posicionY = j;
    //             botonTemporal.addActionListener(new ActionListener() {
    //                 public void actionPerformed(ActionEvent e){
    //                     System.out.println("Se presionó el boton en la posición " + posicionX + " "+ posicionY);
    //                     }
    //             });
    //         }
    //     }
    // }


}