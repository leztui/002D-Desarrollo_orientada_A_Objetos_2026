package SRC;
public class Canil {
  
    public static void main(String[] args) {
     Perro perro_de_martin = new Perro();
        
    perro_de_martin.nombre = "porki";
    perro_de_martin.edad = 5;
    perro_de_martin.raza = "Quiltro";
    perro_de_martin.peso = 40.5;
    perro_de_martin.vacunado = true;

    if (perro_de_martin.edad > 2) {
     System.out.println("Tiene mas de 2 años ");
    }else{
        System.out.println("No tiene mas de dos años");
    }
    if (perro_de_martin.peso > 20 ) {
        System.out.println("Su perro esta flaquito, su perro pesa" + perro_de_martin.peso);
    }else{
        System.out.println("Su perro esta gordito su perro pesa" + perro_de_martin.peso);
    }
    if(perro_de_martin.vacunado = true)
    System.out.println("Su perro puede entrar al canil");
    else{
    System.out.println("Su perro no puede entrar al canil");
    }


    }
}
