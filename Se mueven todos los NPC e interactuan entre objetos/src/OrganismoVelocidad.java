public class OrganismoVelocidad extends OrganismoBasico{
    
    public OrganismoVelocidad(int energia, int vision,int velocidad, int edad){
        super(energia, vision, velocidad, edad);
    }
    /*
    public int getIVE(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(TableroEntero[i][j] == 3){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJVE(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                if(TableroEntero[i][j] == 3){
                    return j;
                }
            }
        }
        return 0;
    }
/*
    public int buscarComidaVe() {
        int i = getIVE();
        int j = getJVE();
        if (getIVE() - vision < 0) {
            i = 0;
            for (int o = 0; o < vision; o++) {
                if(getJVE() - vision < 0){
                    j = 0;
                    for (int p = 0 ; p < vision; p++) {
                        if (TableroEntero[o][p] == 2) {
                            if(o > getIVE()){
                                return 1;
                            }
                            if (o < getIVE()){
                                return 2;
                            }
                            else{
                                if(j > getJVE()){
                                    return 3;
                                }
                                if(j < getJVE()){
                                    return 4;
                                }
                            }
                        }
                    }
                }

                else{
                    for (int k = j- vision ; k < vision; k++) {
                        if (TableroEntero[o][k] == 2) {
                            if(o > getIVE()){
                                return 1;
                            }
                            if (o < getIVE()){
                                return 2;
                            }
                            else{
                                if(j > getJVE()){
                                    return 3;
                                }
                                if(j < getJVE()){
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
                            if (i > getIVE()) {
                                return 1;
                            }
                            if (i < getIVE()) {
                                return 2;
                            } else {
                                if (j > getJVE()) {
                                    return 3;
                                }
                                if (j < getJVE()) {
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
                        if (i > getIVE()) {
                            return 1;
                        }
                        if (i < getIVE()) {
                            return 2;
                        } else {
                            if (j > getJVE()) {
                                return 3;
                            }
                            if (j < getJVE()) {
                                return 4;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void moverseAcomida(int x){
        if(x == 1){
            Tablero[getIVE() + 1][getJVE()] = o2;
            Tablero[getIVE()][getJVE()] = null;
            TableroEntero[getIVE() + 1][getJVE()] = 3;
            TableroEntero[getIVE()][getJVE()] = 0;

        }
        if(x == 2){
            Tablero[getIVE() - 1][getJVE()] = o2;
            Tablero[getIVE()][getJVE()] = null;
            TableroEntero[getIVE() - 1][getJVE()] = 3;
            TableroEntero[getIVE()][getJVE()] = 0;

        }

        if(x == 3){
            Tablero[getIVE()][getJVE() + 1] = o2;
            Tablero[getIVE()][getJVE()] = null;
            TableroEntero[getIVE()][getJVE() + 1] = 3;
            TableroEntero[getIVE()][getJVE()] = 0;

        }

        if(x == 4){
            Tablero[getIVE()][getJVE() - 1] = o2;
            Tablero[getIVE()][getJVE()] = null;
            TableroEntero[getIVE()][getJVE() - 1] = 3;
            TableroEntero[getIVE()][getJVE()] = 0;

        }
    }
*/
}
