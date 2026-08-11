package SRC;

public class Canil {
  
    public static void main(String[] args) {
     Perro perro_de_camilo = new Perro();

    Perro perro_de_alexander = new Perro();

    Perro perro_de_rogelio = new Perro();
        
    perro_de_camilo.nombre = "porki";
    perro_de_camilo.edad = 5;
    perro_de_camilo.raza = "Quiltro";
    perro_de_camilo.peso = 40.5;
    perro_de_camilo.vacunado = true;

    perro_de_alexander.nombre = "sasha";
    perro_de_alexander.edad = 2;
    perro_de_alexander.raza = "Pastor Aleman";
    perro_de_alexander.peso = 35;
    perro_de_alexander.vacunado = true;

    perro_de_rogelio.nombre = "Almendra";
    perro_de_rogelio.edad = 4;
    perro_de_rogelio.raza = "Quiltro";
    perro_de_rogelio.peso = 15;
    perro_de_rogelio.vacunado = true;

    System.out.println("Los siguiente animales han ingresado al canil");

    System.out.println(perro_de_alexander );
    System.out.println(perro_de_rogelio );
    String ladrido = perro_de_rogelio.ladrar();
    System.out.println(ladrido);
    System.out.println(perro_de_camilo );
    }

    

}
