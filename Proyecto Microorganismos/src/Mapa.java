import javax.lang.model.type.NullType;

import java.lang.reflect.Array;
import java.util.Random;
public class Mapa{
    Object[][] Tablero = new Object[50][50];
    int[][] TableroEntero = new int[50][50];

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
        // Tablero[0][0] = j1;
        // Tablero[49][0] = j2;
        // Tablero[0][49] = j3;
        // Tablero[49][49] = j4;

    }
    public void mapaEnteros() {
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







}
