public class OrganismoBasico{
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
}
