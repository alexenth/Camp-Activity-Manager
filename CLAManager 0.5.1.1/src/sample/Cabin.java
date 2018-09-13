package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.TreeMap;

/**
 * Created by layla on 11/15/2016.
 */
public class Cabin {
    private String name;
    private String counselor;
    private String number;
    public TreeMap<String,Camper> roster = new TreeMap<>();
    private Tab tabCabin;
    private TableView<Camper> tableCabin = new TableView();
    private TableColumn<Camper,String> columnFirst = new TableColumn<>("First");
    private TableColumn<Camper,String> columnLast = new TableColumn<>("Last");
    private TableColumn<Camper,String> columnAct1 = new TableColumn<>("Activity 1");
    private TableColumn<Camper,String> columnAct2 = new TableColumn<>("Activity 2");
    private TableColumn<Camper,String> columnAct3 = new TableColumn<>("Activity 3");
    private TableColumn<Camper,String> columnAct4 = new TableColumn<>("Activity 4");
    private TableColumn<Camper,String> columnObNumber = new TableColumn<>("OB Number");

    public ObservableList<Camper> data;
    public Cabin(String name,String number,String counselor){
        this.name = name;
        this.number = number;
        this.counselor = counselor;
    }
    public Cabin(String name,String number){
        //Sets the name and cabin Number
        this.name = name;
        this.number = number;

        //Creates tab with name as title
        this.tabCabin = new Tab(name);

        //Adds to useable lists in the controller
        CabinViewController.keys.add(name);
        CabinViewController.cabins.put(name,this);
        CabinViewController.camperLists.put(name,this.roster);

        //Sets values for the Columns, and the data for the Table
        /*data = FXCollections.observableArrayList(this.roster.values());
        this.tableCabin.setItems(data);*/
        //Setting the items for the tableCain is done in the initialization phase in CabinViewController
       /* this.columnAct1.setCellValueFactory(new PropertyValueFactory<Camper, String>("act1"));
        this.columnAct2.setCellValueFactory(new PropertyValueFactory<Camper, String>("act2"));
        this.columnAct3.setCellValueFactory(new PropertyValueFactory<Camper, String>("act3"));
        this.columnAct4.setCellValueFactory(new PropertyValueFactory<Camper, String>("act4"));
        this.columnLast.setCellValueFactory(new PropertyValueFactory<Camper, String>("last"));
        this.columnFirst.setCellValueFactory(new PropertyValueFactory<Camper, String>("first"));
        this.columnObNumber.setCellValueFactory(new PropertyValueFactory<Camper, String>("obNumber"));
        this.tableCabin.getColumns().addAll(columnFirst,columnLast,columnObNumber,columnAct1,columnAct2,columnAct3,columnAct4);*/

        //Puts TableView into the tab
        this.tabCabin.setContent(this.tableCabin);
        this.tabCabin.setClosable(false);

        //Adds tab for this cabin to the main TabPane in the controller
        CabinViewController.cabinsTabPane.getTabs().add(this.tabCabin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TreeMap<String, Camper> getRoster() {
        return roster;
    }

    public void setRoster(TreeMap<String, Camper> roster) {
        this.roster = roster;
    }

    public Tab getTabCabin() {
        return tabCabin;
    }

    public void setTabCabin(Tab tabCabin) {
        this.tabCabin = tabCabin;
    }

    public TableView<Camper> getTableCabin() {
        return tableCabin;
    }

    public void setTableCabin(TableView<Camper> tableCabin) {
        this.tableCabin = tableCabin;
    }

    public TableColumn<Camper, String> getColumnFirst() {
        return columnFirst;
    }

    public void setColumnFirst(TableColumn<Camper, String> columnFirst) {
        this.columnFirst = columnFirst;
    }

    public TableColumn<Camper, String> getColumnLast() {
        return columnLast;
    }

    public void setColumnLast(TableColumn<Camper, String> columnLast) {
        this.columnLast = columnLast;
    }

    public TableColumn<Camper, String> getColumnAct1() {
        return columnAct1;
    }

    public void setColumnAct1(TableColumn<Camper, String> columnAct1) {
        this.columnAct1 = columnAct1;
    }

    public TableColumn<Camper, String> getColumnAct2() {
        return columnAct2;
    }

    public void setColumnAct2(TableColumn<Camper, String> columnAct2) {
        this.columnAct2 = columnAct2;
    }

    public TableColumn<Camper, String> getColumnAct3() {
        return columnAct3;
    }

    public void setColumnAct3(TableColumn<Camper, String> columnAct3) {
        this.columnAct3 = columnAct3;
    }

    public TableColumn<Camper, String> getColumnAct4() {
        return columnAct4;
    }

    public void setColumnAct4(TableColumn<Camper, String> columnAct4) {
        this.columnAct4 = columnAct4;
    }

    public TableColumn<Camper, String> getColumnObNumber() {
        return columnObNumber;
    }

    public void setColumnObNumber(TableColumn<Camper, String> columnObNumber) {
        this.columnObNumber = columnObNumber;
    }
}
