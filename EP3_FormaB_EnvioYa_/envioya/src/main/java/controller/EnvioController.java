package controller;

import dao.EnvioDAO;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Envio;
import model.EnvioInternacional;
import model.EnvioNacional;

public class EnvioController {

    private final EnvioDAO envioDao;

    public EnvioController() {
        this.envioDao = new EnvioDAO();
    }

    public List<Envio> obtenerTodosLosEnvios() {
        try {
            return envioDao.listarTodosLosEnvios();
        } catch (SQLException excepcionSql) {
            System.err.println("Error al listar envíos desde Oracle: " + excepcionSql.getMessage());
            return Collections.emptyList();
        }
    }

    public String registrarEnvio(Envio nuevoEnvio) {
        String mensajeErrorValidacion = validarDatosEnvio(nuevoEnvio);
        if (mensajeErrorValidacion != null) {
            return mensajeErrorValidacion;
        }
        try {
            boolean operacionExitosa = envioDao.insertarEnvio(nuevoEnvio);
            return operacionExitosa ? "OK" : "No se pudo registrar el envío en la base de datos.";
        } catch (SQLException excepcionSql) {
            return "Error de base de datos al registrar: " + excepcionSql.getMessage();
        }
    }

    public String modificarEnvio(Envio envioEditado) {
        if (envioEditado.getId() <= 0) {
            return "Debe seleccionar un envío válido de la tabla para actualizar.";
        }
        String mensajeErrorValidacion = validarDatosEnvio(envioEditado);
        if (mensajeErrorValidacion != null) {
            return mensajeErrorValidacion;
        }
        try {
            boolean operacionExitosa = envioDao.actualizarEnvio(envioEditado);
            return operacionExitosa ? "OK" : "No se encontró el envío para actualizar.";
        } catch (SQLException excepcionSql) {
            return "Error de base de datos al actualizar: " + excepcionSql.getMessage();
        }
    }

    public String eliminarEnvio(int identificadorEnvio) {
        if (identificadorEnvio <= 0) {
            return "Debe seleccionar un envío válido para eliminar.";
        }
        try {
            boolean operacionExitosa = envioDao.eliminarEnvioPorId(identificadorEnvio);
            return operacionExitosa ? "OK" : "No se encontró el envío a eliminar.";
        } catch (SQLException excepcionSql) {
            return "Error de base de datos al eliminar: " + excepcionSql.getMessage();
        }
    }

    private String validarDatosEnvio(Envio envioEvaluado) {
        if (envioEvaluado == null) {
            return "Los datos del envío no pueden ser nulos.";
        }
        if (envioEvaluado.getDestinatario() == null || envioEvaluado.getDestinatario().trim().isEmpty()) {
            return "El destinatario no puede estar vacío.";
        }
        if (envioEvaluado.getTarifaBase() <= 0) {
            return "La tarifa base debe ser mayor a 0.";
        }
        if (envioEvaluado.getPesoKg() <= 0) {
            return "El peso en kg debe ser mayor a 0.";
        }
        if (envioEvaluado instanceof EnvioNacional envioNacional) {
            if (envioNacional.getDistanciaKm() <= 0) {
                return "La distancia en km debe ser mayor a 0.";
            }
        } else if (envioEvaluado instanceof EnvioInternacional envioInternacional) {
            if (envioInternacional.getDiasEstimados() <= 0) {
                return "Los días estimados deben ser mayores a 0.";
            }
            if (envioInternacional.getImpuestoAduana() < 0) {
                return "El impuesto de aduana no puede ser negativo.";
            }
        }
        return null;
    }
}
