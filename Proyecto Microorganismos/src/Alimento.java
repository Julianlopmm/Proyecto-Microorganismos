import java.util.Random;

public class Alimento {
    Random tamano = new Random();
    Random probabilidadVisionOVelocidad = new Random();
    int probabilidadVisionOVelocidad2;
    int tamano2;
    int mejoraEnergia;
    int mejoraVision;
    int mejoraVelocidad;

    public Alimento(){
        tamano2 = tamano.nextInt(1,4);
        probabilidadVisionOVelocidad2 = probabilidadVisionOVelocidad.nextInt(1,3);
        mejoraEnergia = 2 * tamano2;
        if (probabilidadVisionOVelocidad2 == 1){
            mejoraVision = 2 * tamano2;
            mejoraVelocidad = mejoraVision / 2;
        }
        else{
            mejoraVelocidad = 2 * tamano2;
            mejoraVision = mejoraVelocidad / 2;
        }
    }


    public void getAlimentoEstadisticas(){
        System.out.println("La energía es: " + mejoraEnergia + " . La vision es " + mejoraVision + " . La velocidad es : " + mejoraVelocidad);
    }
}
