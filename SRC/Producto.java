public abstract class Producto {
  protected String nombre;
  protected int precioBase;
  protected int stock;

  public Producto(String nombre, int precioBase, int stock) {
    this.nombre = nombre;
    this.precioBase = precioBase;
    this.stock = stock;
  }

  public String getNombre() { return nombre; }
  public int getPrecioBase() { return precioBase; }
  public int getStock() { return stock; }
  public void setStock(int stock) { this.stock = stock; }

 
  public abstract int calcularPrecioFinal();

  public String mostrarInfo() {
    return "Nombre: " + nombre + " | Precio Base: $" + precioBase + " | Stock: " + stock;
  }
}