import javax.lang.model.type.NullType;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Mapa{
    Object[][] Tablero = new Object[50][50];
    int[][] TableroEntero = new int[50][50];

    protected Jugador player = new Jugador(1,1,1,1);
    JFrame mapaPrueba;
    public Mapa(){}

    public void LlenarMapa(Object player, Object j1, Object j2, Object j3, Object j4){
        Random r1 = new Random();
        Random r2 = new Random();
        int r2Entero;
        for(int i = 0; i <50; i++){
            for (int j = 0; j < 50; j++) {
                if(r1.nextInt(1, 4) == 3){
                    r2Entero = r2.nextInt(1,4);
                    if (r2Entero == 1){
                        Tablero[i][j] = new AlimentoVision();
                    }
                    else if(r2Entero == 2){
                        Tablero[i][j] = new AlimentoEnergia();
                    }
                    else if(r2Entero == 3){
                        Tablero[i][j] = new AlimentoVelocidad();
                    }
                }
            }
        }
        Object jugadores[] = {j1, j2, j3, j4, player};
        Random fila = new Random();
        Random columna = new Random();

        for (int j = 0; j < 5; j++){
            Tablero[fila.nextInt(0,50)][columna.nextInt(0,50)] = jugadores[j];
        }
        // Tablero[0][0] = j1;
        // Tablero[49][0] = j2;
        // Tablero[0][49] = j3;
        // Tablero[49][49] = j4;

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
                if (Tablero[i][j] instanceof Jugador) {
                    TableroEntero[i][j] = 9999;

                }
            }
        }
        return TableroEntero;
    }
    public void getMapaEnteros(){
        int c = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(TableroEntero[i][j] + " ");
            }
            System.out.println(c +"----------------------------------------------------------------------------------------------");
            c++;
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

    public void getMapaAlimentos(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(Tablero[i][j] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i][j]).getAlimentoEstadisticas();

                }
                else if(Tablero[i][j] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i][j]).getAlimentoEstadisticas();

                }
                else if(Tablero[i][j] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i][j]).getAlimentoEstadisticas();

                }
            }
        }
    }

    public int EncontrarFilaJugador() {
        int cont = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (TableroEntero[i][j] == 9999) {

                    return cont;
                }
            }
            cont++;
        }
        return 0;
    }
    public int EncontrarColumnaJugador(){

        for (int i = 0; i < 50; i++) {
            int cont = 0;
            for (int j = 0; j < 50; j++) {
                if (TableroEntero[i][j] == 9999) {
                    return cont;
                }
                cont++;
            }
        }
        return 0;
    }
    public void reflejarMovimiento(){
        int i = EncontrarFilaJugador();
        int j = EncontrarColumnaJugador();
        int x = player.movimiento();
        if(x == 1){
            if(i==0){
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            }
            else {
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i--;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
            }
        }
        else if (x == 2){
            if(i == 49){
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");}
            else {
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i++;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
            }
        }
        else if (x == 3){
            if(j == 0){
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            }
            else {
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j--;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
            }
        }
        else if (x == 4){
            if(j == 49){
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");

            }
            else {
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j++;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
            }
        }
        else{
            System.out.println("ERROR");
        }
    }

    public void crearMapa(){
        mapaPrueba = new JFrame("Microorganismos prueba");
        mapaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapaPrueba.setVisible(true);

        JPanel panelBotonesJuego = new JPanel();
        panelBotonesJuego.setLayout(new GridLayout(50,50,1,1));
        mapaPrueba.add(panelBotonesJuego);
        for (int i=0; i<50; i++){
            for (int j = 0; j<50; j++ ){
                JButton botonTemporal = new JButton();
                if (TableroEntero[i][j] == 9999){
                    botonTemporal.setBackground(Color.cyan);
                }
                else if (Tablero[i][j] instanceof Alimento){
                    botonTemporal.setBackground(Color.red);
                }
                else if (Tablero[i][j] instanceof OrganismoVelocidad){
                    botonTemporal.setBackground(Color.MAGENTA);
                }
                else if (Tablero[i][j] instanceof OrganismoVision){
                    botonTemporal.setBackground(Color.yellow);
                }
                else if (Tablero[i][j] instanceof OrganismoBasico){
                    botonTemporal.setBackground(Color.green);
                }
                else{
                    botonTemporal.setBackground(Color.gray);
                }
                panelBotonesJuego.add(botonTemporal);
                panelBotonesJuego.setLocation(i,j);
                int posicionX = i;
                int posicionY = j;
                botonTemporal.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        System.out.println("Se presionó el boton en la posición " + posicionX + " "+ posicionY);
                        if (Tablero[posicionX][posicionY] instanceof Alimento){
                            System.out.println("Botón de alimento presionado");
                            ((Alimento) Tablero[posicionX][posicionY]).getAlimentoEstadisticas();
                        }
                    }
                });
            }
        }
    }
}
