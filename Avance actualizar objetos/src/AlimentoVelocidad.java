public class AlimentoVelocidad extends Alimento{
    int tamano2;
    int mejoraVelocidad;

    public AlimentoVelocidad(){
        tamano2 = tamano.nextInt(1,4);
        mejoraVelocidad = tamano2 * 2;
    }

    public void getAlimentoEstadisticas(){
        System.out.println("Velocidad tamaño " + mejoraVelocidad);
    }
}
