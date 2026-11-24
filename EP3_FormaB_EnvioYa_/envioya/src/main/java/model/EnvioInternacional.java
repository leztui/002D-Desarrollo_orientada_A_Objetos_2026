package model;

public class EnvioInternacional extends Envio {

    private int diasEstimados;
    private int impuestoAduana;

    public EnvioInternacional(int id, String destinatario, int tarifaBase, int pesoKg, int diasEstimados, int impuestoAduana) {
        super(id, destinatario, tarifaBase, pesoKg);
        this.diasEstimados = diasEstimados;
        this.impuestoAduana = impuestoAduana;
    }

    public EnvioInternacional(String destinatario, int tarifaBase, int pesoKg, int diasEstimados, int impuestoAduana) {
        super(destinatario, tarifaBase, pesoKg);
        this.diasEstimados = diasEstimados;
        this.impuestoAduana = impuestoAduana;
    }

    @Override
    public String getTipo() {
        return "INTERNACIONAL";
    }

    @Override
    public String getDetalle() {
        return diasEstimados + " días. Aduana $" + impuestoAduana;
    }

    @Override
    public int getCostoTotal() {
        return getTarifaBase() + impuestoAduana;
    }

    public int getDiasEstimados() {
        return diasEstimados;
    }

    public void setDiasEstimados(int diasEstimados) {
        this.diasEstimados = diasEstimados;
    }

    public int getImpuestoAduana() {
        return impuestoAduana;
    }

    public void setImpuestoAduana(int impuestoAduana) {
        this.impuestoAduana = impuestoAduana;
    }
}
