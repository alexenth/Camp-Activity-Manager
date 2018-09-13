package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by layla on 11/19/2016.
 */
public class ActivityViewController implements Initializable {
    @FXML
    private BorderPane borderPaneActivityView;

    @FXML
    private TabPane activityViewPane;

    @FXML
    private Button assignmentButton;

    @FXML
    private Button cabinViewButton;




    public static ArrayList<Tab> tabs = new ArrayList<>();



    public static Period period1 = new Period(1);
    public static Period period2 = new Period(2);
    public static Period period3 = new Period(3);
    public static Period period4 = new Period(4);
    public static Period period5 = new Period(5);
    public static Period period6 = new Period(6);


    public static ArrayList<Period> periods = new ArrayList<>();
    static{
        periods.add(period1);
        periods.add(period2);
        periods.add(period3);
        periods.add(period4);
        periods.add(period5);
        periods.add(period6);
    }
    public static void setTables(){
        for(Activity activity: Activity.getMasterActivityList()){
            if(activity.isPeriod1()){
                periods.get(0).getActivities().add(activity);
                System.out.println(activity.getName()+" added to period1");


            }
            if(activity.isPeriod2()){
                periods.get(1).getActivities().add(activity);
                System.out.println(activity.getName()+" added to period2");
            }
            if(activity.isPeriod3()){
                periods.get(2).getActivities().add(activity);
                System.out.println(activity.getName()+" added to period3");

            }
            if(activity.isPeriod4()){
                periods.get(3).getActivities().add(activity);
                System.out.println(activity.getName()+" added to period4");

            }
            if(activity.isPeriod5()){
                periods.get(4).getActivities().add(activity);
            }
            if(activity.isPeriod6()){
                periods.get(5).getActivities().add(activity);
            }
        }
        for(Period period: periods){
            period.data = FXCollections.observableArrayList(period.activities);
            period.getPeriodTable().setItems(period.data);
        }
        System.out.println(periods.get(0).getActivities().size()+" "+
                periods.get(1).getActivities().size()+" "+
                periods.get(2).getActivities().size()+" "+
                periods.get(3).getActivities().size()+" "+
                periods.get(4).getActivities().size()+" "+
                periods.get(5).getActivities().size());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityViewPane.getTabs().addAll(tabs);
        System.out.println("tabs loaded");
       //readActivitiesFile("TestDataActivity.txt");
        System.out.println("file loaded");
        setTables();
        System.out.println("tables updated");
    }
    public static void updateTables(){
        for (Period period: periods){
            System.out.println(period.getActivities());
            period.data = FXCollections.observableArrayList(period.activities);
            period.getPeriodTable().setItems(period.data);
        }
    }
    public static void readActivitiesFile(String filename){

            try{
                Scanner sc = new Scanner(new File(filename));
                System.out.println("Scanner made");
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
                }
            }catch(Exception e){
                e.printStackTrace();
            }

    }
}
