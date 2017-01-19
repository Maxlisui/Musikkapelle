import DBConnection.DBManager;
import Utils.GloballyData;
import Utils.ListUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        try {
    		GloballyData.getInstance().setMusikanten(DBManager.getInstance().selectAllMusikanten());	
    		GloballyData.getInstance().setHauptwohnsitze(DBManager.getInstance().selectAllHauptwohnsitze());
    		GloballyData.getInstance().setInstrumente(DBManager.getInstance().selectAllIntrumente());
    		GloballyData.getInstance().setMusikantInstrumente(DBManager.getInstance().selectAllMusikantInstrument());
    		
            launch(args);
            
            ListUtils.getInstance().updateDeletedMusikantenIntrumente();
    		
            DBManager.getInstance().refreshDB(GloballyData.getInstance().getMusikanten(), GloballyData.getInstance().getInstrumente(), 
            		GloballyData.getInstance().getHauptwohnsitze(), GloballyData.getInstance().getMusikantInstrumente());
            
            DBManager.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception
	{
    	BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("View/MainWindowView.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Musikkapelle");
    	primaryStage.getIcons().add(new Image("http://web2153.ws.mynet.at/uploads/pics/logoleft1_01.jpg"));
    	primaryStage.show();
    }
}