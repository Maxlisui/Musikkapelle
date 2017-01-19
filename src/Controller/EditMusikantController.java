package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import DBClasses.Instrument;
import DBClasses.Musikant;
import DBClasses.MusikantInstrument;
import Utils.GloballyData;
import Utils.ListUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditMusikantController {
	
	private final String centerStyle = "-fx-alignment: CENTER;";
	private ArrayList<Instrument> newlyAddedInstruments = new ArrayList<>();
	private boolean isNewMusikant;
	private Musikant editableMusikant = new Musikant(0, "", "", null, "", "", 0, false);
	private ArrayList<Instrument> currentInstrumente = new ArrayList<>();
	
    @FXML
    private TextField tBox_Vorname;
    @FXML
    private TextField tBox_Nachname;
    @FXML
    private TextField tBox_Email;
    @FXML
    private TextField tBox_Spezialfunktion;
    @FXML
    private DatePicker dBox_Geburtsdatum;
    @FXML
    private Button btn_Save;
    @FXML
    private Button btn_Cancel;
    @FXML
    private TextField tBox_HauptwohnsitzId;
    @FXML
    private Button btn_choose_Hauptwohnsitz;
    @FXML
    private TableView<Instrument> tView_Instrumente;
    @FXML
    private TableColumn<Instrument, String> column_Instrumente_Id;
    @FXML
    private Button btn_add_Instrument;
    @FXML
    private Button btn_remove_Instrument;
    
    /**
     * Gets called when the UI is generated. Initializes the UI.
     */
    @FXML
    private void initialize()
    {
    	if(GloballyData.getInstance().getMusikantIdForEditing() == -1) isNewMusikant = true;
    	else
    	{
    		isNewMusikant = false;
    		editableMusikant = GloballyData.getInstance().getMusikanten().stream().filter(x -> 
    			x.getId() == GloballyData.getInstance().getMusikantIdForEditing()).findFirst().orElse(null);
    		
    		tBox_Vorname.setText(editableMusikant.getVorname());
    		tBox_Nachname.setText(editableMusikant.getNachname());
    		tBox_Email.setText(editableMusikant.getEmail());
    		tBox_Spezialfunktion.setText(editableMusikant.getSpezialfunktion() == null ? "" : editableMusikant.getSpezialfunktion());
    		dBox_Geburtsdatum.setValue(editableMusikant.getGeburtsdatum().toLocalDate());
    		tBox_HauptwohnsitzId.setText(String.valueOf(editableMusikant.getHauptwohnsitzId()));
    		newlyAddedInstruments = ListUtils.getInstance().getAvailableInstrumente(editableMusikant.getId());
    		currentInstrumente = ListUtils.getInstance().getAvailableInstrumente(editableMusikant.getId());
    		updateInstrumente();
    	}
    	column_Instrumente_Id.setStyle(centerStyle);
    }
    
    /**
     * Eventhandler for the "Cancel" button
     * @param event MousEvent handler
     */
    @FXML
    void btn_Cancel_clicked(MouseEvent event) 
    {	
    	close();
    }

    /**
     * Eventhandler for the "Save" button
     * @param event MouseEvent handler
     */
    @FXML
    void btn_Save_clicked(MouseEvent event) 
    {
    	if(isNewMusikant) saveNewMusikant();
    	else saveUpdatedMusikant();
    }
    
    /**
     * Eventhandler for the "Instrument hinzufügen" buttons
     * @param event MouseEvent handler
     * @throws IOException In case the .fxml file could not be found
     */
    @FXML
    void btn_add_Instrument_clicked(MouseEvent event) throws IOException 
    {
    	GloballyData.getInstance().setAlreadyUsedInstrumente(newlyAddedInstruments);
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/ChooseInstrumentView.fxml"));
		Scene chooseInstrumentScene = new Scene(root);
		Stage chooseInstrumentStage = new Stage();
		chooseInstrumentStage.setScene(chooseInstrumentScene);
		chooseInstrumentStage.setTitle("Instrument wählen");
		chooseInstrumentStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
		chooseInstrumentStage.initModality(Modality.APPLICATION_MODAL);
		chooseInstrumentStage.showAndWait();
		if(GloballyData.getInstance().getLastSelectedInstrument() != null)
		{
			newlyAddedInstruments.add(GloballyData.getInstance().getLastSelectedInstrument());
			updateInstrumente();
		}
    }
    
    /**
     * Eventhandler for the "Instrument löschen" button
     * @param event MouseEvent handler
     */
    @FXML
    void btn_remove_Instrument_clicked(MouseEvent event)
    {
    	newlyAddedInstruments.remove(tView_Instrumente.getSelectionModel().getSelectedItem());
    	updateInstrumente();
    }

    /**
     * Evethandler for the "Hauprwohnsitz wählen" button
     * @param event MouseEvent handler 
     * @throws IOException In case the .fxml file could not be found
     */
    @FXML
    void btn_choose_Hauptwohnsitz_clicked(MouseEvent event) throws IOException 
    {
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/ChooseHauptwohnsitzView.fxml"));
		Scene chooseHauptwohnsitzScene = new Scene(root);
		Stage chooseHauptwohnsitzStage = new Stage();
		chooseHauptwohnsitzStage.setScene(chooseHauptwohnsitzScene);
		chooseHauptwohnsitzStage.setTitle("Hauptwohnsitz wählen");
		chooseHauptwohnsitzStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
		chooseHauptwohnsitzStage.initModality(Modality.APPLICATION_MODAL);
		chooseHauptwohnsitzStage.showAndWait();
		if(GloballyData.getInstance().getLastSelectedHauptwohnsitz() != null)
		{
			tBox_HauptwohnsitzId.setText(String.valueOf(GloballyData.getInstance().getLastSelectedHauptwohnsitz().getId()));
		}
    }
    
    /**
     * Updates the Instrumente TableView
     */
    private void updateInstrumente()
    {
    	tView_Instrumente.setItems(FXCollections.observableArrayList(newlyAddedInstruments));
    	column_Instrumente_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    }
    
    /**
     * Closes the given stage
     */
    private void close()
    {
        Stage thisStage = (Stage) btn_Cancel.getScene().getWindow();
        thisStage.close();
    }
    
    /**
     * Saves a new Musikant
     */
    private void saveNewMusikant()
    {
		String vorname = tBox_Vorname.getText();
    	String nachname = tBox_Nachname.getText();
    	String email = tBox_Email.getText();
    	String spezialfunktion = tBox_Spezialfunktion.getText();
    	String hauptwohnsitzIdAsString = tBox_HauptwohnsitzId.getText();
    	int hauptwohnsitzId = 0;
    	Date geburtsdatum = null;
    	boolean success = true;
    	
    	if(!isNullOrEmpty(vorname) && !isNullOrEmpty(nachname) && !isNullOrEmpty(email)&& dBox_Geburtsdatum != null)
    	{
    		try
    		{
        		geburtsdatum = Date.valueOf(dBox_Geburtsdatum.getValue());
    			
    			hauptwohnsitzId = Integer.parseInt(hauptwohnsitzIdAsString);
    			
    			if(ListUtils.getInstance().checkIfHauptwohnsitzExists(hauptwohnsitzId))
    			{
    				int id = ListUtils.getInstance().getLastMusikantId(GloballyData.getInstance().getMusikanten()) + 1;
    				
            		GloballyData.getInstance().getMusikanten().add(
            				new Musikant(id, vorname, nachname, geburtsdatum, email, spezialfunktion, hauptwohnsitzId, true));
            		
            		for(Instrument i : newlyAddedInstruments)
            		{
            			GloballyData.getInstance().getMusikantInstrumente().add(
            					new MusikantInstrument(id, i.getId(), true));
            		}
            		
            		showMessageBox("Neuer Musikant angelegt", "\"Ok\" um ins Hauptmenü zurückzukehren.");
            		close();
    			}
    			else 
    			{
					success = false;
				}
    		}
    		catch (Exception e) 
    		{
    			success = false;
			}
    		if(!success)
    		{
				if(!showMessageBox("Warunung", "Ein Fehler ist aufgetreten",
	        			"\"Ok\" um die Infos zu bearbeiten.\n\"Cancel\" um ins Menü zurückzukehren."))
	        	{
	        		close(); 
	        	}
    		}
    	}
    	else
    	{
        	if(!showMessageBox("Warunung", "Es sind nicht alle notwendigen Informationen gegeben",
        			"\"Ok\" um die Infos zu vervollständigen.\n\"Cancel\" um ins Menü zurückzukehren."))
        	{
        		close(); 
        	}
    	}
    }
    
    /**
     * Saves a updated Musikant
     */
	private void saveUpdatedMusikant()
	{
		String vorname = tBox_Vorname.getText();
    	String nachname = tBox_Nachname.getText();
    	String email = tBox_Email.getText();
    	String spezialfunktion = tBox_Spezialfunktion.getText();
    	String hauptwohnsitzIdAsString = tBox_HauptwohnsitzId.getText();
    	int hauptwohnsitzId = 0;
    	Date geburtsdatum = null;
    	boolean success = true;
    	
    	if(!isNullOrEmpty(vorname) && !isNullOrEmpty(nachname) && !isNullOrEmpty(email)&& dBox_Geburtsdatum != null)
    	{
    		try
    		{
        		geburtsdatum = Date.valueOf(dBox_Geburtsdatum.getValue());
    			
    			hauptwohnsitzId = Integer.parseInt(hauptwohnsitzIdAsString);
    			
    			if(ListUtils.getInstance().checkIfHauptwohnsitzExists(hauptwohnsitzId))
    			{    				
    				GloballyData.getInstance().getMusikanten().remove(editableMusikant);
    				
    				editableMusikant.setVorname(vorname);
    				editableMusikant.setNachname(nachname);
    				editableMusikant.setEmail(email);
    				editableMusikant.setSpezialfunktion(spezialfunktion);
    				editableMusikant.setGeburtsdatum(geburtsdatum);
    				editableMusikant.setHauptwohnsitzId(hauptwohnsitzId);
    				editableMusikant.setUpdated(true);
    				
    				GloballyData.getInstance().getMusikanten().add(editableMusikant);
    				GloballyData.getInstance().getMusikanten().trimToSize();
    				
    				ArrayList<Instrument> newInstrumente = new ArrayList<>();
    				
					for(Instrument i : newlyAddedInstruments)
					{
						if(ListUtils.getInstance().checkIfInstrumentIsNew(i, currentInstrumente)) newInstrumente.add(i);
					}
					
					for(Instrument i : newInstrumente)
					{
						GloballyData.getInstance().getMusikantInstrumente().add(
								new MusikantInstrument(editableMusikant.getId(), i.getId(), true));
					}
					
					ArrayList<Instrument> deletedInstrumente = new ArrayList<>();
					
					for(Instrument i : currentInstrumente)
					{
						if(ListUtils.getInstance().checkifInstrumentIsDeleted(i, newlyAddedInstruments)) deletedInstrumente.add(i);
					}
					
					for(Instrument i : deletedInstrumente)
					{
						GloballyData.getInstance().getInstrumente().get(GloballyData.getInstance().getInstrumente().indexOf(i)).setDeleted(true);
					}
            		
            		showMessageBox("Musikant gespeichert", "\"Ok\" um ins Hauptmenü zurückzukehren.");
            		close();
    			}
    			else 
    			{
					success = false;
				}
    		}
    		catch (Exception e) 
    		{
    			success = false;
			}
    		if(!success)
    		{
				if(!showMessageBox("Warunung", "Ein Fehler ist aufgetreten",
	        			"\"Ok\" um die Infos zu bearbeiten.\n\"Cancel\" um ins Menü zurückzukehren."))
	        	{
	        		close(); 
	        	}
    		}
    	}
    	else
    	{
        	if(!showMessageBox("Warunung", "Es sind nicht alle notwendigen Informationen gegeben",
        			"\"Ok\" um die Infos zu vervollständigen.\n\"Cancel\" um ins Menü zurückzukehren."))
        	{
        		close(); 
        	}
    	}
	}
    
    /**
     * Opens a alert box 
     * @param title Title of the Box
     * @param content Content of the Box
     */
    private void showMessageBox(String title, String content)
	{
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);

    	alert.showAndWait();
	}
    
    /**
     * Opens a alert box
     * @param title Title of the box
     * @param header Header of the box
     * @param body Body of the box
     * @return Whether "Ok" Button is pressed
     */
    private boolean showMessageBox(String title, String header, String body)
	{
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(body);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK)
		{
			return true;
		}
    	else
    	{
    		return false;
    	}
	}
    
    /**
     * Checks whether the given String is null or empty
     * @param s Given string
     * @return Whether the string is null or empty
     */
    private boolean isNullOrEmpty(String s)
    {
    	return s == null||s.trim().isEmpty();
    }

}