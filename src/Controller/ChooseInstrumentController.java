package Controller;

import java.util.ArrayList;
import DBClasses.Instrument;
import Utils.GloballyData;
import Utils.ListUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ChooseInstrumentController {
	
	private final String centerStyle = "-fx-alignment: CENTER;";

    @FXML
    private TableView<Instrument> tView_Instrumente;
    @FXML
    private TableColumn<Instrument, String> column_Id;
    @FXML
    private TableColumn<Instrument, String> column_Name;
    @FXML
    private TableColumn<Instrument, String> column_Kategorie;
    @FXML
    private TableColumn<Instrument, String> column_Preis;
    
    /**
     * Gets called when the UI is generated. Configures the UI
     */
    @FXML
    private void initialize()
    {
    	GloballyData.getInstance().setLastSelectedInstrument(null);
    	column_Id.setStyle(centerStyle);
    	column_Name.setStyle(centerStyle);
    	column_Kategorie.setStyle(centerStyle);
    	column_Preis.setStyle(centerStyle);
    	updateTable();
    	
    	//Doubleclick Event for the TableView
    	tView_Instrumente.setRowFactory(tv ->
			{
				TableRow<Instrument> row = new TableRow<>();
				row.setOnMouseClicked(event -> 
				{
					if(event.getClickCount() == 2 && !row.isEmpty())
					{
						GloballyData.getInstance().setLastSelectedInstrument(row.getItem());
						close();
					}
					else
					{
						GloballyData.getInstance().setLastSelectedInstrument(null);
					}
				});
				return row;
			});
    }
    
    /**
     * Updates the TableView with all available Instrumente
     */
    private void updateTable()
    {
    	ArrayList<Instrument> availableInstrumente = ListUtils.getInstance().getAvailableInstrumente(
    			GloballyData.getInstance().getAlreadyUsedInstrumente());
    	tView_Instrumente.setItems(FXCollections.observableArrayList(availableInstrumente));
    	
    	column_Id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    	column_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    	column_Kategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKategorie()));
    	column_Preis.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPreis())));
    }
    
    /**
     * CLoses the stage
     */
    private void close()
    {
    	Stage thisStage = (Stage) tView_Instrumente.getScene().getWindow();
        thisStage.close();
    }

}