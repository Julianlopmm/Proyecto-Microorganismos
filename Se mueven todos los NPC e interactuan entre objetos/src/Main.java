import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        //Jugador j1 = new Jugador(1,1,1,1);
        //OrganismoBasico o1 = new OrganismoBasico(1,2,2,2);
        //OrganismoBasico o2 = new OrganismoBasico(1,2,2,2);
        //OrganismoVelocidad o3 = new OrganismoVelocidad(1,2,2,2);
        //OrganismoVision o4 = new OrganismoVision(1,2,2,2);
        Mapa m = new Mapa();
        m.LlenarMapa();
        m.mapaEnteros();
        m.getMapaEnteros();
        //m.EncontrarFilaJugador();
        //m.EncontrarColumnaJugador();
        //m.reflejarMovimiento();
        // m.getMapaEnteros();
        // m.getMapaAlimentos();
        //m.getMapaEnteros();
        

    }
}