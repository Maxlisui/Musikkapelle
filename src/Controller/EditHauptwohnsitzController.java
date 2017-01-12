package Controller;

import java.util.Optional;

import DBClasses.Hauptwohnsitz;
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

public class EditHauptwohnsitzController {

	private boolean isNewWohnsitz;
	private Hauptwohnsitz editableHauptwohnsitz;
	
    @FXML
    private TextField tBox_Strasse;

    @FXML
    private TextField tBox_Hausnummer;

    @FXML
    private TextField tBox_PLZ;

    @FXML
    private TextField tBox_Ort;

    @FXML
    private TextField tBox_Land;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_cancel;

    @FXML
    private void initialize()
    {
    	if(GloballyData.getInstance().getWohnsitzIdForEditing() == -1) isNewWohnsitz = true;
    	else
    	{
    		isNewWohnsitz = false;
    		editableHauptwohnsitz = GloballyData.getInstance().getHauptwohnsitze().stream().filter(x -> 
    				x.getId() == GloballyData.getInstance().getWohnsitzIdForEditing()).findFirst().orElse(null);
    		
    		tBox_Strasse.setText(editableHauptwohnsitz.getStrasse());
    		tBox_Hausnummer.setText(editableHauptwohnsitz.getHausnummer());
    		tBox_Ort.setText(editableHauptwohnsitz.getOrt());
    		tBox_Land.setText(editableHauptwohnsitz.getLand());
    		tBox_PLZ.setText(String.valueOf(editableHauptwohnsitz.getPLZ()));
    	}
    }
    
    @FXML
    void btn_cancel_clicked(MouseEvent event) 
    {
    	close();
    }

    @FXML
    void btn_save_clicked(MouseEvent event)
    {
    	if(isNewWohnsitz) saveNewWohnsitz();
    	else saveUpdatedWohnsitz();
    }
    
    private void saveUpdatedWohnsitz()
    {
    	String strasse = tBox_Strasse.getText();
    	String hausnummer = tBox_Hausnummer.getText();
    	String ort = tBox_Ort.getText();
    	String land = tBox_Land.getText();
    	int PLZ;
    	boolean success = true;
    	
    	if(!isNullOrEmpty(strasse) && !isNullOrEmpty(hausnummer) && !isNullOrEmpty(land) && !isNullOrEmpty(ort))
    	{
    		try
    		{
    			PLZ = Integer.parseInt(tBox_PLZ.getText());
    			
    			GloballyData.getInstance().getHauptwohnsitze().remove(editableHauptwohnsitz);
    			
    			editableHauptwohnsitz.setHausnummer(hausnummer);
    			editableHauptwohnsitz.setLand(land);
    			editableHauptwohnsitz.setOrt(ort);
    			editableHauptwohnsitz.setPLZ(PLZ);
    			editableHauptwohnsitz.setStrasse(strasse);
    			editableHauptwohnsitz.setUpdated(true);
    			
    			GloballyData.getInstance().getHauptwohnsitze().add(editableHauptwohnsitz);
    			GloballyData.getInstance().getHauptwohnsitze().trimToSize();
    			
        		showMessageBox("Wohnsitz gespeichert", "\"Ok\" um ins Hauptmenü zurückzukehren.");
        		close();
    		}
    		catch(Exception e)
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
    
    private void saveNewWohnsitz()
    {
    	String strasse = tBox_Strasse.getText();
    	String hausnummer = tBox_Hausnummer.getText();
    	String ort = tBox_Ort.getText();
    	String land = tBox_Land.getText();
    	int PLZ;
    	boolean success = true;
    	
    	if(!isNullOrEmpty(strasse) && !isNullOrEmpty(hausnummer) && !isNullOrEmpty(land) && !isNullOrEmpty(ort))
    	{
    		try
    		{
    			PLZ = Integer.parseInt(tBox_PLZ.getText());
    			
    			int id = ListUtils.getInstance().getLastHauptwohnsitzId(GloballyData.getInstance().getHauptwohnsitze()) + 1;
    			
    			GloballyData.getInstance().getHauptwohnsitze().add(
    					new Hauptwohnsitz(id, strasse, hausnummer, PLZ, ort, land, true));
    			
    			showMessageBox("Wohnsitz gespiechert.", "\"Ok\" um ins Hauptmenü zurückzukehren.");
    			close();
    		}
    		catch(Exception e)
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
    
    private boolean isNullOrEmpty(String s)
    {
    	return s == null||s.trim().isEmpty();
    }
    
    private void showMessageBox(String title, String content)
	{
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);

    	alert.showAndWait();
	}
    
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
    
    private void close()
    {
        Stage thisStage = (Stage) btn_cancel.getScene().getWindow();
        thisStage.close();
    }

}
