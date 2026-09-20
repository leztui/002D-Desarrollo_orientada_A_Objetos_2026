import java.util.ArrayList;
import java.util.Scanner;



public class Tienda {



  static ArrayList<ProductoFisico> coleccion_juegos_fisicos = new ArrayList<>();



  static ArrayList<ProductoDigital> coleccion_juegos_digitales = new ArrayList<>();



  static Scanner sc = new Scanner(System.in);



  public static void main(String[] args) {

    mostrarMenu();

  }



  static void mostrarMenu() {

    boolean mostrar_menu = true;



    while (mostrar_menu) {

      System.out.println("1. Registrar producto");

      System.out.println("2. Listar inventario");

      System.out.println("3. Buscar producto por nombre");

      System.out.println("4. Vender producto");

      System.out.println("5. Resumen del inventario");

      System.out.println("6. Salir");

      System.out.println("7. Agregar datos de prueba");



      String opcion_menu = sc.nextLine();



      switch (opcion_menu) {

        case "1":

          registrarProducto();

          break;



        case "2":

          listarInventario();

          break;



        case "3":
          buscarProducto();



          break;



        case "4":
          vedenderProducto();



          break;



        case "5":
          ResumneInventario();
          break;

        case "6":
          System.out.println("Pagina cerrada, Hasta Luego ");
          mostrar_menu = false;
          break;

        case "7":
          agregarDatosDePrueba();
          break;

        default:
          System.out.println("Opcion no valida,Intente nuevamente ");
          break;

      }

    }

  }



  static public void registrarProducto() {

    System.out.println("******INGRESO DE JUEGOS A LA PLATAFORMA*****");

    boolean mostrar_menu = true;



    while (mostrar_menu) {

      System.out.println("1. Producto físico");

      System.out.println("2. Producto digital");

      System.out.println("3. Volver al menú");



      System.out.println("Escoja una opción");



      String opcion_registrar_juego = sc.nextLine();



      switch (opcion_registrar_juego) {

        case "1":

          registrarProductoFisico();

          break;

        case "2":

          registrarProductoDigital();

          break;

        case "3":

          mostrar_menu = false;

          break;



        default:

          break;

      }

    }

  }



  static public void registrarProductoFisico() {

    System.out.println("Ingrese el nombre del juego");

    String nombre = sc.nextLine();



    System.out.println("Ingrese el precio base del juego");

    int precioBase = Integer.parseInt(sc.nextLine());



    System.out.println("Ingrese el stock del juego");

    int stock = Integer.parseInt(sc.nextLine());



    System.out.println("Ingrese costo de envio del juego");

    int costoEnvio = Integer.parseInt(sc.nextLine());



    ProductoFisico juego = new ProductoFisico(nombre, precioBase, stock, costoEnvio);



    if (coleccion_juegos_fisicos.add(juego)) {

      System.out.println("Juego agregado");

    } else {

      System.err.println("No se pudo agregar :C");

    }

  }



  static public void registrarProductoDigital() {

    System.out.println("Ingrese el nombre del juego");

    String nombre = sc.nextLine();



    System.out.println("Ingrese el precio base del juego");

    int precioBase = Integer.parseInt(sc.nextLine());



    System.out.println("Ingrese el stock del juego");

    int stock = Integer.parseInt(sc.nextLine());



    System.out.println("Ingrese descueto del juego");

    int descuento = Integer.parseInt(sc.nextLine());



    System.out.println("Ingrese la plataforma del juego");

    String plataforma = sc.nextLine();



    ProductoDigital juego = new ProductoDigital(nombre, precioBase, stock, descuento, plataforma);



    if (coleccion_juegos_digitales.add(juego)) {

      System.out.println("Juego agregado");

    } else {

      System.err.println("No se pudo agregar :C");

    }

  }



  static public void listarInventario() {

    System.out.println("****INVENTARIO DE JUEGOS******");



    System.out.println("****INVENTARIO DE JUEGOS Físicos******");



    for (ProductoFisico juego_fisico : coleccion_juegos_fisicos) {

      System.out.println(juego_fisico.mostrarInfo());

      ;

    }



    System.out.println("****INVENTARIO DE JUEGOS DIGITAL******");

    for (ProductoDigital juego_digital : coleccion_juegos_digitales) {

      System.out.println(juego_digital.mostrarInfo());

    }



  }



