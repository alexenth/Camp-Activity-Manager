package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by layla on 11/15/2016.
 */
public class Activity {
    private String name;
    private Button openRosterButton;
    private boolean period1 = false;
    private boolean period2 = false;
    private boolean period3 = false;
    private boolean period4 = false;
    private boolean period5 = false;
    private boolean period6 = false;
    private static ArrayList<Activity> masterActivityList = new ArrayList<>();
    public static Stage rosterViewStage;
    private boolean isTwoPeriods;
    private boolean isOverbooked;
    private ArrayList<Camper> roster = new ArrayList<>();
    private int current = roster.size();

    private int max;
    public Activity(String name,int max,ArrayList<Boolean> bools,boolean isTwoPeriods){
        this.name = name;
        this.max = max;
        this.period1 = bools.get(0);
        this.period2 = bools.get(1);
        this.period3 = bools.get(2);
        this.period4 = bools.get(3);
        this.period5 = bools.get(4);
        this.period6 = bools.get(5);
        openRosterButton = new Button("Edit Roster");
        openRosterButton.setOnAction(e->{
            openRosterView();
        });
        this.isTwoPeriods = isTwoPeriods;
        masterActivityList.add(this);
    }
    public void openRosterView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Roster.fxml"));
            Parent rosterViewScene = loader.load();
            rosterViewStage = new Stage();
            rosterViewStage.setTitle("Roster for "+this.name);
            rosterViewStage.setScene(new Scene(rosterViewScene));
            rosterViewStage.show();
        }catch(Exception e){

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getOpenRosterButton() {
        return openRosterButton;
    }

    public void setOpenRosterButton(Button openRosterButton) {
        this.openRosterButton = openRosterButton;
    }

    public boolean isPeriod1() {
        return period1;
    }

    public void setPeriod1(boolean period1) {
        this.period1 = period1;
    }

    public boolean isPeriod2() {
        return period2;
    }

    public void setPeriod2(boolean period2) {
        this.period2 = period2;
    }

    public boolean isPeriod3() {
        return period3;
    }

    public void setPeriod3(boolean period3) {
        this.period3 = period3;
    }

    public boolean isPeriod4() {
        return period4;
    }

    public void setPeriod4(boolean period4) {
        this.period4 = period4;
    }

    public boolean isPeriod5() {
        return period5;
    }

    public static ArrayList<Activity> getMasterActivityList() {
        return masterActivityList;
    }

    public static void setMasterActivityList(ArrayList<Activity> masterActivityList) {
        Activity.masterActivityList = masterActivityList;
    }

    public void setPeriod5(boolean period5) {
        this.period5 = period5;
    }

    public boolean isPeriod6() {
        return period6;
    }

    public void setPeriod6(boolean period6) {
        this.period6 = period6;
    }

    public static Stage getRosterViewStage() {
        return rosterViewStage;
    }

    public static void setRosterViewStage(Stage rosterViewStage) {
        Activity.rosterViewStage = rosterViewStage;
    }

    public boolean isTwoPeriods() {
        return isTwoPeriods;
    }

    public void setTwoPeriods(boolean twoPeriods) {
        isTwoPeriods = twoPeriods;
    }

    public boolean isOverbooked() {
        return isOverbooked;
    }

    public void setOverbooked(boolean overbooked) {
        isOverbooked = overbooked;
    }

    public ArrayList<Camper> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Camper> roster) {
        this.roster = roster;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return roster.size();
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
