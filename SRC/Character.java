public class Character {
    private String name;
    private int hit_points;
    private int magic_points;
    boolean is_ally;
    private int age;

    // Métodos para el Nombre
    public void AsignarNombre(String inputName) {
        String[] garabatos = {"pinochet", "macaco", "faggot"}; 
        String nombreLimpio = inputName;
        for (String garabato : garabatos) {
            nombreLimpio = nombreLimpio.replaceAll("(?i)" + garabato, "***");
        }
        this.name = nombreLimpio;
    }

    public void ValidarNombre(String name_character) {
        this.name = name_character;
    }

    public String ShowName() {
        return this.name;
    }

    // Métodos para la Edad
    public void validarEdad(int age) {
        if (age < 0) {
            System.out.println("Dado que le asignaste una edad negativa, el programa la dejó en 0");
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public int mostrarEdad() {
        return this.age;
    }

    // Métodos para la Vida (HP)
    public void asignarVida(int hit_points) {
        if (hit_points < 0) {
            this.hit_points = 0;
        } else {
            this.hit_points = hit_points;
        }
    }

    public int mostrarVida() {
        return this.hit_points;
    }

    // Métodos para el Maná (Magic Points)
    public void asignarMana(int magic_points) {
        if (magic_points < 0) {
            this.magic_points = 0;
        } else {
            this.magic_points = magic_points;
        }
    }

    public int mostrarMana() {
        return this.magic_points;
    }
}