  static void agregarDatosDePrueba() {

    System.out.println("Agregando juegos fisicos");



    coleccion_juegos_fisicos.add(new ProductoFisico("Pokémon Perla", 27000, 20, 3000));



    coleccion_juegos_fisicos.add(new ProductoFisico("Pokémon Diamante", 27000, 14, 3000));



    coleccion_juegos_fisicos.add(new ProductoFisico("GTA V", 32000, 26, 3000));



    coleccion_juegos_fisicos.add(new ProductoFisico("Majoras Mask", 150000, 2, 3000));



    coleccion_juegos_fisicos.add(new ProductoFisico("PayDay2", 6000, 6, 3000));



    coleccion_juegos_digitales.add(new ProductoDigital("Halo", 9000, 20, 45, "PC"));



    coleccion_juegos_digitales.add(new ProductoDigital("Balatro", 12000, 46, 5, "PC"));



    System.out.println("********DATOS DE PRUEBA CARGADOS******");



  }
  static void buscarProducto() {
    System.out.println("Ingrese la inicial o parte del nombre del producto a buscar:");
    String busqueda = sc.nextLine().toLowerCase().replace("é", "e");
    
    
    ArrayList<ProductoFisico> matchesFisicos = new ArrayList<>();
    ArrayList<ProductoDigital> matchesDigitales = new ArrayList<>();

   
    for (ProductoFisico juego : coleccion_juegos_fisicos) {
      String nombreJuego = juego.getNombre().toLowerCase().replace("é", "e");
      if (nombreJuego.contains(busqueda)) {
        matchesFisicos.add(juego);
      }
    }

   
    for (ProductoDigital juego : coleccion_juegos_digitales) {
      String nombreJuego = juego.getNombre().toLowerCase().replace("é", "e");
      if (nombreJuego.contains(busqueda)) {
        matchesDigitales.add(juego);
      }
    }

    int totalMatches = matchesFisicos.size() + matchesDigitales.size();

    if (totalMatches == 0) {
      System.out.println("No se encontró ningún juego con el texto: " + busqueda);
      return;
    }

    
    System.out.println("--- Resultados encontrados ---");
    int contador = 1;

    for (ProductoFisico juego : matchesFisicos) {
      System.out.println(contador + ". [Físico] " + juego.getNombre());
      contador++;
    }

    for (ProductoDigital juego : matchesDigitales) {
      System.out.println(contador + ". [Digital] " + juego.getNombre());
      contador++;
    }

    System.out.println("Seleccione el número del producto para ver sus detalles (o 0 para cancelar):");
    
    
    int opcion;
    try {
      opcion = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Entrada inválida. Debe ingresar un número. Búsqueda cancelada.");
      return;
    }

    if (opcion == 0 || opcion > totalMatches || opcion < 0) {
      System.out.println("Búsqueda cancelada.");
      return;
    }

    
    System.out.println("\n--- Detalles del Producto Seleccionado ---");
    if (opcion <= matchesFisicos.size()) {
      
      ProductoFisico seleccionado = matchesFisicos.get(opcion - 1);
      System.out.println(seleccionado.mostrarInfo());
    } else {
      
      ProductoDigital seleccionado = matchesDigitales.get(opcion - 1 - matchesFisicos.size());
      System.out.println(seleccionado.mostrarInfo());
    }
  }

  static void vedenderProducto() {
    System.out.println("Venta de producto");
    System.out.println("Funcionalidad no implementada todavía.");
  }

  static void ResumneInventario() {
    int totalProductos = coleccion_juegos_fisicos.size() + coleccion_juegos_digitales.size();
    System.out.println("Resumen del inventario:");
    System.out.println("Juegos físicos: " + coleccion_juegos_fisicos.size());
    System.out.println("Juegos digitales: " + coleccion_juegos_digitales.size());
    System.out.println("Total de productos: " + totalProductos);
  }
  static void resumenInventario() {
  System.out.println("**** RESUMEN DEL INVENTARIO ****");
  
  int totalJuegosFisicos = 0;
  for (ProductoFisico juego : coleccion_juegos_fisicos) {
    totalJuegosFisicos += juego.getStock();
  }

  int totalJuegosDigitales = 0;
  for (ProductoDigital juego : coleccion_juegos_digitales) {
    totalJuegosDigitales += juego.getStock();
  }

  System.out.println("Total de copias físicas en stock: " + totalJuegosFisicos);
  System.out.println("Total de copias digitales en stock: " + totalJuegosDigitales);
  System.out.println("Total general de artículos: " + (totalJuegosFisicos + totalJuegosDigitales));
}
}


