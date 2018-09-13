package sample;

/*import javafx.cabin.getCollections.FXCollections;
import javafx.cabin.getCollections.ObservableList;*/
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CabinViewController extends Main implements Initializable {
    public static ArrayList<String> keys = new ArrayList<>();
    public static TreeMap<String,Cabin> cabins = new TreeMap<>();
    public static TreeMap<String,TreeMap<String,Camper>> camperLists = new TreeMap<>();
    public static Cabin Gopher,Badger,Beaver,Ranger,Kingfisher,Eagles,Chippewa,Bunkhouse,Pioneer,OlyClub,Mohawk,Apache,Senior1,Senior2,LT1;
    public static TreeMap<String,Camper> masterCamperMap = new TreeMap<>();
    public static String masterDataFileName;
    public static File masterDataFile;
    public static Stage assignmentStage;
    public static Stage activityViewStage;
    public static Stage activityEditorStage;

    public static Button button = new Button();
    @FXML
    private Button assignmentButton;

    @FXML
    private Button activityViewButton;

    @FXML
    private BorderPane borderPaneCabinView;

    @FXML
    private Button manageActivitiesButton;

    public static TabPane cabinsTabPane = new TabPane();
    public void readFileNoActivities(String filename){
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");
                boolean isNew = false;
                if(fields[5].equals("Yes")) {
                    isNew = true;
                }
                //Creating new camper object based off of the next line of the CSV
                Camper camper = new Camper(fields[0],fields[1],fields[2],fields[3],fields[4],isNew);
                
                //Adding cabin camper to the correct maps (Master Map, Cabin Specific maps, Activity Rosters if the activity fields exist)
                cabins.get(fields[3]).roster.put(fields[2],camper);
            }
            sc.close();
        }
         catch(Exception e){
                System.out.println("Could not read file");
            }
        }

    public void initialize(URL url, ResourceBundle rb){
        System.out.println("yoyoyo");
        Gopher = new Cabin("Gopher","1");
        Badger = new Cabin("Badger","2");
        Beaver = new Cabin("Beaver","3");
        Ranger = new Cabin("Ranger","4");
        Kingfisher = new Cabin("Kingfisher","5");
        Eagles = new Cabin("Eagles","6");
        Chippewa = new Cabin("Chippewa","7");
        Bunkhouse = new Cabin("Bunkhouse","8");
        Pioneer = new Cabin("Pioneer","9");
        OlyClub = new Cabin("OlyClub","10");
        Mohawk = new Cabin("Mohawk","11");
        Apache = new Cabin("Apache","12");
        Senior1 = new Cabin("Senior1","S1");
        Senior2 = new Cabin("Senior2","S2");
        LT1 = new Cabin("LT1","LT");

        activityViewButton.setOnAction(e->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityView.fxml"));
                Parent activityViewScene = loader.load();
                activityViewStage = new Stage();
                activityViewStage.setTitle("Roster View");
                activityViewStage.setScene(new Scene(activityViewScene));
                activityViewStage.show();
            }catch(Exception e3){
                e3.printStackTrace();
            }
        });
        manageActivitiesButton.setOnAction(event -> {
            try{
                System.out.println("Loading activity manager...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityEditor.fxml"));
                Parent activityEditorScene = loader.load();
                activityEditorStage = new Stage();
                activityEditorStage.setTitle("Activity Editor");
                activityEditorStage.setScene(new Scene(activityEditorScene,1500,1000));
                activityEditorStage.show();

            }catch(Exception e){
                e.printStackTrace();
            }
        });
        assignmentButton.setOnAction(ev->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("QuickAssignment.fxml"));
                Parent assignmentScene = loader.load();
                assignmentStage = new Stage();
                assignmentStage.setTitle("Activity Assignment");
                assignmentStage.setScene(new Scene(assignmentScene));
                assignmentStage.show();
            }catch(Exception e){
                System.out.println("Oopy scoopy!");
            }
        });

        borderPaneCabinView.setCenter(cabinsTabPane);
        try{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            masterDataFile = fileChooser.showOpenDialog(new Stage());

        }catch(Exception e){
            
        }

        readFileNoActivities(masterDataFile.getName());

        setTables();

        ActivityViewController.readActivitiesFile("TestDataActivity.txt");
        //ActivityViewController.updateTables();


    }
    public static void setTables(){
        for(Cabin cabin: cabins.values()) {
            //sets each cabins columns to the correct values for each camper field
            cabin.getColumnAct1().setCellValueFactory(new PropertyValueFactory<Camper, String>("act1"));
            cabin.getColumnAct2().setCellValueFactory(new PropertyValueFactory<Camper, String>("act2"));
            cabin.getColumnAct3().setCellValueFactory(new PropertyValueFactory<Camper, String>("act3"));
            cabin.getColumnAct4().setCellValueFactory(new PropertyValueFactory<Camper, String>("act4"));
            cabin.getColumnLast().setCellValueFactory(new PropertyValueFactory<Camper, String>("last"));
            cabin.getColumnFirst().setCellValueFactory(new PropertyValueFactory<Camper, String>("first"));
            cabin.getColumnObNumber().setCellValueFactory(new PropertyValueFactory<Camper, String>("obNumber"));
            cabin.getTableCabin().getColumns().addAll(cabin.getColumnFirst(),cabin.getColumnLast(),cabin.getColumnObNumber(),cabin.getColumnAct1(),cabin.getColumnAct2(),cabin.getColumnAct3(),cabin.getColumnAct4());
            //ObservableList<Camper> campers2;
            cabin.data = FXCollections.observableArrayList(cabin.roster.values());
            cabin.getTableCabin().setItems(cabin.data);
        }
    }
    public static void updateTables(){
        for(Cabin cabin: cabins.values()){
            cabin.data = FXCollections.observableArrayList(cabin.roster.values());
            cabin.getTableCabin().setItems(cabin.data);

        }
    }
}
