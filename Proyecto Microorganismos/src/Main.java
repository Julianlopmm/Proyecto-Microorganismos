import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        OrganismoBasico o1 = new OrganismoBasico(1,2,2,2);
        OrganismoBasico o2 = new OrganismoBasico(1,2,2,2);
        OrganismoVelocidad o3 = new OrganismoVelocidad(1,2,2,2);
        OrganismoVision o4 = new OrganismoVision(1,2,2,2);
        Mapa m = new Mapa();
        m.LlenarMapa(o1,o2,o3,o4);
        m.mapaEnteros();
        m.getMapaEnteros();
        m.crearMapa();

    }
}