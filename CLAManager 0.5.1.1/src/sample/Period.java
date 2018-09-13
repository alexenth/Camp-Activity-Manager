package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

/**
 * Created by layla on 11/19/2016.
 */
public class Period {
    private int label;
    public ArrayList<Activity> activities;
    private Tab periodTab;
    private TableView<Activity> periodTable = new TableView<>();
    private TableColumn<Activity,String> nameColumn = new TableColumn<>("Name");
    private TableColumn<Activity,String> currentColumn = new TableColumn<>("Currently Enrolled");
    private TableColumn<Activity,String> maxColumn = new TableColumn<>("Max permitted");
    private TableColumn viewRosterButtonColumn;
    public ObservableList<Activity> data;

    public Period(int label){
        activities = new ArrayList<>();
        this.label = label;
        periodTab = new Tab("Period "+label);
        this.periodTab.setClosable(false);

        this.periodTab.setContent(this.periodTable);
        ActivityViewController.tabs.add(this.periodTab);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Activity, String>("name"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<Activity, String>("max"));
        //viewRosterButtonColumn.setCellValueFactory(new PropertyValueFactory<Activity, Button>("openRosterButton"));
        viewRosterButtonColumn = new TableColumn( " " );
        viewRosterButtonColumn.setCellValueFactory( new PropertyValueFactory<>( "DUMMY" ) );

        Callback<TableColumn<Activity, String>, TableCell<Activity, String>> cellFactory =
                new Callback<TableColumn<Activity, String>, TableCell<Activity, String>>()
                {
                    @Override
                    public TableCell call(final TableColumn<Activity, String> param )
                    {
                        final TableCell<Activity, String> cell = new TableCell<Activity, String>()
                        {

                            final Button btn = new Button( "Edit Roster" );

                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                    {
                                        Activity activity = getTableView().getItems().get( getIndex() );
                                        activity.openRosterView();
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        viewRosterButtonColumn.setCellFactory( cellFactory );
        this.periodTable.getColumns().addAll(nameColumn,currentColumn,maxColumn,viewRosterButtonColumn);
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Tab getPeriodTab() {
        return periodTab;
    }

    public void setPeriodTab(Tab periodTab) {
        this.periodTab = periodTab;
    }

    public TableView<Activity> getPeriodTable() {
        return periodTable;
    }

    public void setPeriodTable(TableView<Activity> periodTable) {
        this.periodTable = periodTable;
    }

    public TableColumn<Activity, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<Activity, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public TableColumn<Activity, String> getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(TableColumn<Activity, String> currentColumn) {
        this.currentColumn = currentColumn;
    }

    public TableColumn<Activity, String> getMaxColumn() {
        return maxColumn;
    }

    public void setMaxColumn(TableColumn<Activity, String> maxColumn) {
        this.maxColumn = maxColumn;
    }

    public TableColumn<Activity, Button> getViewRosterButtonColumn() {
        return viewRosterButtonColumn;
    }

    public void setViewRosterButtonColumn(TableColumn<Activity, Button> viewRosterButtonColumn) {
        this.viewRosterButtonColumn = viewRosterButtonColumn;

    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public ObservableList<Activity> getData() {
        return data;
    }

    public void setData(ObservableList<Activity> data) {
        this.data = data;
    }
}
