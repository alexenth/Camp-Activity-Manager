package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by layla on 2/6/2017.
 */
public class ActivityEditorController extends Main implements Initializable {
    @FXML
    private TableColumn<String, String> period1Column;

    @FXML
    private TableColumn<String, String> period4Column;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<?> rosterTable;

    @FXML
    private TableColumn<String, String> period5Column;

    @FXML
    private TableColumn<String, String> period2Column;

    @FXML
    private TableColumn<String, String> period3Column;

    @FXML
    private Menu activityMenu;

    @FXML
    private TableColumn<String, String> period6Column;
    
    private ObservableList<String> data;
    private TreeMap<String,MenuItem> menuItems = new TreeMap<>();
    public static ArrayList<Activity> readActivitiesFile(String filename){

        try{
            Scanner sc = new Scanner(new File(filename));
            System.out.println("Scanner made");
            ArrayList<Activity> ret = new ArrayList<>();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] fields = line.split(",");
                ArrayList<Boolean> bools = new ArrayList<>();
                bools.add(Boolean.parseBoolean(fields[2]));
                bools.add(Boolean.parseBoolean(fields[3]));
                bools.add(Boolean.parseBoolean(fields[4]));
                bools.add(Boolean.parseBoolean(fields[5]));
                bools.add(Boolean.parseBoolean(fields[6]));
                bools.add(Boolean.parseBoolean(fields[7]));
                Activity activity = new Activity(fields[0],Integer.parseInt(fields[1]),bools,Boolean.parseBoolean(fields[8]));
                System.out.println("Activity: "+activity.getName()+" created");
                ret.add(activity);
            }
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("check");
            return null;
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("loading file");
        ArrayList<Activity> aList = readActivitiesFile("TestDataActivity.txt");
        System.out.println("activity editor loaded");
        
        for(Activity a: aList){
            MenuItem item = new MenuItem(a.getName());
            menuItems.put(a.getName(),item);
            activityMenu.getItems().addAll(item);


        }

    }

    public void setActivity(Activity activity){
        ArrayList<String> strs = new ArrayList<>();
        for(Period period: ActivityViewController.periods){
            for(Activity activ: period.activities){
                if(activ.getName().equals(activity.getName())){
                    // If the activity plugged in is in the period's array list, populate the tablecolumn with the roster for that activity
                    for(Camper camper: activ.getRoster()){
                        strs.add(camper.getFirst()+ " " + camper.getLast());
                    }
                    data =FXCollections.observableArrayList(strs);
                    switch (period.getLabel()){
                        case 1:
                            period1Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;

                        case 2:
                            period2Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;
                        case 3:
                            period3Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;
                        case 4:
                            period4Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;
                        case 5:
                            period5Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;
                        case 6:
                            period6Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstLast"));
                            break;
                    }

                    
                }
            }
        }


    }

}
