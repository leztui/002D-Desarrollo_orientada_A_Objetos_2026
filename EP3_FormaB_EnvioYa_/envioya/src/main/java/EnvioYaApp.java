import javafx.application.Application;
import javafx.stage.Stage;
import view.EnvioView;

public class EnvioYaApp extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        EnvioView vistaPrincipalEnvios = new EnvioView();
        escenarioPrincipal.setTitle("EnvíoYa - Gestión de Envíos y Encomiendas");
        escenarioPrincipal.setScene(vistaPrincipalEnvios.crearEscena());
        escenarioPrincipal.show();
    }

    public static void main(String[] argumentosLineaComandos) {
        launch(argumentosLineaComandos);
    }
}
