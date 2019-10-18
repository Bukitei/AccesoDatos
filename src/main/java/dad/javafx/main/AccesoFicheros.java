package dad.javafx.main;


import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccesoFicheros extends Application {

	TabPane panel;
	Tab filesTb, aleatoryTb, XMLTb;
	Label nameLb, ruteLb;
	TextField ruteTf, fileTf;
	Button createBt, deleteBt, moveBt, vFilesBt, vContentBt, modifyBt;
	ListView<String> listLv;
	TextArea contentAr;
	BorderPane border;
	GridPane grid;
	RadioButton carpetR, fileR;
	HBox buttonsH;
	VBox buttons;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Inicialización de las variables
		
		panel = new TabPane();
		
		filesTb = new Tab();
		filesTb.setText("Acceso a ficheros");
		aleatoryTb = new Tab();
		aleatoryTb.setText("Acceso aleatorio");
		XMLTb = new Tab();
		XMLTb.setText("Acceso XML");
		
		nameLb = new Label();
		nameLb.setText("Borja David Gómez Alayón");
		ruteLb = new Label();
		ruteLb.setText("Ruta Actual");
		
		ruteTf = new TextField();
		ruteTf.setText(System.getProperty("user.home")+"\\Prueba_ficheros");
		fileTf = new TextField();
		fileTf.setPromptText("Carpeta o fichero a crear, eliminar o destino a mover");
		
		createBt = new Button();
		createBt.setText("Crear");
		deleteBt = new Button();
		deleteBt.setText("Eliminar");
		moveBt = new Button();
		moveBt.setText("Mover");
		vFilesBt = new Button();
		vFilesBt.setText("Ver ficheros y carpetas");
		vFilesBt.setOnAction(onComprobar(ruteTf.getText().toString()));
		vContentBt = new Button();
		vContentBt.setText("Ver Contenido Fichero");
		modifyBt = new Button();
		modifyBt.setText("Modificar Fichero");
		
		listLv = new ListView<String>();
		
		contentAr = new TextArea();
		
		grid = new GridPane();
		
		border = new BorderPane();
		
		carpetR = new RadioButton();
		carpetR.setText("Es carpeta");
		fileR = new RadioButton();
		fileR.setText("Es fichero");
		
		ToggleGroup radio = new ToggleGroup();
		radio.getToggles().addAll(carpetR, fileR);
		
		
		buttons = new VBox(5, vContentBt, modifyBt);
		buttonsH = new HBox(5, createBt, deleteBt, moveBt, carpetR, fileR);
		
		//Configuración del BorderPane
		
		border.setTop(nameLb);
		border.setCenter(grid);
		BorderPane.setAlignment(nameLb, Pos.CENTER);
		BorderPane.setAlignment(grid, Pos.CENTER);
		
		//Configuración del GridPane
		
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setPadding(new Insets(5));
		grid.setGridLinesVisible(false);
		grid.addColumn(0);
		grid.addColumn(1);
		grid.addColumn(2);
		grid.addRow(0, ruteLb, ruteTf);
		grid.add(buttonsH, 1, 1);
		grid.addRow(2, fileTf);
		grid.addRow(3, vFilesBt);
		grid.addRow(4, listLv);
		grid.addRow(5, buttons, contentAr);
		
		
		/*grid.getChildren().addAll(ruteLb, ruteTf,
				createBt, deleteBt, moveBt, radios,
				fileTf,
				vFilesBt,
				listLv,
				buttons, contentAr);*/
		
		GridPane.setHgrow(ruteTf, Priority.ALWAYS);
		GridPane.setHgrow(fileTf, Priority.ALWAYS);
		GridPane.setHgrow(listLv, Priority.ALWAYS);
		GridPane.setHgrow(contentAr, Priority.ALWAYS);
		GridPane.setHalignment(buttonsH, HPos.CENTER);
		grid.getColumnConstraints().add(new ColumnConstraints(200));
		GridPane.setFillWidth(ruteLb, false);
		
		grid.setAlignment(Pos.CENTER);
		
		
		//configuración del contenido del tab y el tabPane
		
		filesTb.setContent(border);
		
		panel.getTabs().addAll(filesTb, aleatoryTb, XMLTb);
		
		
		//Escena
		
		Scene scene = new Scene(panel, 620, 400);
		
		primaryStage.setTitle("Acceso a Datos");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private EventHandler<ActionEvent> onComprobar(String string) {
		
		File directory = new File(string);
		
		File[] files = directory.listFiles();
		
		ObservableList<String> names = FXCollections.observableArrayList();
		names.addAll(files.toString());
		ListView<String> listView = new ListView<String>(names);
		this.listLv = listView;
				
		
		return null;
	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
