public class OrganismoBasico{//extends Mapa{
    protected int energia;
    protected int vision;
    protected int velocidad;
    protected int edad;

    public OrganismoBasico(int energia, int vision,int velocidad, int edad){
        this.energia = energia;
        this.vision = vision;
        this.velocidad = velocidad;
        this.edad = edad;
    }
    public int getEnergia(){
        return energia;
    }
    public int getVision(){
        return vision;
    }

    public int getVelocidad(){
        return velocidad;
    }

    public int getEdad(){
        return edad;
    }
/*
    public int getIB(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapaEnteros()[i][j] == 1){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJB(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapaEnteros()[i][j] == 1){
                    return j;
                }
            }
        }
        return 0;
    }

    public int buscarComidaB() {
        int i = getIB();
        int j = getJB();
        if (getIB() - vision < 0) {
            i = 0;
            for (int o = 0; o < vision; o++) {
                if(getJB() - vision < 0){
                    j = 0;
                    for (int p = 0 ; p < vision; p++) {
                        if (TableroEntero[o][p] == 2) {
                            if(o > getIB()){
                                return 1;
                            }
                            if (o < getIB()){
                                return 2;
                            }
                            else{
                                if(j > getJB()){
                                    return 3;
                                }
                                if(j < getJB()){
                                    return 4;
                                }
                            }
                        }
                    }
                }

                else{
                    for (int k = j- vision ; k < vision; k++) {
                        if (TableroEntero[o][k] == 2) {
                            if(o > getIB()){
                                return 1;
                            }
                            if (o < getIB()){
                                return 2;
                            }
                            else{
                                if(j > getJB()){
                                    return 3;
                                }
                                if(j < getJB()){
                                    return 4;
                                }
                            }
                        }
                    }
                }
            }
        }



        else {
            for (int a = i - vision; a < vision; a++) {
                if(j-vision < 0){
                    j = 0;
                    for (int h = 0; h < vision; h++) {
                        if (TableroEntero[a][h] == 2) {
                            if (i > getIB()) {
                                return 1;
                            }
                            if (i < getIB()) {
                                return 2;
                            } else {
                                if (j > getJB()) {
                                    return 3;
                                }
                                if (j < getJB()) {
                                    return 4;
                                }
                            }
                        }
                    }
                }
                else{
                }
                for (int z = j- vision; z < vision; z++) {
                    if (TableroEntero[a][z] == 2) {
                        if (i > getIB()) {
                            return 1;
                        }
                        if (i < getIB()) {
                            return 2;
                        } else {
                            if (j > getJB()) {
                                return 3;
                            }
                            if (j < getJB()) {
                                return 4;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
 */
    public void imprimirDatos(){
        System.out.println("Energia: " + energia);
        System.out.println("Vision: " + vision);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Edad: " + edad);
    }


}
