public class OrganismoVision extends OrganismoBasico {

    public OrganismoVision(int energia, int vision, int velocidad, int edad) {
        super(energia, vision, velocidad, edad);
    }
    /*
    public int getIVI(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapaEnteros()[i][j] == 4){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getJVI(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapaEnteros()[i][j] == 4){
                    return j;
                }
            }
        }
        return 0;
    }

    public int buscarComidaVi() {
        int i = getIVI();
        int j = getJVI();
        if (getIVI() - vision < 0) {
            i = 0;
            for (int o = 0; o < vision; o++) {
                if(getJVI() - vision < 0){
                    j = 0;
                    for (int p = 0 ; p < vision; p++) {
                        if (TableroEntero[o][p] == 2) {
                            if(o > getIVI()){
                                return 1;
                            }
                            if (o < getIVI()){
                                return 2;
                            }
                            else{
                                if(j > getJVI()){
                                    return 3;
                                }
                                if(j < getJVI()){
                                    return 4;
                                }
                            }
                        }
                    }
                }

                else{
                for (int k = j- vision ; k < vision; k++) {
                    if (TableroEntero[o][k] == 2) {
                        if(o > getIVI()){
                            return 1;
                        }
                        if (o < getIVI()){
                            return 2;
                        }
                        else{
                            if(j > getJVI()){
                                return 3;
                            }
                            if(j < getJVI()){
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
                            if (i > getIVI()) {
                                return 1;
                            }
                            if (i < getIVI()) {
                                return 2;
                            } else {
                                if (j > getJVI()) {
                                    return 3;
                                }
                                if (j < getJVI()) {
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
                        if (i > getIVI()) {
                            return 1;
                        }
                        if (i < getIVI()) {
                            return 2;
                        } else {
                            if (j > getJVI()) {
                                return 3;
                            }
                            if (j < getJVI()) {
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
                Tablero[getIVI() + 1][getJVI()] = o4;
                Tablero[getIVI()][getJVI()] = null;
                TableroEntero[getIVI() + 1][getJVI()] = 4;
                TableroEntero[getIVI()][getJVI()] = 0;

            }
        if(x == 2){
                Tablero[getIVI() - 1][getJVI()] = o4;
                Tablero[getIVI()][getJVI()] = null;
                TableroEntero[getIVI() - 1][getJVI()] = 4;
                TableroEntero[getIVI()][getJVI()] = 0;

            }

        if(x == 3){
                Tablero[getIVI()][getJVI() + 1] = o4;
                Tablero[getIVI()][getJVI()] = null;
                TableroEntero[getIVI()][getJVI() + 1] = 4;
                TableroEntero[getIVI()][getJVI()] = 0;

            }

        if(x == 4){
                Tablero[getIVI()][getJVI() - 1] = o4;
                Tablero[getIVI()][getJVI()] = null;
                TableroEntero[getIVI()][getJVI() - 1] = 4;
                TableroEntero[getIVI()][getJVI()] = 0;

            }
        }

*/
}
