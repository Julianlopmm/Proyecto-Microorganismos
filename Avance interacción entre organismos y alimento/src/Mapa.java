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
    OrganismoBasico o1 = new OrganismoBasico(1, 2, 2, 2);
    // OrganismoBasico o2 = new OrganismoVelocidad(1, 2, 2, 2);
    // OrganismoVelocidad o3 = new OrganismoVelocidad(1, 2, 2, 2);
    OrganismoVision o4 = new OrganismoVision(1, 2, 2, 2);

    OrganismoBasico o2 = new OrganismoVelocidad(10, 2, 2, 2);
    OrganismoVelocidad o3 = new OrganismoVelocidad(10, 2, 2, 2);

    JFrame mapaPrueba;
    public Mapa(){}

    public void LlenarMapa(){
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
        Object jugadores[] = {o1, o2, o3, o4, player};
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

    public void actualizarMapaObjetos() {
        Random jrandom = new Random();
        int contador = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (TableroEntero[i][j] == 7) {
                    Tablero[i][j] = new AlimentoEnergia();
                } else if (TableroEntero[i][j] == 1) {
                    Tablero[i][j] = o1;

                } else if (TableroEntero[i][j] == 2) {
                    Tablero[i][j] = o2;

                } else if (TableroEntero[i][j] == 3) {
                    Tablero[i][j] = o3;

                } else if (TableroEntero[i][j] == 4) {
                    Tablero[i][j] = o4;

                } else if (TableroEntero[i][j] == 9999) {
                    Tablero[i][j] = player;
                }
            }
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
    public ActionListener reflejarMovimiento(int x) {
        //int x
        int i = EncontrarFilaJugador();
        int j = EncontrarColumnaJugador();
        //int x = player.movimiento();
        if (x == 1) {
            if (i == 0) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            } else {
                
                if(Tablero[i-1][j] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i-1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i-1][j]).getEnergia();
                    player.imprimirDatos();
                }
                else if(Tablero[i-1][j] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i-1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();player.imprimirDatos();
                    System.out.println("--------------");
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i-1][j]).getVelocidad();
                    player.imprimirDatos();
                }
                else if(Tablero[i-1][j] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i-1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.vision = player.vision + ((AlimentoVision) Tablero[i-1][j]).getVision();
                    player.imprimirDatos();
                }

                else if(Tablero[i-1][j] instanceof OrganismoBasico){
                    if (player.energia > ((OrganismoBasico) Tablero[i-1][j]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2;
                        player.imprimirDatos();
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i-1][j]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i-1][j]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2;
                            player.imprimirDatos();
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i-1][j]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i-1][j]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2; 
                                player.imprimirDatos();
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i-1][j]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                        }
                            
                        }
                    else{
                        System.out.println("Perdio pibe");
                        
                    }
                }


                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i--;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;

            }
        } else if (x == 2) {
            if (i == 49) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            } else {

                if(Tablero[i+1][j] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i+1][j]).getEnergia();
                    player.imprimirDatos();
                }
                else if(Tablero[i+1][j] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();player.imprimirDatos();
                    System.out.println("--------------");
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i+1][j]).getVelocidad();
                    player.imprimirDatos();
                }
                else if(Tablero[i+1][j] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.vision = player.vision + ((AlimentoVision) Tablero[i+1][j]).getVision();
                    player.imprimirDatos();
                }
                else if (Tablero[i+1][j] instanceof OrganismoBasico) {
                    if (player.energia > ((OrganismoBasico) Tablero[i+1][j]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2;
                        player.imprimirDatos();
                    }

                    else if (player.energia == ((OrganismoBasico) Tablero[i+1][j]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i+1][j]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2; 
                            player.imprimirDatos();
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i+1][j]).getVelocidad()) {
                            if (player.vision > ((OrganismoBasico) Tablero[i+1][j]).getVision()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2;
                                player.imprimirDatos();
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i+1][j]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                            }
                                
                        }
                        else{
                            System.out.println("Perdio pibe");
                        }
                        
                    }
                    else{
                        System.out.println("Perdio pibe");
                    }
                    
                }
                   
                
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i++;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
                }

                

        } else if (x == 3) {
            if (j == 0) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            } else {


                if(Tablero[i][j-1] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i][j-1]).getEnergia();
                    player.imprimirDatos();
                }
                else if(Tablero[i][j-1] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.imprimirDatos();player.imprimirDatos();
                    System.out.println("--------------");
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i][j-1]).getVelocidad();
                    player.imprimirDatos();
                }
                else if(Tablero[i][j-1] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.vision = player.vision + ((AlimentoVision) Tablero[i][j-1]).getVision();
                    player.imprimirDatos();
                }
                else if (Tablero[i][j-1] instanceof OrganismoBasico) {
                    if (player.energia > ((OrganismoBasico) Tablero[i][j-1]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2;
                        player.imprimirDatos();
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i][j-1]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i][j-1]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2;
                            player.imprimirDatos();
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i][j-1]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i][j-1]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2; 
                                player.imprimirDatos();
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i][j-1]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                        }
                            
                        }
                    else{
                        System.out.println("Perdio pibe");
                        
                    }
                    }


                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j--;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;

            }
        } else if (x == 4) {
            if (j == 49) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");

            } else {

                if(Tablero[i][j+1] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");

                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i][j+1]).getEnergia();
                    player.imprimirDatos();
                }
                else if(Tablero[i][j+1] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.imprimirDatos();player.imprimirDatos();
                    System.out.println("--------------");
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i][j+1]).getVelocidad();
                    player.imprimirDatos();
                }
                else if(Tablero[i][j+1] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.imprimirDatos();
                    System.out.println("--------------");
                    player.vision = player.vision + ((AlimentoVision) Tablero[i][j+1]).getVision();
                    player.imprimirDatos();
                }
                else if (Tablero[i][j+1] instanceof OrganismoBasico) {
                    if (player.energia > ((OrganismoBasico) Tablero[i][j+1]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2;
                        player.imprimirDatos();
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i][j+1]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i][j+1]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2;
                            player.imprimirDatos();
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i][j+1]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i][j+1]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2; 
                                player.imprimirDatos();
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i][j+1]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                        }
                            
                        }
                    else{
                        System.out.println("Perdio pibe");
                        
                    }
                    }

                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j++;
                TableroEntero[i][j] = 9999;
                Tablero[i][j] = player;
            }

        } else {
            System.out.println("ERROR");
        }
        return null;
    }

    public void crearMapa(){
        mapaPrueba = new JFrame("Microorganismos prueba");
        mapaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapaPrueba.setVisible(true);
        
        mapaPrueba.setSize(600, 1000);
        JPanel panelPrincipal = new JPanel();


        JPanel panelBotonesJuego = new JPanel();
        panelBotonesJuego.setLayout(new GridLayout(51,51,1,1));
        JPanel panelBotonesMoverse = new JPanel();
        panelBotonesMoverse.setLayout(new GridLayout(1, 4));
        JButton botonArriba = new JButton("Arriba");
        botonArriba.setLocation(1, 51);
        botonArriba.setBounds(100, 100, 100, 40);
        panelBotonesMoverse.add(botonArriba);

        botonArriba.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reflejarMovimiento(1);
                //getMapaEnteros();

                Component[] componentes = panelBotonesJuego.getComponents();
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        JButton botonTemporal = (JButton) componentes[i * 50 + j];
                        if (Tablero[i][j] instanceof Jugador) {
                            botonTemporal.setBackground(Color.cyan);
                        } else if (Tablero[i][j] instanceof Alimento) {
                            botonTemporal.setBackground(Color.red);
                        } else if (Tablero[i][j] instanceof OrganismoVelocidad) {
                            botonTemporal.setBackground(Color.magenta);
                        } else if (Tablero[i][j] instanceof OrganismoVision) {
                            botonTemporal.setBackground(Color.yellow);
                        } else if (Tablero[i][j] instanceof OrganismoBasico) {
                            botonTemporal.setBackground(Color.green);
                        }
                        else{
                            botonTemporal.setBackground(Color.gray);
                        }
                    }
                }
            }
        });






        JButton botonAbajo = new JButton("Abajo");
        botonAbajo.setLocation(2, 51);
        botonAbajo.setBounds(100, 100, 100, 40);
        panelBotonesMoverse.add(botonAbajo);


        botonAbajo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reflejarMovimiento(2);
                //getMapaEnteros();

                Component[] componentes = panelBotonesJuego.getComponents();
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        JButton botonTemporal = (JButton) componentes[i * 50 + j];
                        if (Tablero[i][j] instanceof Jugador) {
                            botonTemporal.setBackground(Color.cyan);
                        } else if (Tablero[i][j] instanceof Alimento) {
                            botonTemporal.setBackground(Color.red);
                        } else if (Tablero[i][j] instanceof OrganismoVelocidad) {
                            botonTemporal.setBackground(Color.magenta);
                        } else if (Tablero[i][j] instanceof OrganismoVision) {
                            botonTemporal.setBackground(Color.yellow);
                        } else if (Tablero[i][j] instanceof OrganismoBasico) {
                            botonTemporal.setBackground(Color.green);
                        }
                        else{
                            botonTemporal.setBackground(Color.gray);
                        }
                    }
                }
            }
        });

        JButton botonIzquierda = new JButton("Izquierda");
        botonIzquierda.setLocation(3, 51);
        botonIzquierda.setBounds(100, 100, 100, 40);
        panelBotonesMoverse.add(botonIzquierda);

        botonIzquierda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reflejarMovimiento(3);
                //getMapaEnteros();

                Component[] componentes = panelBotonesJuego.getComponents();
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        JButton botonTemporal = (JButton) componentes[i * 50 + j];
                        if (Tablero[i][j] instanceof Jugador) {
                            botonTemporal.setBackground(Color.cyan);
                        } else if (Tablero[i][j] instanceof Alimento) {
                            botonTemporal.setBackground(Color.red);
                        } else if (Tablero[i][j] instanceof OrganismoVelocidad) {
                            botonTemporal.setBackground(Color.magenta);
                        } else if (Tablero[i][j] instanceof OrganismoVision) {
                            botonTemporal.setBackground(Color.yellow);
                        } else if (Tablero[i][j] instanceof OrganismoBasico) {
                            botonTemporal.setBackground(Color.green);
                        }
                        else{
                            botonTemporal.setBackground(Color.gray);
                        }
                    }
                }
            }
        });


        JButton botonDerecha = new JButton("Derecha");
        botonDerecha.setLocation(4, 51);
        botonDerecha.setBounds(100, 100, 100, 40);
        panelBotonesMoverse.add(botonDerecha);

        botonDerecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reflejarMovimiento(4);
                //getMapaEnteros();

                Component[] componentes = panelBotonesJuego.getComponents();
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        JButton botonTemporal = (JButton) componentes[i * 50 + j];
                        if (Tablero[i][j] instanceof Jugador) {
                            botonTemporal.setBackground(Color.cyan);
                        } else if (Tablero[i][j] instanceof Alimento) {
                            botonTemporal.setBackground(Color.red);
                        } else if (Tablero[i][j] instanceof OrganismoVelocidad) {
                            botonTemporal.setBackground(Color.magenta);
                        } else if (Tablero[i][j] instanceof OrganismoVision) {
                            botonTemporal.setBackground(Color.yellow);
                        } else if (Tablero[i][j] instanceof OrganismoBasico) {
                            botonTemporal.setBackground(Color.green);
                        }
                        else{
                            botonTemporal.setBackground(Color.gray);
                        }
                    }
                }
            }
        });

        panelPrincipal.add(panelBotonesJuego);
        panelPrincipal.add(panelBotonesMoverse);

        mapaPrueba.add(panelPrincipal);

        for (int i=0; i<50; i++){
            for (int j = 0; j<50; j++ ){
                JButton botonTemporal = new JButton();
                if (Tablero[i][j] instanceof Jugador){
                    botonTemporal.setBackground(Color.cyan);
                }
                else if (Tablero[i][j] instanceof Alimento){
                    botonTemporal.setBackground(Color.red);
                }
                else if (Tablero[i][j] instanceof OrganismoVelocidad){
                    botonTemporal.setBackground(Color.magenta);
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
