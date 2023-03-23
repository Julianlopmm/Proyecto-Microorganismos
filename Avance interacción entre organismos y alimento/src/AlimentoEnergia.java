public class AlimentoEnergia extends Alimento{
    int tamano2;
    int mejoraEnergia;

    public AlimentoEnergia(){
        tamano2 = tamano.nextInt(1,4);
        mejoraEnergia = tamano2 * 2;
    }

    public void getAlimentoEstadisticas(){
        System.out.println("Energía tamaño " + mejoraEnergia);
    }

    public int getEnergia(){
        return mejoraEnergia;
    }
}
