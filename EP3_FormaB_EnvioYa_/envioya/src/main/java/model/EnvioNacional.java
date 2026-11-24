package model;

public class EnvioNacional extends Envio {

    private static final int RECARGO_CONTENIDO_FRAGIL = 3500;
    private int distanciaKm;
    private boolean esFragil;

    public EnvioNacional(int id, String destinatario, int tarifaBase, int pesoKg, int distanciaKm, boolean esFragil) {
        super(id, destinatario, tarifaBase, pesoKg);
        this.distanciaKm = distanciaKm;
        this.esFragil = esFragil;
    }

    public EnvioNacional(String destinatario, int tarifaBase, int pesoKg, int distanciaKm, boolean esFragil) {
        super(destinatario, tarifaBase, pesoKg);
        this.distanciaKm = distanciaKm;
        this.esFragil = esFragil;
    }

    @Override
    public String getTipo() {
        return "NACIONAL";
    }

    @Override
    public String getDetalle() {
        String indicadorFragil = esFragil ? "Sí" : "No";
        return distanciaKm + " km Frágil: " + indicadorFragil;
    }

    @Override
    public int getCostoTotal() {
        int recargoFinal = esFragil ? RECARGO_CONTENIDO_FRAGIL : 0;
        return getTarifaBase() + recargoFinal;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(int distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public boolean isEsFragil() {
        return esFragil;
    }

    public void setEsFragil(boolean esFragil) {
        this.esFragil = esFragil;
    }
}
