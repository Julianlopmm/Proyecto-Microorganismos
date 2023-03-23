public class AlimentoVision extends Alimento{
    int tamano2;
    int mejoraVision;

    public AlimentoVision(){
        tamano2 = tamano.nextInt(1,4);
        mejoraVision = tamano2 * 2;

    }

    public void getAlimentoEstadisticas(){
        System.out.println("Vision tamaño " + mejoraVision);
    }

    public int getVision(){
        return mejoraVision;
    }
    
}
