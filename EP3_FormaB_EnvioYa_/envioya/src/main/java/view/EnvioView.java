package view;

import controller.EnvioController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Envio;
import model.EnvioInternacional;
import model.EnvioNacional;

public class EnvioView {

    private final EnvioController controladorEnvios;
    private TableView<Envio> tablaEnviosRegistrados;
    private ComboBox<String> selectorTipoEnvio;
    private TextField campoDestinatario;
    private TextField campoTarifaBase;
    private TextField campoPesoKilogramos;
    private TextField campoDistanciaKilometros;
    private CheckBox casillaContenidoFragil;
    private TextField campoDiasEstimados;
    private TextField campoImpuestoAduana;
    private VBox contenedorCamposNacionales;
    private VBox contenedorCamposInternacionales;
    private Label etiquetaEstadoOperacion;
    private Integer idSeleccionado;

    public EnvioView() {
        this.controladorEnvios = new EnvioController();
        this.idSeleccionado = null;
    }

    public Scene crearEscena() {
        VBox panelTablaSuperior = crearPanelTabla();
        VBox panelFormularioInferior = crearPanelFormulario();

        VBox.setVgrow(panelTablaSuperior, Priority.ALWAYS);

        VBox contenedorPrincipal = new VBox(14, panelTablaSuperior, panelFormularioInferior);
        contenedorPrincipal.setPadding(new Insets(18));

        cargarDatosEnTabla();
        return new Scene(contenedorPrincipal, 920, 650);
    }

