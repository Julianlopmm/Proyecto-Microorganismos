import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;



public class Opciones {
    protected int tamano = 0;
    Opciones(){
    }

    public void crearOpciones(){
        JFrame opcionesPrueba = new JFrame();
        opcionesPrueba.setSize(500,500);
        opcionesPrueba.setVisible(true);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Opciones");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,3));
        JButton boton1 = new JButton("Boton 1");
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presionó el botón 1");
                tamano = 1;
                setTamano(tamano);
            }
        });
        JButton boton2 = new JButton("Boton 2");
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presionó el botón 2");
                tamano = 2;
                setTamano(tamano);
            }
        });
        JButton boton3 = new JButton("Boton 3");
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presionó el botón 3");
                tamano = 3;
                setTamano(tamano);
            }
        });
        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        
        opcionesPrueba.add(panelPrincipal);
    }

    public int getTamano(){
        return tamano;
    }

    public void setTamano(int tamano){
        this.tamano = tamano;
    }
}
