package Controller;

import java.util.Optional;
import DBClasses.Instrument;
import Utils.GloballyData;
import Utils.ListUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditInstrumentController {
	
	private boolean isNewInstrument;
	private Instrument editableInstrument;

    @FXML
    private TextField tBox_Name;
    @FXML
    private TextField tBox_Kategorie;
    @FXML
    private TextField tBox_Preis;
    @FXML
    private Button btn_Save;
    @FXML
    private Button btn_Cancel;
    
    /**
     * Gets called when the UI is generated. Initializes the UI
     */
    @FXML
    private void initialize()
    {
    	if(GloballyData.getInstance().getInstrumentIdForEditing() == -1) isNewInstrument = true;
    	else
    	{
    		isNewInstrument = false;
    		editableInstrument = GloballyData.getInstance().getInstrumente().stream().filter(x ->
    				x.getId() == GloballyData.getInstance().getInstrumentIdForEditing()).findFirst().orElse(null);
    		
    		tBox_Name.setText(editableInstrument.getName());
    		tBox_Kategorie.setText(editableInstrument.getKategorie());
    		tBox_Preis.setText(String.valueOf(editableInstrument.getPreis()));
    	}
    }

    /**
     * Eventhandler for the "Cancel" button
     * @param event Mousevent handler
     */
    @FXML
    void btn_Cancel_clicked(MouseEvent event) 
    {
    	close();
    }

    /**
     * Eventhandler for the "Save" button
     * @param event Mouseevent handler
     */
    @FXML
    void btn_Save_clicked(MouseEvent event) 
    {
    	if(isNewInstrument) SaveNewInstrument();
    	else SaveUpdatedInstrument();
    }
    
    /**
     * Saves a updated Instrument
     */
    private void SaveUpdatedInstrument()
    {
    	String name = tBox_Name.getText();
    	String kategorie = tBox_Kategorie.getText();
    	
    	if(!isNullOrEmpty(name) && !isNullOrEmpty(kategorie))
    	{
    		try
    		{
    			double preis = Double.parseDouble(tBox_Preis.getText());
    			
    			GloballyData.getInstance().getInstrumente().remove(editableInstrument);
    			
    			editableInstrument.setName(name);
    			editableInstrument.setKategorie(kategorie);
    			editableInstrument.setPreis(preis);
    			editableInstrument.setUpdated(true);
    			
    			GloballyData.getInstance().getInstrumente().add(editableInstrument);
    			GloballyData.getInstance().getInstrumente().trimToSize();
    			
    			showMessageBox("Instrument gespeichert", "\"Ok\" um ins Hauptmenü zurückzukehren.");
        		close();
    		}
    		catch(Exception e)
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
     * Saves a new Instrument
     */
    private void SaveNewInstrument()
    {
    	String name = tBox_Name.getText();
    	String kategorie = tBox_Kategorie.getText();
    	
    	if(!isNullOrEmpty(name) && !isNullOrEmpty(kategorie))
    	{
    		try
    		{
    			double preis = Double.parseDouble(tBox_Preis.getText());
    			
    			int id = ListUtils.getInstance().getLastInstrumentId(GloballyData.getInstance().getInstrumente());
    			
    			GloballyData.getInstance().getInstrumente().add(
    					new Instrument(id + 1, name, kategorie, preis, true));
    			
    			showMessageBox("Instrument gespiechert.", "\"Ok\" um ins Hauptmenü zurückzukehren.");
    			close();
    		}
    		catch(Exception e)
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
     * Closes the stage
     */
    private void close()
    {
    	Stage thisStage = (Stage) btn_Cancel.getScene().getWindow();
        thisStage.close();
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
