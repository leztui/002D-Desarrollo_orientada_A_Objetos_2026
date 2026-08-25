import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showMenu();
    }

    static void showMenu(){
        Scanner scanner = new Scanner(System.in);
        Character player_character = new Character();

        boolean flag_menu = true;

        System.out.println("****WELCOME TO THE SEVEN KINGDOMS****");

        while (flag_menu) {
            System.out.println("\n1.- CREATE CHARACTER");
            System.out.println("2.- VISUALIZE CHARACTER");
            System.out.println("3.- EXIT");
            System.out.println("CHOOSE AN OPTION:");

            String user_option = scanner.nextLine();

            switch (user_option) {
                case "1":
                    
                    break;

                case "2":
                    System.out.println("\n--- VER PERSONAJE ---");
                    System.out.println("NOMBRE: " + player_character.ShowName());
                    System.out.println("HP: " + player_character.mostrarVida());
                    System.out.println("MANÁ: " + player_character.mostrarMana());
                    System.out.println("AGE: " + player_character.mostrarEdad());
                    break;

                case "3":
                    System.out.println("GRACIAS POR JUGAR, VUELVA PRONTO");
                    flag_menu = false;
                    break;
            
                default:
                    System.out.println("OPCIÓN INVÁLIDA");
                    break;
            }
        }
        scanner.close();
    }
}