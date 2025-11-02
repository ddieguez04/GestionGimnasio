package app;

public enum Especialidad {
    FITNESS("fitness"),
    PISCINA("piscina"),
    CICLISMO("ciclismo"),
    HIIT("hiit"),
    CORE("core"),
    BAILE("baile"),
    BODYCARE("bodycore"),
    CARDIO("cardio");
    
    String nombre;
    
    Especialidad(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
