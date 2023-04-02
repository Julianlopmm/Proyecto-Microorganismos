import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Mapa {
    Object[][] Tablero = new Object[50][50];
    int[][] TableroEntero = new int[50][50];

    protected Jugador player = new Jugador(10,10,5,1);
    OrganismoBasico o1 = new OrganismoBasico(10, 10, 5, 1);
    OrganismoVision o4 = new OrganismoVision(10, 10, 5, 1);

    OrganismoVelocidad o2 = new OrganismoVelocidad(10, 10, 5, 1);
    OrganismoBasico o3 = new OrganismoBasico(10, 10, 5, 1);

    OrganismoBasico lista[] = {player, o1, o2, o3, o4};

    JFrame mapaPrueba;
    public Mapa(){}

    public Object[][] LlenarMapa(){
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
        return Tablero;
    }
    public int[][] mapaEnteros() {

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (Tablero[i][j] instanceof AlimentoVision) {
                    TableroEntero[i][j] = 2;
                }
                if(Tablero[i][j] instanceof AlimentoEnergia){
                    TableroEntero[i][j] = 5;
                }
                if(Tablero[i][j] instanceof AlimentoVelocidad){
                    TableroEntero[i][j] = 6;
                }
                if (Tablero[i][j] instanceof OrganismoBasico) {
                    TableroEntero[i][j] = 1;
                }
                if (Tablero[i][j] == o2) {
                    TableroEntero[i][j] = 3;
                }
                if(Tablero[i][j] == o4){
                    TableroEntero[i][j] = 4;
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



    public void interaccionOrganismosAlimento(int opcion){

        int i = EncontrarFilaJugador();
        int j = EncontrarColumnaJugador();
        
        if (opcion == 1){
            int contador = 0; 
            while (contador < player.getVelocidad()) {
                if (i - 1 < 0){
                    break;
                }
                
                
                else if(Tablero[i-1][j] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i-1][j]).getAlimentoEstadisticas();
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i-1][j]).getEnergia();
                    TableroEntero[i][j] = 0;
                    Tablero[i][j] = null;
                    if (i < 0){
                        i = 0;
                    }
                    i-=1;
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoEnergia();
                    break;
                }
                else if(Tablero[i-1][j] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i-1][j]).getAlimentoEstadisticas();
                    
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i-1][j]).getVelocidad();
                    
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    // i-= 2;
                    i-=1;
                    if (i < 0){
                        i = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i-1][j] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i-1][j]).getAlimentoEstadisticas();
                
                    player.vision = player.vision + ((AlimentoVision) Tablero[i-1][j]).getVision();
                    
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    // i-= 2;
                    i-=1;
                    if (i < 0){
                        i = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }

                else if(Tablero[i-1][j] instanceof OrganismoBasico){
                    if (player.energia > ((OrganismoBasico) Tablero[i-1][j]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2;
                        
                        Tablero[i][j] = null;
                        TableroEntero[i][j] = 0;
                        // i-= 2;
                        i-=1;
                        if (i < 0){
                            i = 0;
                        }
                        TableroEntero[i][j] = 9999;
                        Tablero[i][j] = player;
                        int random1;
                        int random2;
                        Random r1 = new Random();
                        Random r2 = new Random();
                        random1 = r1.nextInt(0,50);
                        random2 = r2.nextInt(0,50);
                        Tablero[random1][random2] = new OrganismoBasico(1,1,1,1);
                        break;
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i-1][j]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i-1][j]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2;
                            
                            Tablero[i][j] = null;
                            TableroEntero[i][j] = 0;
                            i-=1;
                            if (i < 0){
                            i = 0;
                            }
                            TableroEntero[i][j] = 9999;
                            Tablero[i][j] = player;
                            break;
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i-1][j]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i-1][j]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i-1][j]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i-1][j]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i-1][j]).getVelocidad())/2; 
                                
                                Tablero[i][j] = null;
                                TableroEntero[i][j] = 0;
                                // i-= 2;
                                i-=1;
                                if (i < 0){
                                    i = 0;
                                }
                                TableroEntero[i][j] = 9999;
                                Tablero[i][j] = player;
                                break;
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i-1][j]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                Tablero[i][j] = null;
                                i-=1;
                                if (i < 0){
                                    i = 0;
                                }
                                Tablero[i][j] = ((OrganismoBasico) Tablero[i-1][j]);
                            }
                        }
                        else{
                            Tablero[i][j] = null;
                            i-=1;
                            if (i < 0){
                                i = 0;
                            }
                            Tablero[i][j] = ((OrganismoBasico) Tablero[i-1][j]);
                        }
                            
                        }
                    else{
                        System.out.println("Perdio");
                        Tablero[i][j] = null;
                        i-=1;
                        if (i < 0){
                            i = 0;
                        }
                        Tablero[i][j] = ((OrganismoBasico) Tablero[i-1][j]);

                        
                    }
                }
                else{
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    i-= 1;
                    if (i < 0){
                        i = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                }
            contador += 1;
            }

        }


        if (opcion == 2){
            int contador = 0; 
            while (contador <= player.getVelocidad()) {
                if (i + 1 > 49){
                    break;
                }
                else if(Tablero[i+1][j] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i+1][j]).getEnergia();
                    TableroEntero[i][j] = 0;
                    Tablero[i][j] = null;
                    i+=1;
                    // i-= 2;
                    if (i > 49){
                        i = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoEnergia();
                    break;
                }
                else if(Tablero[i+1][j] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i+1][j]).getVelocidad();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    // i-= 2;
                    i+=1;
                    if (i > 49){
                        i = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i+1][j] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i+1][j]).getAlimentoEstadisticas();
                    player.vision = player.vision + ((AlimentoVision) Tablero[i+1][j]).getVision();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    // i-= 2;
                    i+=1;
                    if (i > 49){
                        i = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }

                else if(Tablero[i+1][j] instanceof OrganismoBasico){
                    if (player.energia > ((OrganismoBasico) Tablero[i+1][j]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2;
                        Tablero[i][j] = null;
                        TableroEntero[i][j] = 0;
                        // i-= 2;
                        i+=1;
                        if (i > 49){
                            i = 49;
                        }
                        TableroEntero[i][j] = 9999;
                        Tablero[i][j] = player;
                        break;
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i+1][j]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i+1][j]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2;
                            Tablero[i][j] = null;
                            TableroEntero[i][j] = 0;
                            // i-= 2;
                            i+=1;
                            if (i > 49){
                            i = 49;
                            }
                            TableroEntero[i][j] = 9999;
                            Tablero[i][j] = player;
                            break;
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i+1][j]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i+1][j]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i+1][j]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i+1][j]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i+1][j]).getVelocidad())/2; 
                                Tablero[i][j] = null;
                                TableroEntero[i][j] = 0;
                                i+=1;
                                // i-= 2;
                                if (i > 49){
                                    i = 49;
                                }
                                TableroEntero[i][j] = 9999;
                                Tablero[i][j] = player;
                                break;
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i+1][j]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                                Tablero[i][j] = null;
                                i+=1;
                                if (i > 49){
                                    i = 49;
                                }
                                Tablero[i][j] = ((OrganismoBasico) Tablero[i+1][j]);
                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                            Tablero[i][j] = null;
                            i+=1;
                            if (i > 49){
                                i = 49;
                            }
                            Tablero[i][j] = ((OrganismoBasico) Tablero[i+1][j]);
                        }
                            
                        }
                    else{
                        System.out.println("Perdio");
                        Tablero[i][j] = null;
                        i+=1;
                        if (i > 49){
                            i = 49;
                        }
                        Tablero[i][j] = ((OrganismoBasico) Tablero[i+1][j]);

                        
                    }
                }
                else{
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    i+= 1;
                    if (i > 49){
                        i = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                }
            contador += 1;
            }
        }

        if (opcion == 3){
            int contador = 0; 
            while (contador <= player.getVelocidad()) {
                if (j-1 < 0){
                    break;
                }
                if(Tablero[i][j-1] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i][j-1]).getEnergia();
                    TableroEntero[i][j] = 0;
                    Tablero[i][j] = null;
                    j-=1;
                    if (j < 0){
                        j = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoEnergia();
                    break;
                }
                else if(Tablero[i][j-1] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i][j-1]).getVelocidad();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j-=1;
                    if (j < 0){
                        j = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i][j-1] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i][j-1]).getAlimentoEstadisticas();
                    player.vision = player.vision + ((AlimentoVision) Tablero[i][j-1]).getVision();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j-=1;
                    if (j < 0){
                        j = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }

                else if(Tablero[i][j-1] instanceof OrganismoBasico){
                    if (player.energia > ((OrganismoBasico) Tablero[i][j-1]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2;
                        Tablero[i][j] = null;
                        TableroEntero[i][j] = 0;
                        j-=1;
                        if (j < 0){
                            j = 0;
                        }
                        TableroEntero[i][j] = 9999;
                        Tablero[i][j] = player;
                        break;
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i][j-1]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i][j-1]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2;
                            Tablero[i][j] = null;
                            TableroEntero[i][j] = 0;
                            j-=1;
                            if (j < 0){
                            j = 0;
                            }
                            TableroEntero[i][j] = 9999;
                            Tablero[i][j] = player;
                            break;
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i][j-1]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i][j-1]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i][j-1]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i][j-1]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j-1]).getVelocidad())/2; 
                                Tablero[i][j] = null;
                                TableroEntero[i][j] = 0;
                                j-=1;
                                if (j < 0){
                                    j = 0;
                                }
                                TableroEntero[i][j] = 9999;
                                Tablero[i][j] = player;
                                break;
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i][j-1]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                                Tablero[i][j] = null;
                                j-=1;
                                if (j < 0){
                                    j = 0;
                                }
                                Tablero[i][j] = ((OrganismoBasico) Tablero[i][j-1]);

                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                            Tablero[i][j] = null;
                            j-=1;
                            if (j < 0){
                                j = 0;
                            }
                            Tablero[i][j] = ((OrganismoBasico) Tablero[i][j-1]);

                        }
                            
                        }
                    else{
                        System.out.println("Perdio pibe");
                        Tablero[i][j] = null;
                        j-=1;
                        if (j < 0){
                            j = 0;
                        }
                        Tablero[i][j] = ((OrganismoBasico) Tablero[i][j-1]);

                        
                    }
                }
                else{
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j -= 1;
                    if (j < 0){
                        j = 0;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                }
            contador += 1;
            }
        }

        if (opcion == 4){
            int contador = 0; 
            while (contador <= player.getVelocidad()) {
                if (j + 1 > 49){
                    break;
                }
                if(Tablero[i][j+1] instanceof AlimentoEnergia){
                    ((AlimentoEnergia) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.energia = player.energia + ((AlimentoEnergia) Tablero[i][j+1]).getEnergia();
                    TableroEntero[i][j] = 0;
                    Tablero[i][j] = null;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoEnergia();
                    break;
                }
                else if(Tablero[i][j+1] instanceof AlimentoVelocidad){
                    ((AlimentoVelocidad) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.velocidad = player.velocidad + ((AlimentoVelocidad) Tablero[i][j+1]).getVelocidad();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i][j+1] instanceof AlimentoVision){
                    ((AlimentoVision) Tablero[i][j+1]).getAlimentoEstadisticas();
                    player.vision = player.vision + ((AlimentoVision) Tablero[i][j+1]).getVision();
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }

                else if(Tablero[i][j+1] instanceof OrganismoBasico){
                    if (player.energia > ((OrganismoBasico) Tablero[i][j+1]).getEnergia()) {
                        player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                        player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                        player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2;
                        Tablero[i][j] = null;
                        TableroEntero[i][j] = 0;
                        j += 1;
                        if (j > 49){
                            j = 49;
                        }
                        TableroEntero[i][j] = 9999;
                        Tablero[i][j] = player;
                        break;
                    }
                    else if (player.energia == ((OrganismoBasico) Tablero[i][j+1]).getEnergia()) {
                        if (player.velocidad > ((OrganismoBasico) Tablero[i][j+1]).getVelocidad()) {
                            player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                            player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                            player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2;
                            Tablero[i][j] = null;
                            TableroEntero[i][j] = 0;
                            j += 1;
                            if (j > 49){
                            j = 49;
                            }
                            TableroEntero[i][j] = 9999;
                            Tablero[i][j] = player;
                            break;
                        }
                        else if (player.velocidad == ((OrganismoBasico) Tablero[i][j+1]).getVelocidad()) {
                            if (player.edad > ((OrganismoBasico) Tablero[i][j+1]).getEdad()) {
                                player.energia = player.energia + (((OrganismoBasico) Tablero[i][j+1]).getEnergia())/2;
                                player.vision = player.vision + (((OrganismoBasico) Tablero[i][j+1]).getVision())/2;
                                player.velocidad = player.velocidad + (((OrganismoBasico) Tablero[i][j+1]).getVelocidad())/2; 
                                Tablero[i][j] = null;
                                TableroEntero[i][j] = 0;
                                j += 1;
                                if (j > 49){
                                    j = 49;
                                }
                                TableroEntero[i][j] = 9999;
                                Tablero[i][j] = player;
                                break;
                            }
                            else if (player.edad == ((OrganismoBasico) Tablero[i][j+1]).getEdad()) {
                                System.out.println("Empate");
                            }
                            else{
                                System.out.println("Perdio pibe");
                                Tablero[i][j] = null;
                                j+=1;
                                if (j > 49){
                                    j = 49;
                                }
                                Tablero[i][j] = ((OrganismoBasico) Tablero[i][j+1]);
                            }
                        }
                        else{
                            System.out.println("Perdio pibe");
                            Tablero[i][j] = null;
                            j+=1;
                            if (j > 49){
                                j = 49;
                            }
                            Tablero[i][j] = ((OrganismoBasico) Tablero[i][j+1]);
                        }
                            
                        }
                    else{
                        System.out.println("Perdio pibe"); 
                        Tablero[i][j] = null;
                        j+=1;
                        if (j > 49){
                            j = 49;
                        }
                        Tablero[i][j] = ((OrganismoBasico) Tablero[i][j+1]);
                    }
                }
                else{
                    Tablero[i][j] = null;
                    TableroEntero[i][j] = 0;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    TableroEntero[i][j] = 9999;
                    Tablero[i][j] = player;
                }
            contador += 1;
            }
        }

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
                
                interaccionOrganismosAlimento(x);

            }
        } else if (x == 2) {
            if (i == 49) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            } else {

                interaccionOrganismosAlimento(x);
                }

                

        } else if (x == 3) {
            if (j == 0) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");
            } else {


                interaccionOrganismosAlimento(x);

            }
        } else if (x == 4) {
            if (j == 49) {
                System.out.println("NO SE PUEDE HACER ESE MOVIMIENTO...");

            } else {

                interaccionOrganismosAlimento(x);
            }

        } else {
            System.out.println("ERROR");
        }
        return null;
    }



    //////////////// MOVIMIENTO ORGANISMOS
    //public boolean estaDentroDeVision(int[][] matriz, int iO, int jO, int iA, int jA, int vision) {
        //double distancia = Math.sqrt(Math.pow(iO - iA, 2) + Math.pow(jO - jA, 2));
        //return distancia <= vision && matriz[iO][jO] == matriz[iA][jA];
    //}


    /////// MOVIMIENTO ORGANISMO VELOCIDAD


    public int getIB1(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o1){
                    return i;
                }
            }
        }
        return 0;
    }

    public int getJB1(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o1){
                    return j;
                }
            }
        }
        return 0;
    }

    public int[] encontrarAlimentoCercanoB1(Object[][] m, int i, int j, OrganismoBasico o){
        int[] AlimentoAseguir = new int[2];
        int distancia = 0;
        int distanciaMenor = 100;
        int visionTemp = o.getVision();
        if (visionTemp > 50){
            visionTemp = 50;
        }


        for(int x = 0; x < visionTemp; x++){
            for(int y = 0; y < visionTemp; y++){
                if(m[x][y] instanceof Alimento){
                    distancia = Math.abs(i - x) + Math.abs(j - y);
                    if(distancia < distanciaMenor){
                        distanciaMenor = distancia;
                        AlimentoAseguir[0] = x;
                        AlimentoAseguir[1] = y;
                    }
                }
            }
        }
        return AlimentoAseguir;
    }


    public int direccionMovimientoB1(){
        int[] AlimentoAseguir = encontrarAlimentoCercanoB1(Tablero, getIB1(), getJB1(), o1);
        int i = AlimentoAseguir[0];
        int j = AlimentoAseguir[1];
        Random r = new Random();
        //boolean estaDentro = false;
        if(i == getIB1() && j == getJB1()){
            return 5;
        }
        else{
            if(i > getIB1()){
                return 1;
            }
            else if(i < getIB1()){
                return 2;
            }
            else if(j > getJB1()){
                return 3;
            }
            else if(j < getJB1()){
                return 4;
            }
        }
        return 0;
    }

    public int moverseAcomidaB1(int x) {
        int i = getIB1();
        int j = getJB1();
        if (x == 1) {
            if (i + 1 <= 49) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // i++;
                // TableroEntero[i][j] = 3;
                // Tablero[i][j] = o1;
                interaccionOrganismosAlimentoNPC(2, o1, i, j);
            }
        } else if (x == 2) {
            if (i - 1 >= 0) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // i--;
                // TableroEntero[i][j] = 3;
                // Tablero[i][j] = o1;
                interaccionOrganismosAlimentoNPC(1, o1, i, j);
            }
        } else if (x == 3) {
            if (j + 1 <= 49) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // j++;
                // TableroEntero[i][j] = 3;
                // Tablero[i][j] = o1;
                interaccionOrganismosAlimentoNPC(4, o1, i, j);
            }
        } else if (x == 4) {
            if (j - 1 >= 0) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // j--;
                // TableroEntero[i][j] = 3;
                // Tablero[i][j] = o1;
                interaccionOrganismosAlimentoNPC(3, o1, i, j);
            }

        }
        return 0;
    }
    /// ORGANISMO 2

    public int getIB2(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o3){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJB2(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o3){
                    return j;
                }
            }
        }
        return 0;
    }

    public int[] alimentoAseguirB2(Object[][] m, int i, int j, OrganismoBasico o){
        int[] AlimentoAseguir = new int[2];
        int distancia = 0;
        int distanciaMenor = 100;
        int visionTemp = o.getVision();
        if (visionTemp > 50){
            visionTemp = 50;
        }

        for(int x = 0; x < visionTemp; x++){
            for(int y = 0; y < visionTemp; y++){
                if(m[x][y] instanceof Alimento){
                    distancia = Math.abs(i - x) + Math.abs(j - y);
                    if(distancia < distanciaMenor){
                        distanciaMenor = distancia;
                        AlimentoAseguir[0] = x;
                        AlimentoAseguir[1] = y;
                    }
                }
            }
        }
        return AlimentoAseguir;
    }

    public int direccionMovimientoB2(){
        int[] AlimentoAseguir = alimentoAseguirB2(Tablero, getIB2(), getJB2(), o3);
        int i = AlimentoAseguir[0];
        int j = AlimentoAseguir[1];
        Random r = new Random();
        //boolean estaDentro = false;
        if(i == getIB2() && j == getJB2()){
            return 5;
        }
        else{
            if(i > getIB2()){
                return 1;
            }
            else if(i < getIB2()){
                return 2;
            }
            else if(j > getJB2()){
                return 3;
            }
            else if(j < getJB2()){
                return 4;
            }
        }
        return 0;
    }
    public int moverseAcomidaB2(int x) {
        int i = getIB2();
        int j = getJB2();
        if (x == 1) {
            if (i + 1 <= 49) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // i++;
                // TableroEntero[i][j] = 4;
                // Tablero[i][j] = o3;
                interaccionOrganismosAlimentoNPC(2, o3, i, j);
            }
        } else if (x == 2) {
            if (i - 1 >= 0) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // i--;
                // TableroEntero[i][j] = 4;
                // Tablero[i][j] = o3;

                interaccionOrganismosAlimentoNPC(1, o3, i, j);
            }
        } else if (x == 3) {
            if (j + 1 <= 49) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // j++;
                // TableroEntero[i][j] = 4;
                // Tablero[i][j] = o3;
                interaccionOrganismosAlimentoNPC(4, o3, i, j);
            }
        } else if (x == 4) {
            if (j - 1 >= 0) {
                // TableroEntero[i][j] = 0;
                // Tablero[i][j] = null;
                // j--;
                // TableroEntero[i][j] = 4;
                // Tablero[i][j] = o3;
                interaccionOrganismosAlimentoNPC(3, o3, i, j);
            }

        }
        return 0;
    }
    public int[] encontrarAlimentoCercanoVE(Object[][] matriz, int x, int y, OrganismoVelocidad o) {
        int[] posicionAlimentoCercano = {-1, -1};
        double distanciaMinima = Double.MAX_VALUE;
        int visionTemp = o.getVision();
        if (visionTemp > 50) {
            visionTemp = 50;
        }

        for (int i = 0; i < visionTemp; i++) {
            for (int j = 0; j < visionTemp; j++) {
                if (matriz[i][j] instanceof AlimentoVelocidad){
                    double distancia = Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        posicionAlimentoCercano[0] = i;
                        posicionAlimentoCercano[1] = j;
                    }
                }
            }
        }

        return posicionAlimentoCercano;
    }
    public int[] encontrarAlimentoCercanoVI(Object[][] matriz, int x, int y, OrganismoVision o) {
        int[] posicionAlimentoCercano = {-1, -1};
        double distanciaMinima = Double.MAX_VALUE;
        int visionTemp = o.getVision();
        if (visionTemp > 50) {
            visionTemp = 50;
        }

        for (int i = 0; i < visionTemp; i++) {
            for (int j = 0; j < visionTemp; j++) {
                if (matriz[i][j] instanceof AlimentoVision) {
                    double distancia = Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        posicionAlimentoCercano[0] = i;
                        posicionAlimentoCercano[1] = j;
                    }
                }
            }
        }
        return posicionAlimentoCercano;
    }

    public int getIVE(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o2){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJVE(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o2){
                    return j;
                }
            }
        }
        return 0;
    }




    public void interaccionOrganismosAlimentoNPC(int opcion, OrganismoBasico organismo, int i, int j){
        if (opcion == 1){
            int contador = 0;
            while (contador < organismo.getVelocidad()){
                if (i - 1 < 0) {
                    break;
                }
                else if (Tablero[i-1][j] instanceof AlimentoEnergia) {
                organismo.energia = organismo.energia + ((AlimentoEnergia) Tablero[i-1][j]).getEnergia();
                Tablero[i][j] = null;
                i -= 1;
                if (i < 0){
                    i = 0;
                }
                Tablero[i][j] = organismo;
                int random1;
                int random2;
                Random r1 = new Random();
                Random r2 = new Random();
                random1 = r1.nextInt(0,50);
                random2 = r2.nextInt(0,50);
                Tablero[random1][random2] = new AlimentoEnergia();
                break;
                }
                else if (Tablero[i-1][j] instanceof AlimentoVelocidad) {
                    organismo.velocidad = organismo.velocidad + ((AlimentoVelocidad) Tablero[i-1][j]).getVelocidad();
                    Tablero[i][j] = null;
                    i -= 1;
                    if (i < 0){
                        i = 0;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i-1][j] instanceof AlimentoVision){
                    organismo.vision = organismo.vision + ((AlimentoVision) Tablero[i-1][j]).getVision();
                    Tablero[i][j] = null;
                    i -= 1;
                    if (i < 0){
                        i = 0;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }
                else{
                    Tablero[i][j] = null;
                    i -= 1;
                    if (i < 0){
                        i = 0;
                    }
                    Tablero[i][j] = organismo;
                }
                contador += 1;
            }
        }
        else if(opcion == 2){
            int contador = 0;
            while(contador < organismo.getVelocidad()){
                if (i + 1 > 49){
                    break;
                }
                else if (Tablero[i+1][j] instanceof AlimentoEnergia){
                    organismo.energia = organismo.energia + ((AlimentoEnergia) Tablero[i+1][j]).getEnergia();
                    Tablero[i][j] = null;
                    i += 1;
                    if (i > 49){
                        i = 49;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoEnergia();
                    break;
                }
                else if (Tablero[i+1][j] instanceof AlimentoVelocidad){
                    organismo.velocidad = organismo.velocidad + ((AlimentoVelocidad) Tablero[i+1][j]).getVelocidad();
                    Tablero[i][j] = null;
                    i += 1;
                    if (i > 49){
                        i = 49;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i+1][j] instanceof AlimentoVision){
                    organismo.vision = organismo.vision + ((AlimentoVision) Tablero[i+1][j]).getVision();
                    Tablero[i][j] = null;
                    i += 1;
                    if (i > 49){
                        i = 49;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }
                else{
                    Tablero[i][j] = null;
                    i += 1;
                    if (i > 49){
                        i = 49;
                    }
                    Tablero[i][j] = organismo;
                }
                contador += 1;
            }
        }
        else if (opcion == 3){
            int contador = 0;
            while (contador < organismo.getVelocidad()){
                if(j -1 < 0){
                    break;
                }
                else if (Tablero[i][j-1] instanceof AlimentoEnergia){
                organismo.energia = organismo.energia + ((AlimentoEnergia) Tablero[i][j-1]).getEnergia();
                Tablero[i][j] = null;
                j -= 1;
                if (j < 0){
                   j = 0;
                }
                Tablero[i][j] = organismo;
                int random1;
                int random2;
                Random r1 = new Random();
                Random r2 = new Random();
                random1 = r1.nextInt(0,50);
                random2 = r2.nextInt(0,50);
                Tablero[random1][random2] = new AlimentoEnergia();
                break;
                }
                else if (Tablero[i][j-1] instanceof AlimentoVelocidad){
                    organismo.velocidad = organismo.velocidad + ((AlimentoVelocidad) Tablero[i][j-1]).getVelocidad();
                    Tablero[i][j] = null;
                    j -= 1;
                    if (j < 0){
                        j = 0;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i][j-1] instanceof AlimentoVision){
                    organismo.vision = organismo.vision + ((AlimentoVision) Tablero[i][j-1]).getVision();
                    Tablero[i][j] = null;
                    j -= 1;
                    if (j < 0){
                        j = 0;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }
                else{
                    Tablero[i][j] = null;
                    j -= 1;
                    if (j < 0){
                        j = 0;
                    }
                    Tablero[i][j] = organismo;
                }
                contador += 1;
            }
        }
        else if (opcion == 4) {
            int contador = 0;
            while (contador < organismo.getVelocidad()){
                if (j + 1 > 49) {
                    break;
                }
                if (Tablero[i][j+1] instanceof AlimentoEnergia){
                organismo.energia = organismo.energia + ((AlimentoEnergia) Tablero[i][j+1]).getEnergia();
                Tablero[i][j] = null;
                j += 1;
                if (j > 49){
                    j = 49;
                }
                Tablero[i][j] = organismo;
                int random1;
                int random2;
                Random r1 = new Random();
                Random r2 = new Random();
                random1 = r1.nextInt(0,50);
                random2 = r2.nextInt(0,50);
                Tablero[random1][random2] = new AlimentoEnergia();
                break;
                }
                else if (Tablero[i][j+1] instanceof AlimentoVelocidad){
                    organismo.velocidad = organismo.velocidad + ((AlimentoVelocidad) Tablero[i][j+1]).getVelocidad();
                    Tablero[i][j] = null;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVelocidad();
                    break;
                }
                else if(Tablero[i][j+1] instanceof AlimentoVision){
                    organismo.vision = organismo.vision + ((AlimentoVision) Tablero[i][j+1]).getVision();
                    Tablero[i][j] = null;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    Tablero[i][j] = organismo;
                    int random1;
                    int random2;
                    Random r1 = new Random();
                    Random r2 = new Random();
                    random1 = r1.nextInt(0,50);
                    random2 = r2.nextInt(0,50);
                    Tablero[random1][random2] = new AlimentoVision();
                    break;
                }
                else{
                    Tablero[i][j] = null;
                    j += 1;
                    if (j > 49){
                        j = 49;
                    }
                    Tablero[i][j] = organismo;
                }
                contador += 1;
            }
        }
    }




    public int direccionMovimientoVE(){
        int[] AlimentoAseguir = encontrarAlimentoCercanoVE(Tablero, getIVE(), getJVE(), o2);
        int i = AlimentoAseguir[0];
        int j = AlimentoAseguir[1];
        Random r = new Random();
        //boolean estaDentro = estaDentroDeVision(TableroEntero, i,j, getIVE(), getJVE(), o2.vision);
        if (i == -1 && j == -1) {
            System.out.println("xd");
            return r.nextInt(1,4);
        } else
        if (i > getIVE()) {
            return 1;
        } else if (i < getIVE()) {
            return 2;
        } else if (j > getJVE()) {
            return 3;
        } else if (j < getJVE()) {
            return 4;
        }
        return 5;

    }


    public void moverseAcomidaVE(int x){
        int i = getIVE();
        int j = getJVE();
        if(x == 1){
            // if(i+ 1<= 49){
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // i++;
            //     // TableroEntero[i][j] = 3;
            //     // Tablero[i][j] = o2;
                
            // }
            interaccionOrganismosAlimentoNPC(2, o2, i, j);
        }
        else if(x == 2){
            // if(i-1>= 0) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // i-- ;
            //     // TableroEntero[i][j] = 3;
            //     // Tablero[i][j] = o2;
                
            // }
            interaccionOrganismosAlimentoNPC(1, o2, i, j);
        }

        else if(x == 3){
            // if (j+1 < 49) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // j++;
            //     // TableroEntero[i][j] = 3;
            //     // Tablero[i][j] = o2;
                
            // }
            interaccionOrganismosAlimentoNPC(4, o2, i, j);
        }

        else if(x == 4){
            // if (j-1 >= 0) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // j--;
            //     // TableroEntero[i][j] = 3;
            //     // Tablero[i][j] = o2;
                
            // }
            interaccionOrganismosAlimentoNPC(3, o2, i, j);

        }
        else if(x== 5){
            if(getIVE() + 1 <= 49) {
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i++;
                TableroEntero[i][j] = 3;
                Tablero[i][j] = o2;
            }
            if(getIVE() - 1 >= 0){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i--;
                TableroEntero[i][j] = 3;
                Tablero[i][j] = o2;
            }
            if(getJVE() + 1 <= 49){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j++;
                TableroEntero[i][j] = 3;
                Tablero[i][j] = o2;
            }
            if(getJVE() - 1 >= 0){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j--;
                TableroEntero[i][j] = 3;
                Tablero[i][j] = o2;
            }
        }
    }


    ///// MOVIMIENTO ORGANISMO VISION

    public int getIVI(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o4){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJVI(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(Tablero[i][j] == o4){
                    return j;
                }
            }
        }
        return 0;
    }
    public int direccionMovimientoVI(){
        int[] AlimentoAseguir = encontrarAlimentoCercanoVI(Tablero, getIVI(), getJVI(), o4);
        int i = AlimentoAseguir[0];
        int j = AlimentoAseguir[1];
        Random r = new Random();
        //boolean estaDentro = estaDentroDeVision(TableroEntero, getIVI(), getJVI(), i, j, o4.vision);

        if (i == -1 && j == -1) {
            System.out.println("xd");
            return r.nextInt(1,4);
        }

            if (i > getIVI()) {
                return 1;
            } else if (i < getIVI()) {
                return 2;
            } else if (j > getJVI()) {
                return 3;
            } else if (j < getJVI()) {
                return 4;
            }
            return 5;


    }

    public void moverseAcomidaVI(int x){
        int i = getIVI();
        int j = getJVI();
        if(x == 1){
            // if(i+1 <= 49){
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // i++;
            //     // TableroEntero[i][j] = 4;
            //     // Tablero[i][j] = o4;
                
            // }
            interaccionOrganismosAlimentoNPC(2, o4, i, j);

        }
        else if(x == 2){
            // if (i-1 >= 0) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // i--;
            //     // TableroEntero[i][j] = 4;
            //     // Tablero[i][j] = o4;
                
            // }
            interaccionOrganismosAlimentoNPC(1, o4, i, j);
        }

        else if(x == 3){
            // if(j+1 < 49) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // j++;
            //     // TableroEntero[i][j] = 4;
            //     // Tablero[i][j] = o4;
                
            // }
            interaccionOrganismosAlimentoNPC(4, o4, i, j);
        }

        else if(x == 4){
            // if(j-1 >= 0) {
            //     // TableroEntero[i][j] = 0;
            //     // Tablero[i][j] = null;
            //     // j--;
            //     // TableroEntero[i][j] = 4;
            //     // Tablero[i][j] = o4;
                
            // }
            interaccionOrganismosAlimentoNPC(3, o4, i, j);
        }
        else if(x == 5){
            if(i+1 <= 49){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i++;
                TableroEntero[i][j] = 4;
                Tablero[i][j] = o4;
            }
            if(i-1 >= 0){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                i--;
                TableroEntero[i][j] = 4;
                Tablero[i][j] = o4;
            }
            if(j+1 <= 49){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j++;
                TableroEntero[i][j] = 4;
                Tablero[i][j] = o4;
            }
            if(j-1 >= 0){
                TableroEntero[i][j] = 0;
                Tablero[i][j] = null;
                j--;
                TableroEntero[i][j] = 4;
                Tablero[i][j] = o4;
            }
        }
    }


















































    public void disminuirCaracteristicas(){
        for (int i = 0; i < 5; i++) {
            lista[i].energia = lista[i].energia - 1;
            lista[i].velocidad = lista[i].velocidad - 1;
            lista[i].vision = lista[i].vision - 1;
            lista[i].edad = lista[i].edad + 1;
        }
    }


    public void crearMapa(){
        mapaPrueba = new JFrame("Microorganismos prueba");
        mapaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapaPrueba.setVisible(true);
        
        mapaPrueba.setSize(600, 1000);
        mapaPrueba.setMaximumSize(new Dimension(600, 1000));
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

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
                //System.out.println(buscarComidaVi());
                //System.out.println(buscarComidaVe());

                int direccionVE = direccionMovimientoVE();
                int direccionVI = direccionMovimientoVI();
                int direccionB1 = direccionMovimientoB1();
                int direccionB2 = direccionMovimientoB2();
                moverseAcomidaB1(direccionB1);
                moverseAcomidaB2(direccionB2);
                moverseAcomidaVE(direccionVE);
                moverseAcomidaVI(direccionVI);
                reflejarMovimiento(1);
                //getMapaEnteros();
                //disminuirCaracteristicas();

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
                int direccionVE = direccionMovimientoVE();
                int direccionVI = direccionMovimientoVI();
                int direccionB1 = direccionMovimientoB1();
                int direccionB2 = direccionMovimientoB2();
                moverseAcomidaB1(direccionB1);
                moverseAcomidaB2(direccionB2);
                moverseAcomidaVE(direccionVE);
                moverseAcomidaVI(direccionVI);
                reflejarMovimiento(2);
                // disminuirCaracteristicas();
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
                int direccionVE = direccionMovimientoVE();
                int direccionVI = direccionMovimientoVI();
                int direccionB1 = direccionMovimientoB1();
                int direccionB2 = direccionMovimientoB2();
                moverseAcomidaB1(direccionB1);
                moverseAcomidaB2(direccionB2);
                moverseAcomidaVE(direccionVE);
                moverseAcomidaVI(direccionVI);
                reflejarMovimiento(3);
                //getMapaEnteros();
                // disminuirCaracteristicas();

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
                int direccionVE = direccionMovimientoVE();
                int direccionVI = direccionMovimientoVI();
                int direccionB1 = direccionMovimientoB1();
                int direccionB2 = direccionMovimientoB2();
                moverseAcomidaB1(direccionB1);
                moverseAcomidaB2(direccionB2);
                moverseAcomidaVE(direccionVE);
                moverseAcomidaVI(direccionVI);
                reflejarMovimiento(4);
                // disminuirCaracteristicas();

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

        panelPrincipal.add(panelBotonesJuego, BorderLayout.CENTER);

        panelPrincipal.add(panelBotonesMoverse, BorderLayout.SOUTH);

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
                        else if (Tablero[posicionX][posicionY] instanceof OrganismoBasico){
                            ((OrganismoBasico) Tablero[posicionX][posicionY]).imprimirDatos();
                        }
                    }
                });
            }
        }
    }
}
