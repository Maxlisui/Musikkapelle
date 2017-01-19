package Controller;

import java.util.ArrayList;
import DBClasses.Hauptwohnsitz;
import Utils.GloballyData;
import Utils.ListUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ChooseHauptwohnsitzController {

	private final String centerStyle = "-fx-alignment: CENTER;";
	
    @FXML
    private TableView<Hauptwohnsitz> tView_Hauptwohnsitze;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Id;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Strasse;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Hausnummer;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_PLZ;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_Ort;
    @FXML
    private TableColumn<Hauptwohnsitz, String> column_land;
    
    /**
     * Gets called when the UI is generated. Configures the UI
     */
    @FXML
    private void initialize()
    {
    	GloballyData.getInstance().setLastSelectedHauptwohnsitz(null);
    	column_Id.setStyle(centerStyle);
    	column_Strasse.setStyle(centerStyle);
    	column_Hausnummer.setStyle(centerStyle);
    	column_PLZ.setStyle(centerStyle);
    	column_Ort.setStyle(centerStyle);
    	column_land.setStyle(centerStyle);
    	updateTable();
    	
    	//Doubleclick Event for the TableView
    	tView_Hauptwohnsitze.setRowFactory(tv ->
			{
				TableRow<Hauptwohnsitz> row = new TableRow<>();
				row.setOnMouseClicked(event -> 
				{
					if(event.getClickCount() == 2 && !row.isEmpty())
					{
						GloballyData.getInstance().setLastSelectedHauptwohnsitz(row.getItem());
						close();
					}
					else
					{
						GloballyData.getInstance().setLastSelectedHauptwohnsitz(null);
					}
				});
				return row;
			});
    }
    
    /**
     * Updates the TableView with all available Hauptwohnsitze 
     */
    private void updateTable()
    {
    	ArrayList<Hauptwohnsitz> availableHauptwohnsitze = ListUtils.getInstance().getAvailableHauptwohnsitze();
    	tView_Hauptwohnsitze.setItems(FXCollections.observableArrayList(availableHauptwohnsitze));
    	
    	column_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    	column_Strasse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStrasse()));
    	column_Hausnummer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHausnummer()));
    	column_PLZ.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPLZ())));
    	column_Ort.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrt()));
    	column_land.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLand()));
    }
    
    /**
     * Closes the stage
     */
    private void close()
    {
        Stage thisStage = (Stage) tView_Hauptwohnsitze.getScene().getWindow();
        thisStage.close();
    }
}
