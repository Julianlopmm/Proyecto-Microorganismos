import java.util.Objects;
import java.util.Scanner;

public class Jugador extends OrganismoBasico{
    int i;
    int j;

    public Jugador(int energia, int vision,int velocidad, int edad){
        super(energia, vision, velocidad, edad);
    }
    public int movimiento(){
        Scanner pregunta = new Scanner(System.in);
        System.out.println("hacia que posicion deseas moverte: ");
        if(pregunta.nextInt() == 1){
            return 1;
        }
        else if(pregunta.nextInt() == 2){
            return 2;
        }
        else if(pregunta.nextInt() == 3){
            return 3;
        }
        else if(pregunta.nextInt() == 4){
            return 4;
        }
        return 0;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
}
