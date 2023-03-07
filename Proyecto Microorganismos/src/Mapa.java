import javax.lang.model.type.NullType;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mapa{

    protected Object[][] Tablero = new Object[50][50];
    protected int[][] TableroEntero = new int[50][50];
    JFrame mapaPrueba;

    public Mapa(){}

    public void LlenarMapa(Object j1, Object j2, Object j3, Object j4){
        Random r1 = new Random();
        for(int i = 0; i <50; i++){
            for (int j = 0; j < 50; j++) {
                if(r1.nextInt(1, 4) == 3){
                    Tablero[i][j] = new Alimento();}
            }
        }
        Object jugadores[] = {j1, j2, j3, j4};
        Random fila = new Random();
        Random columna = new Random();

        for (int j = 0; j < 4; j++){
            Tablero[fila.nextInt(0,50)][columna.nextInt(0,50)] = jugadores[j];
        }


    }
    public int[][] mapaEnteros() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (Tablero[i][j] instanceof Alimento) {
                    TableroEntero[i][j] = 2;
                }
                if (Tablero[i][j] instanceof OrganismoBasico) {
                        TableroEntero[i][j] = 1;
                }

            }
        }
        return TableroEntero;
    }
    public void getMapaEnteros(){
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(TableroEntero[i][j] + " ");
            }
            System.out.println(TableroEntero[i]);
        }
    }

    public void getMapaObjetos(){
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(Tablero[i][j] + " ");
            }
            System.out.println(Tablero[i]);
        }

    }

    public void crearMapa(){


        int[][] matrizEnterosPrueba1 = mapaEnteros();

        mapaPrueba = new JFrame("Microorganismos prueba");
        mapaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapaPrueba.setVisible(true);

        JPanel panelBotonesJuego = new JPanel(); 
        panelBotonesJuego.setLayout(new GridLayout(50,50,1,1));
        mapaPrueba.add(panelBotonesJuego);
        for (int i=0; i<50; i++){
            for (int j = 0; j<50; j++ ){
                JButton botonTemporal = new JButton();
                
                if (matrizEnterosPrueba1[i][j] == 2){
                    botonTemporal.setBackground(Color.red);
                }
                else{
                    botonTemporal.setBackground(Color.black);
                }
                
                panelBotonesJuego.add(botonTemporal);
                panelBotonesJuego.setLocation(i,j);
                int posicionX = i;
                int posicionY = j;
                botonTemporal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        System.out.println("Se presionó el boton en la posición " + posicionX + " "+ posicionY);
                        }
                });
            }
        }
    }

}
