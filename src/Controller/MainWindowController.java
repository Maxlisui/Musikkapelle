package Controller;

import java.io.IOException;
import java.util.ArrayList;
import DBClasses.Hauptwohnsitz;
import DBClasses.Instrument;
import DBClasses.Musikant;
import Utils.GloballyData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController {
	
	private final String centerStyle = "-fx-alignment: CENTER;";
	
	@FXML
    private TableView<Musikant> tView_Musikanten;
	@FXML
    private TableColumn<Musikant, String> column_Musikant_Id;
	@FXML
    private TableColumn<Musikant, String> column_Musikant_Vorname;
	@FXML
    private TableColumn<Musikant, String> column_Musikant_Nachname;
	@FXML
    private TableView<Hauptwohnsitz> tView_Hauptwohnsitze;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Hauptwohnsitz_Id;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Hauptwohnsitz_Strasse;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Hauptwohnsitz_Ort;
    @FXML
    private TableView<Instrument> tView_Instrumente;
    @FXML
    private TableColumn<Instrument, String> column_Instrument_Id;
    @FXML
    private TableColumn<Instrument, String> column_Instrument_Name;
    @FXML
    private TableColumn<Instrument, String> column_Instrument_Preis;
    @FXML
    private Button btn_addMusikant;
    @FXML
    private Button btn_removeMusikant;
    @FXML
    private Button btn_createWohnsitz;
    @FXML
    private Button btn_deleteWohnsitz;
    @FXML
    private Button btn_createInstrument;
    @FXML
    private Button btn_removeInstrument;
    
    /**
     * Gets calles when the UI is generated. Initializes the UI.
     */
    @FXML
    private void initialize()
    {
    	updateMusikanten();
    	updateInstrumente();
    	updateHauptwohnsitze();
    	column_Musikant_Id.setStyle(centerStyle);
    	column_Musikant_Vorname.setStyle(centerStyle);
    	column_Musikant_Nachname.setStyle(centerStyle);
    	column_Hauptwohnsitz_Id.setStyle(centerStyle);
    	column_Hauptwohnsitz_Strasse.setStyle(centerStyle);
    	column_Hauptwohnsitz_Ort.setStyle(centerStyle);
    	column_Instrument_Id.setStyle(centerStyle);
    	column_Instrument_Name.setStyle(centerStyle);
    	column_Instrument_Preis.setStyle(centerStyle);
    	
    	//Doubleclick event for the Musikanten TableView
    	tView_Musikanten.setRowFactory(tv ->
		{
			TableRow<Musikant> row = new TableRow<>();
			row.setOnMouseClicked(event -> 
			{
				if(event.getClickCount() == 2 && !row.isEmpty())
				{
					try{
						
						GloballyData.getInstance().setMusikantIdForEditing(row.getItem().getId());
				    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/EditMusikantView.fxml"));
						Scene editMusikantScene = new Scene(root);
						Stage editMusikantStage = new Stage();
						editMusikantStage.setScene(editMusikantScene);
						editMusikantStage.setTitle("Neuer Musikant");
						editMusikantStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
						editMusikantStage.initModality(Modality.APPLICATION_MODAL);
						editMusikantStage.showAndWait();
				    	updateMusikanten();
				    	updateInstrumente();
				    	updateHauptwohnsitze();
				    	
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});
    	
    	//Doubleclick event for the Hauptwohnsitz TableView
    	tView_Hauptwohnsitze.setRowFactory(tv ->
		{
			TableRow<Hauptwohnsitz> row = new TableRow<>();
			row.setOnMouseClicked(event -> 
			{
				if(event.getClickCount() == 2 && !row.isEmpty())
				{
					try{
						
						GloballyData.getInstance().setWohnsitzIdForEditing(row.getItem().getId());
				    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().
				    			getResource("View/EditHauptwohnsitzView.fxml"));
						Scene editMusikantScene = new Scene(root);
						Stage editMusikantStage = new Stage();
						editMusikantStage.setScene(editMusikantScene);
						editMusikantStage.setTitle("Neuer Musikant");
						editMusikantStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
						editMusikantStage.initModality(Modality.APPLICATION_MODAL);
						editMusikantStage.showAndWait();
				    	updateMusikanten();
				    	updateInstrumente();
				    	updateHauptwohnsitze();
				    	
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});
    	
    	//Doubleclick event for the Instrument TableView
    	tView_Instrumente.setRowFactory(tv ->
		{
			TableRow<Instrument> row = new TableRow<>();
			row.setOnMouseClicked(event -> 
			{
				if(event.getClickCount() == 2 && !row.isEmpty())
				{
					try{
						
						GloballyData.getInstance().setInstrumentIdForEditing(row.getItem().getId());
				    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().
				    			getResource("View/EditInstrumentView.fxml"));
						Scene editMusikantScene = new Scene(root);
						Stage editMusikantStage = new Stage();
						editMusikantStage.setScene(editMusikantScene);
						editMusikantStage.setTitle("Neuer Musikant");
						editMusikantStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
						editMusikantStage.initModality(Modality.APPLICATION_MODAL);
						editMusikantStage.showAndWait();
				    	updateMusikanten();
				    	updateInstrumente();
				    	updateHauptwohnsitze();
				    	
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});
    	
    }
    
    /**
     * Eventhandler for the "Musikant hinzufügen" button
     * @param event MouseEvent handler
     * @throws IOException In case the .fxml could not be found
     */
    @FXML
    void btn_addMusikant_clicked(MouseEvent event) throws IOException 
    {
    	GloballyData.getInstance().setMusikantIdForEditing(-1);
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/EditMusikantView.fxml"));
		Scene createMusikantScene = new Scene(root);
		Stage createMusikantStage = new Stage();
		createMusikantStage.setScene(createMusikantScene);
		createMusikantStage.setTitle("Neuer Musikant");
		createMusikantStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
		createMusikantStage.initModality(Modality.APPLICATION_MODAL);
		createMusikantStage.showAndWait();
    	updateMusikanten();
    	updateInstrumente();
    	updateHauptwohnsitze();
    }

    /**
     * MouseEvent handler for the "Musikant löschen" button
     * @param event MouseEvent handler
     */
    @FXML
    void btn_removeMusikant_clicked(MouseEvent event) 
    {
    	if(tView_Musikanten.getSelectionModel().getSelectedItem() != null)
    	{
	    	GloballyData.getInstance().getMusikanten().get(GloballyData.getInstance().getMusikanten().indexOf(
	    			tView_Musikanten.getSelectionModel().getSelectedItem())).setDeleted(true);
	    	updateMusikanten();
    	}
    }
    
    /**
     * MousEvent handler for the "Wohnsitz hinzufügen" button
     * @param event MousEvent handler
     * @throws IOException In case the .fxml file could not be found
     */
    @FXML
    void btn_addWohnsitz_clicked(MouseEvent event) throws IOException 
    {
    	GloballyData.getInstance().setWohnsitzIdForEditing(-1);
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/EditHauptwohnsitzView.fxml"));
		Scene createWohnsitzScene = new Scene(root);
		Stage createWohnsitzStage = new Stage();
		createWohnsitzStage.setScene(createWohnsitzScene);
		createWohnsitzStage.setTitle("Neuer Wohnsitz");
		createWohnsitzStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
		createWohnsitzStage.initModality(Modality.APPLICATION_MODAL);
		createWohnsitzStage.showAndWait();
    	updateMusikanten();
    	updateInstrumente();
    	updateHauptwohnsitze();
    }

    /**
     * MousEvent handler for the "Wohnsitz löschen" button
     * @param event MouseEvent handler
     */
    @FXML
    void btn_deleteWohnsitz_clicked(MouseEvent event) 
    {
    	if(tView_Hauptwohnsitze.getSelectionModel().getSelectedItem() != null)
    	{
	    	if(GloballyData.getInstance().getMusikanten().stream().filter(x -> 
	    		x.getHauptwohnsitzId() == tView_Hauptwohnsitze.getSelectionModel().getSelectedItem().
	    		getId()).findFirst().orElse(null) == null)
	    	{
	        	GloballyData.getInstance().getHauptwohnsitze().get(GloballyData.getInstance().getHauptwohnsitze().indexOf(
	        			tView_Hauptwohnsitze.getSelectionModel().getSelectedItem())).setDeleted(true);
	        	updateHauptwohnsitze();
	    	}
    	}
    }
    
    /**
     * MouseEvent handler for the "Instrument hinzufügen" button
     * @param event MouseEvent handeler 
     * @throws IOException In case the .fxml file could not be found
     */
    @FXML
    void btn_createInstrument_clicked(MouseEvent event) throws IOException 
    {
    	GloballyData.getInstance().setInstrumentIdForEditing(-1);
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/EditInstrumentView.fxml"));
		Scene createInstrumentScene = new Scene(root);
		Stage createInstrumentStage = new Stage();
		createInstrumentStage.setScene(createInstrumentScene);
		createInstrumentStage.setTitle("Neues Instrument");
		createInstrumentStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
		createInstrumentStage.initModality(Modality.APPLICATION_MODAL);
		createInstrumentStage.showAndWait();
    	updateMusikanten();
    	updateInstrumente();
    	updateHauptwohnsitze();
    }
    
    /**
     * MouseEvent handler for the "Instrument löschen" button
     * @param event MouseEvent handler
     */
    @FXML
    void btn_removeInstrument_clicked(MouseEvent event)
    {
    	if(tView_Instrumente.getSelectionModel().getSelectedItem() != null)
    	{
    		if(GloballyData.getInstance().getMusikantInstrumente().stream().filter(x -> 
    			x.getInstrumentid() == tView_Instrumente.getSelectionModel().getSelectedItem().
    			getId()).findFirst().orElse(null) == null)
    		{
    			GloballyData.getInstance().getInstrumente().get(GloballyData.getInstance().getInstrumente().indexOf(
	        			tView_Instrumente.getSelectionModel().getSelectedItem())).setDeleted(true);
	        	updateInstrumente();
    		}
    	}
    }
    
    /**
     * Updates the Musikanten ListView
     */
    private void updateMusikanten()
    {
    	ArrayList<Musikant> notDeletedMusikanten = new ArrayList<>();
    	
    	for(Musikant m : GloballyData.getInstance().getMusikanten())
    	{
    		if(!m.isDeleted()) notDeletedMusikanten.add(m);
    	}
    		
		tView_Musikanten.setItems(FXCollections.observableArrayList(notDeletedMusikanten));
    	column_Musikant_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    	column_Musikant_Vorname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVorname()));
    	column_Musikant_Nachname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNachname()));
    }
    
    /**
     * updates the Instrumenten ListView
     */
    private void updateInstrumente()
    {
    	ArrayList<Instrument> notDeletedInstrumente = new ArrayList<>();
    	
    	for(Instrument i : GloballyData.getInstance().getInstrumente())
    	{
    		if(!i.isDeleted()) notDeletedInstrumente.add(i);
    	}
    	
    	tView_Instrumente.setItems(FXCollections.observableArrayList(notDeletedInstrumente));
    	column_Instrument_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    	column_Instrument_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    	column_Instrument_Preis.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPreis())));
    }
    
    /**
     * Updates the hauptwohnsitz ListView
     */
    private void updateHauptwohnsitze()
    {
    	ArrayList<Hauptwohnsitz> notDeletedHauptwohnsitze = new ArrayList<>();
    	
    	for(Hauptwohnsitz h : GloballyData.getInstance().getHauptwohnsitze())
    	{
    		if(!h.isDeleted()) notDeletedHauptwohnsitze.add(h);
    	}
    	
    	tView_Hauptwohnsitze.setItems(FXCollections.observableArrayList(notDeletedHauptwohnsitze));
    	column_Hauptwohnsitz_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    	column_Hauptwohnsitz_Strasse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStrasse()));
    	column_Hauptwohnsitz_Ort.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrt()));
    }
}