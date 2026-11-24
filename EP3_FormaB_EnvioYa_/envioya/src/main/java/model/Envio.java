package model;

public abstract class Envio {

    private int id;
    private String destinatario;
    private int tarifaBase;
    private int pesoKg;

    public Envio(int id, String destinatario, int tarifaBase, int pesoKg) {
        this.id = id;
        this.destinatario = destinatario;
        this.tarifaBase = tarifaBase;
        this.pesoKg = pesoKg;
    }

    public Envio(String destinatario, int tarifaBase, int pesoKg) {
        this.destinatario = destinatario;
        this.tarifaBase = tarifaBase;
        this.pesoKg = pesoKg;
    }

    public abstract String getTipo();

    public abstract String getDetalle();

    public abstract int getCostoTotal();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public int getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(int tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public int getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(int pesoKg) {
        this.pesoKg = pesoKg;
    }
}