    private VBox crearPanelTabla() {
        Label etiquetaTituloTabla = new Label("Envíos Registrados en Oracle");
        etiquetaTituloTabla.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        tablaEnviosRegistrados = new TableView<>();
        tablaEnviosRegistrados.setPrefHeight(240);

        TableColumn<Envio, Number> columnaIdentificador = new TableColumn<>("ID");
        columnaIdentificador.setCellValueFactory(celda -> new SimpleIntegerProperty(celda.getValue().getId()));
        columnaIdentificador.setPrefWidth(60);

        TableColumn<Envio, String> columnaTipo = new TableColumn<>("Tipo");
        columnaTipo.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getTipo()));
        columnaTipo.setPrefWidth(130);

        TableColumn<Envio, String> columnaDestinatario = new TableColumn<>("Destinatario");
        columnaDestinatario.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getDestinatario()));
        columnaDestinatario.setPrefWidth(190);

        TableColumn<Envio, Number> columnaPeso = new TableColumn<>("Peso (kg)");
        columnaPeso.setCellValueFactory(celda -> new SimpleIntegerProperty(celda.getValue().getPesoKg()));
        columnaPeso.setPrefWidth(90);

        TableColumn<Envio, String> columnaDetalle = new TableColumn<>("Detalle");
        columnaDetalle.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getDetalle()));
        columnaDetalle.setPrefWidth(260);

        TableColumn<Envio, Number> columnaCostoTotal = new TableColumn<>("Costo Total ($)");
        columnaCostoTotal.setCellValueFactory(celda -> new SimpleIntegerProperty(celda.getValue().getCostoTotal()));
        columnaCostoTotal.setPrefWidth(130);

        tablaEnviosRegistrados.getColumns().addAll(
                columnaIdentificador,
                columnaTipo,
                columnaDestinatario,
                columnaPeso,
                columnaDetalle,
                columnaCostoTotal
        );

        tablaEnviosRegistrados.getSelectionModel().selectedItemProperty().addListener(
                (observable, envioAnterior, envioActual) -> {
                    if (envioActual != null) {
                        rellenarFormularioConEnvio(envioActual);
                    }
                }
        );

        VBox.setVgrow(tablaEnviosRegistrados, Priority.ALWAYS);
        return new VBox(8, etiquetaTituloTabla, tablaEnviosRegistrados);
    }

    private VBox crearPanelFormulario() {
        Label etiquetaTituloFormulario = new Label("EnvíoYa - Gestión de Envíos");
        etiquetaTituloFormulario.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        GridPane grillaCamposComunes = new GridPane();
        grillaCamposComunes.setHgap(12);
        grillaCamposComunes.setVgap(10);

        selectorTipoEnvio = new ComboBox<>();
        selectorTipoEnvio.getItems().addAll("NACIONAL", "INTERNACIONAL");
        selectorTipoEnvio.setValue("NACIONAL");

        campoDestinatario = new TextField();
        campoDestinatario.setPromptText("Ej: Sebastián Morales");

        campoTarifaBase = new TextField();
        campoTarifaBase.setPromptText("Ej: 6500");

        campoPesoKilogramos = new TextField();
        campoPesoKilogramos.setPromptText("Ej: 3");

        grillaCamposComunes.add(new Label("Tipo de envío:"), 0, 0);
        grillaCamposComunes.add(selectorTipoEnvio, 1, 0);
        grillaCamposComunes.add(new Label("Destinatario:"), 0, 1);
        grillaCamposComunes.add(campoDestinatario, 1, 1);
        grillaCamposComunes.add(new Label("Tarifa base ($):"), 0, 2);
        grillaCamposComunes.add(campoTarifaBase, 1, 2);
        grillaCamposComunes.add(new Label("Peso (kg):"), 0, 3);
        grillaCamposComunes.add(campoPesoKilogramos, 1, 3);

        construirBloquesDinamicos();

        selectorTipoEnvio.setOnAction(eventoCambioTipo -> actualizarBloquesSegunTipo());
        actualizarBloquesSegunTipo();

        Button botonGuardar = new Button("Guardar");
        Button botonActualizar = new Button("Actualizar");
        Button botonEliminar = new Button("Eliminar");
        Button botonLimpiar = new Button("Limpiar");

        botonGuardar.setOnAction(evento -> accionGuardar());
        botonActualizar.setOnAction(evento -> accionActualizar());
        botonEliminar.setOnAction(evento -> accionEliminar());
        botonLimpiar.setOnAction(evento -> limpiarFormulario());

        HBox filaBotonesAccion = new HBox(10, botonGuardar, botonActualizar, botonEliminar, botonLimpiar);
        filaBotonesAccion.setAlignment(Pos.CENTER_LEFT);

        etiquetaEstadoOperacion = new Label("Listo. Conectado a Oracle.");

        return new VBox(
                10,
                etiquetaTituloFormulario,
                grillaCamposComunes,
                contenedorCamposNacionales,
                contenedorCamposInternacionales,
                filaBotonesAccion,
                etiquetaEstadoOperacion
        );
    }

    private void construirBloquesDinamicos() {
        campoDistanciaKilometros = new TextField();
        campoDistanciaKilometros.setPromptText("Ej: 420");
        casillaContenidoFragil = new CheckBox("Contenido frágil (+$3.500)");

        GridPane grillaNacional = new GridPane();
        grillaNacional.setHgap(12);
        grillaNacional.setVgap(8);
        grillaNacional.add(new Label("Distancia (km):"), 0, 0);
        grillaNacional.add(campoDistanciaKilometros, 1, 0);
        grillaNacional.add(casillaContenidoFragil, 1, 1);

        contenedorCamposNacionales = new VBox(8, grillaNacional);
        contenedorCamposNacionales.setPadding(new Insets(10));
        contenedorCamposNacionales.setStyle("-fx-background-color: #e8f5e9; -fx-background-radius: 6;");

        campoDiasEstimados = new TextField();
        campoDiasEstimados.setPromptText("Ej: 10");
        campoImpuestoAduana = new TextField();
        campoImpuestoAduana.setPromptText("Ej: 14500");

        GridPane grillaInternacional = new GridPane();
        grillaInternacional.setHgap(12);
        grillaInternacional.setVgap(8);
        grillaInternacional.add(new Label("Días estimados:"), 0, 0);
        grillaInternacional.add(campoDiasEstimados, 1, 0);
        grillaInternacional.add(new Label("Aduana ($):"), 0, 1);
        grillaInternacional.add(campoImpuestoAduana, 1, 1);

        contenedorCamposInternacionales = new VBox(8, grillaInternacional);
        contenedorCamposInternacionales.setPadding(new Insets(10));
        contenedorCamposInternacionales.setStyle("-fx-background-color: #e3f2fd; -fx-background-radius: 6;");
    }

    private void actualizarBloquesSegunTipo() {
        boolean esNacionalSeleccionado = "NACIONAL".equals(selectorTipoEnvio.getValue());
        contenedorCamposNacionales.setVisible(esNacionalSeleccionado);
        contenedorCamposNacionales.setManaged(esNacionalSeleccionado);
        contenedorCamposInternacionales.setVisible(!esNacionalSeleccionado);
        contenedorCamposInternacionales.setManaged(!esNacionalSeleccionado);
    }

    private void rellenarFormularioConEnvio(Envio envioSeleccionado) {
        idSeleccionado = envioSeleccionado.getId();
        campoDestinatario.setText(envioSeleccionado.getDestinatario());
        campoTarifaBase.setText(Integer.toString(envioSeleccionado.getTarifaBase()));
        campoPesoKilogramos.setText(Integer.toString(envioSeleccionado.getPesoKg()));

        if (envioSeleccionado instanceof EnvioNacional envioNacional) {
            selectorTipoEnvio.setValue("NACIONAL");
            campoDistanciaKilometros.setText(Integer.toString(envioNacional.getDistanciaKm()));
            casillaContenidoFragil.setSelected(envioNacional.isEsFragil());
            campoDiasEstimados.clear();
            campoImpuestoAduana.clear();
        } else if (envioSeleccionado instanceof EnvioInternacional envioInternacional) {
            selectorTipoEnvio.setValue("INTERNACIONAL");
            campoDiasEstimados.setText(Integer.toString(envioInternacional.getDiasEstimados()));
            campoImpuestoAduana.setText(Integer.toString(envioInternacional.getImpuestoAduana()));
            campoDistanciaKilometros.clear();
            casillaContenidoFragil.setSelected(false);
        }

        actualizarBloquesSegunTipo();
        etiquetaEstadoOperacion.setText("Editando envío ID #" + idSeleccionado);
    }

    public void cargarDatosEnTabla() {
        ObservableList<Envio> datosEnvios = FXCollections.observableArrayList(
                controladorEnvios.obtenerTodosLosEnvios()
        );
        tablaEnviosRegistrados.setItems(datosEnvios);
    }

    public void accionGuardar() {
        try {
            Envio nuevoEnvio = construirEnvioDesdeFormulario(null);
            String resultadoOperacion = controladorEnvios.registrarEnvio(nuevoEnvio);
            if ("OK".equals(resultadoOperacion)) {
                etiquetaEstadoOperacion.setText("Envío guardado correctamente en Oracle.");
                mostrarAlerta(Alert.AlertType.INFORMATION, "Registro Exitoso", "El envío fue guardado en Oracle.");
                cargarDatosEnTabla();
                limpiarFormulario();
            } else {
                etiquetaEstadoOperacion.setText("Error: " + resultadoOperacion);
                mostrarAlerta(Alert.AlertType.WARNING, "Validación", resultadoOperacion);
            }
        } catch (NumberFormatException excepcionNumero) {
            etiquetaEstadoOperacion.setText("Error: Los campos numéricos deben contener números enteros.");
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Inválido", "Los campos numéricos deben contener números enteros válidos.");
        }
    }

    public void accionActualizar() {
        if (idSeleccionado == null) {
            etiquetaEstadoOperacion.setText("Selecciona primero un envío de la tabla.");
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Seleccione un envío de la tabla para actualizar.");
            return;
        }
        try {
            Envio envioModificado = construirEnvioDesdeFormulario(idSeleccionado);
            String resultadoOperacion = controladorEnvios.modificarEnvio(envioModificado);
            if ("OK".equals(resultadoOperacion)) {
                etiquetaEstadoOperacion.setText("Envío ID #" + idSeleccionado + " actualizado en Oracle.");
                mostrarAlerta(Alert.AlertType.INFORMATION, "Actualización Exitosa", "El envío fue actualizado en Oracle.");
                cargarDatosEnTabla();
                limpiarFormulario();
            } else {
                etiquetaEstadoOperacion.setText("Error: " + resultadoOperacion);
                mostrarAlerta(Alert.AlertType.WARNING, "Validación", resultadoOperacion);
            }
        } catch (NumberFormatException excepcionNumero) {
            etiquetaEstadoOperacion.setText("Error: Los campos numéricos deben contener números enteros.");
            mostrarAlerta(Alert.AlertType.ERROR, "Formato Inválido", "Los campos numéricos deben contener números enteros válidos.");
        }
    }

    public void accionEliminar() {
        if (idSeleccionado == null) {
            etiquetaEstadoOperacion.setText("Selecciona primero un envío de la tabla.");
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Seleccione un envío de la tabla para eliminar.");
            return;
        }
        String resultadoOperacion = controladorEnvios.eliminarEnvio(idSeleccionado);
        if ("OK".equals(resultadoOperacion)) {
            etiquetaEstadoOperacion.setText("Envío eliminado de Oracle.");
            mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Exitosa", "El envío fue eliminado de Oracle.");
            cargarDatosEnTabla();
            limpiarFormulario();
        } else {
            etiquetaEstadoOperacion.setText("Error: " + resultadoOperacion);
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso", resultadoOperacion);
        }
    }

    private Envio construirEnvioDesdeFormulario(Integer identificadorExistente) {
        String destinatarioIngresado = campoDestinatario.getText();
        int tarifaBaseIngresada = Integer.parseInt(campoTarifaBase.getText().trim());
        int pesoKilogramosIngresado = Integer.parseInt(campoPesoKilogramos.getText().trim());
        String tipoSeleccionado = selectorTipoEnvio.getValue();

        if ("NACIONAL".equals(tipoSeleccionado)) {
            int distanciaIngresada = Integer.parseInt(campoDistanciaKilometros.getText().trim());
            boolean fragilSeleccionado = casillaContenidoFragil.isSelected();
            if (identificadorExistente == null) {
                return new EnvioNacional(destinatarioIngresado, tarifaBaseIngresada, pesoKilogramosIngresado, distanciaIngresada, fragilSeleccionado);
            }
            return new EnvioNacional(identificadorExistente, destinatarioIngresado, tarifaBaseIngresada, pesoKilogramosIngresado, distanciaIngresada, fragilSeleccionado);
        } else {
            int diasEstimadosIngresados = Integer.parseInt(campoDiasEstimados.getText().trim());
            int impuestoAduanaIngresado = Integer.parseInt(campoImpuestoAduana.getText().trim());
            if (identificadorExistente == null) {
                return new EnvioInternacional(destinatarioIngresado, tarifaBaseIngresada, pesoKilogramosIngresado, diasEstimadosIngresados, impuestoAduanaIngresado);
            }
            return new EnvioInternacional(identificadorExistente, destinatarioIngresado, tarifaBaseIngresada, pesoKilogramosIngresado, diasEstimadosIngresados, impuestoAduanaIngresado);
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        selectorTipoEnvio.setValue("NACIONAL");
        campoDestinatario.clear();
        campoTarifaBase.clear();
        campoPesoKilogramos.clear();
        campoDistanciaKilometros.clear();
        casillaContenidoFragil.setSelected(false);
        campoDiasEstimados.clear();
        campoImpuestoAduana.clear();
        tablaEnviosRegistrados.getSelectionModel().clearSelection();
        actualizarBloquesSegunTipo();
    }

    private void mostrarAlerta(Alert.AlertType tipoAlerta, String tituloAlerta, String mensajeAlerta) {
        Alert ventanaAlerta = new Alert(tipoAlerta);
        ventanaAlerta.setTitle(tituloAlerta);
        ventanaAlerta.setHeaderText(null);
        ventanaAlerta.setContentText(mensajeAlerta);
        ventanaAlerta.showAndWait();
    }
}
