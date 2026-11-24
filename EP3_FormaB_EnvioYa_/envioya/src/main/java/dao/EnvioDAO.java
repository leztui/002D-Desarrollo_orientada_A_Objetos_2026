package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Envio;
import model.EnvioInternacional;
import model.EnvioNacional;

public class EnvioDAO {

    public List<Envio> listarTodosLosEnvios() throws SQLException {
        List<Envio> listaEnviosRegistrados = new ArrayList<>();
        String consultaSqlListar = "SELECT id, tipo, destinatario, tarifa_base, peso_kg, "
                + "distancia_km, es_fragil, dias_estimados, impuesto_aduana "
                + "FROM envios ORDER BY id ASC";

        try (Connection conexionBaseDatos = ConexionDB.obtenerConexion();
             PreparedStatement sentenciaPreparada = conexionBaseDatos.prepareStatement(consultaSqlListar);
             ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {

            while (resultadoConsulta.next()) {
                int identificadorEnvio = resultadoConsulta.getInt("id");
                String tipoEnvio = resultadoConsulta.getString("tipo");
                String destinatarioEnvio = resultadoConsulta.getString("destinatario");
                int tarifaBaseEnvio = resultadoConsulta.getInt("tarifa_base");
                int pesoKilogramosEnvio = resultadoConsulta.getInt("peso_kg");

                if ("NACIONAL".equalsIgnoreCase(tipoEnvio)) {
                    int distanciaKilometros = resultadoConsulta.getInt("distancia_km");
                    boolean esContenidoFragil = "S".equalsIgnoreCase(resultadoConsulta.getString("es_fragil"));
                    EnvioNacional envioNacional = new EnvioNacional(
                            identificadorEnvio,
                            destinatarioEnvio,
                            tarifaBaseEnvio,
                            pesoKilogramosEnvio,
                            distanciaKilometros,
                            esContenidoFragil
                    );
                    listaEnviosRegistrados.add(envioNacional);
                } else if ("INTERNACIONAL".equalsIgnoreCase(tipoEnvio)) {
                    int diasEstimadosTransito = resultadoConsulta.getInt("dias_estimados");
                    int impuestoAduanaEnvio = resultadoConsulta.getInt("impuesto_aduana");
                    EnvioInternacional envioInternacional = new EnvioInternacional(
                            identificadorEnvio,
                            destinatarioEnvio,
                            tarifaBaseEnvio,
                            pesoKilogramosEnvio,
                            diasEstimadosTransito,
                            impuestoAduanaEnvio
                    );
                    listaEnviosRegistrados.add(envioInternacional);
                }
            }
        }

        return listaEnviosRegistrados;
    }

    public boolean insertarEnvio(Envio nuevoEnvio) throws SQLException {
        String consultaSqlInsertar = "INSERT INTO envios "
                + "(tipo, destinatario, tarifa_base, peso_kg, distancia_km, es_fragil, dias_estimados, impuesto_aduana) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexionBaseDatos = ConexionDB.obtenerConexion();
             PreparedStatement sentenciaPreparada = conexionBaseDatos.prepareStatement(consultaSqlInsertar)) {

            sentenciaPreparada.setString(2, nuevoEnvio.getDestinatario());
            sentenciaPreparada.setInt(3, nuevoEnvio.getTarifaBase());
            sentenciaPreparada.setInt(4, nuevoEnvio.getPesoKg());

            if (nuevoEnvio instanceof EnvioNacional envioNacional) {
                sentenciaPreparada.setString(1, "NACIONAL");
                sentenciaPreparada.setInt(5, envioNacional.getDistanciaKm());
                sentenciaPreparada.setString(6, envioNacional.isEsFragil() ? "S" : "N");
                sentenciaPreparada.setNull(7, Types.INTEGER);
                sentenciaPreparada.setNull(8, Types.INTEGER);
            } else if (nuevoEnvio instanceof EnvioInternacional envioInternacional) {
                sentenciaPreparada.setString(1, "INTERNACIONAL");
                sentenciaPreparada.setNull(5, Types.INTEGER);
                sentenciaPreparada.setNull(6, Types.CHAR);
                sentenciaPreparada.setInt(7, envioInternacional.getDiasEstimados());
                sentenciaPreparada.setInt(8, envioInternacional.getImpuestoAduana());
            } else {
                throw new SQLException("Tipo de envío no soportado para inserción.");
            }

            return sentenciaPreparada.executeUpdate() > 0;
        }
    }

    public boolean actualizarEnvio(Envio envioActualizado) throws SQLException {
        String consultaSqlActualizar = "UPDATE envios SET "
                + "tipo = ?, destinatario = ?, tarifa_base = ?, peso_kg = ?, "
                + "distancia_km = ?, es_fragil = ?, dias_estimados = ?, impuesto_aduana = ? "
                + "WHERE id = ?";

        try (Connection conexionBaseDatos = ConexionDB.obtenerConexion();
             PreparedStatement sentenciaPreparada = conexionBaseDatos.prepareStatement(consultaSqlActualizar)) {

            sentenciaPreparada.setString(2, envioActualizado.getDestinatario());
            sentenciaPreparada.setInt(3, envioActualizado.getTarifaBase());
            sentenciaPreparada.setInt(4, envioActualizado.getPesoKg());

            if (envioActualizado instanceof EnvioNacional envioNacional) {
                sentenciaPreparada.setString(1, "NACIONAL");
                sentenciaPreparada.setInt(5, envioNacional.getDistanciaKm());
                sentenciaPreparada.setString(6, envioNacional.isEsFragil() ? "S" : "N");
                sentenciaPreparada.setNull(7, Types.INTEGER);
                sentenciaPreparada.setNull(8, Types.INTEGER);
            } else if (envioActualizado instanceof EnvioInternacional envioInternacional) {
                sentenciaPreparada.setString(1, "INTERNACIONAL");
                sentenciaPreparada.setNull(5, Types.INTEGER);
                sentenciaPreparada.setNull(6, Types.CHAR);
                sentenciaPreparada.setInt(7, envioInternacional.getDiasEstimados());
                sentenciaPreparada.setInt(8, envioInternacional.getImpuestoAduana());
            } else {
                throw new SQLException("Tipo de envío no soportado para actualización.");
            }

            sentenciaPreparada.setInt(9, envioActualizado.getId());
            return sentenciaPreparada.executeUpdate() > 0;
        }
    }

    public boolean eliminarEnvioPorId(int identificadorEnvio) throws SQLException {
        String consultaSqlEliminar = "DELETE FROM envios WHERE id = ?";

        try (Connection conexionBaseDatos = ConexionDB.obtenerConexion();
             PreparedStatement sentenciaPreparada = conexionBaseDatos.prepareStatement(consultaSqlEliminar)) {

            sentenciaPreparada.setInt(1, identificadorEnvio);
            return sentenciaPreparada.executeUpdate() > 0;
        }
    }
}
