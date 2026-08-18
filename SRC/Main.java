import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showmenu();

    }
        static void showmenu(){{
        Scanner scanner = new Scanner(System.in);
        Character  player_character = new Character();
        boolean flag_menu = true;

        System.out.println("Welcome to the seven kingdoms"); 

        while (flag_menu) {
        System.out.println("1... Create Character");
        System.out.println("2... Visualize character");
        System.out.println("3.. Exit");

        System.out.println("Choose an option");

        String user_option = scanner.nextLine();
        switch (user_option) {
            case "1":
                System.out.println("Creating Character");

                System.out.println("Ingrese nombre");

                player_character.name = scanner.nextLine();

                System.out.println("Ingrese puntos de vida ");
                
                player_character.asignar_vida(Integer.parseInt(scanner.nextLine()));

                System.out.println("Ingrese puntos de magia ");

                player_character.Magic_Points =  Integer.parseInt(scanner.nextLine());
                

                break;
            case"2":
            System.out.println("Ver personaje");
            System.out.println("Nombre " + player_character.name);
            System.out.println("HP: " + player_character.Hit_Point);
            System.out.println("MB  " + player_character.Magic_Points);


             break; 


            case"3":
            System.out.println("Hasta la proxima ");

            flag_menu = false;
            break;

            default:
                System.out.println("Opcion invalida ");
            break;
        }
      
        }}
    }
        
}
    

