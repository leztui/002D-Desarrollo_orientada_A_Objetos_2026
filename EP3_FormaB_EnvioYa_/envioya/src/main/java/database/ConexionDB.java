package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL_CONEXION_ORACLE = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static final String USUARIO_ORACLE = "usuario_desarrollador";
    private static final String CONTRASENA_ORACLE = "usuario_desarrollador";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(
                URL_CONEXION_ORACLE,
                USUARIO_ORACLE,
                CONTRASENA_ORACLE
        );
    }

    public static void main(String[] argumentosLineaComandos) {
        try (Connection conexionPrueba = obtenerConexion()) {
            System.out.println("Conexión exitosa a Oracle Database.");
        } catch (SQLException excepcionSql) {
            System.err.println("Error al conectar con Oracle: " + excepcionSql.getMessage());
        }
    }
}
