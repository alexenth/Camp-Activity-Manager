package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static java.awt.SystemColor.window;

public class QuickAssignmentController implements Initializable{

    @FXML
    private Label firstLabel;

    @FXML
    private ComboBox<String> act1TextField;

    @FXML
    private ComboBox<String> act4TextField;

    @FXML
    private Label lastLabel;

    @FXML
    private ComboBox<String> act3TextField;

    @FXML
    private Button skipButton;

    @FXML
    private Button markNoneButton;

    @FXML
    private Label obLabel;

    @FXML
    private ComboBox<String> act2TextField;

    @FXML
    private Button onTripButton;

    @FXML
    private Label cabinLabel;



    public void setLabels(Camper camper){
        if(camper == null){
            CabinViewController.assignmentStage.close();
        }
        firstLabel.setText(camper.getFirst());
        lastLabel.setText(camper.getLast());
        obLabel.setText(camper.getObNumber());
        cabinLabel.setText(camper.getCabin());
    }
    public Camper findUnassigned(){
        for(Camper camper: CabinViewController.masterCamperMap.values()){
            if(camper.getAct1() == null || camper.getAct2() == null ||camper.getAct3() == null || camper.getAct4() == null){
                setLabels(camper);
                return camper;
            }
        }
        return null;
    }
    private boolean hasText(){
        System.out.println("TextField 1 current text: "+act1TextField.getEditor().getText());
        if(act1TextField.getEditor().getText() == null || act2TextField.getEditor().getText() == null || act3TextField.getEditor().getText() == null || act4TextField.getEditor().getText() == null
                || act1TextField.getEditor().getText() == "" || act2TextField.getEditor().getText() == "" || act3TextField.getEditor().getText() == "" || act4TextField.getEditor().getText() == ""
                || act1TextField.getEditor().getText().equals("") || act2TextField.getEditor().getText().equals("") || act3TextField.getEditor().getText().equals("") || act4TextField.getEditor().getText().equals("")){
            return false;
        }
        return true;
    }
    private void setActivities(Camper camper){
        System.out.println(act1TextField.getEditor().getText());
        if(hasText()) {
            camper.setAct1(act1TextField.getEditor().getText());
            camper.setAct2(act2TextField.getEditor().getText());
            camper.setAct3(act3TextField.getEditor().getText());
            camper.setAct4(act4TextField.getEditor().getText());
        }
    }
    private void clearEntries(){
        act1TextField.getEditor().setText("");
        act2TextField.getEditor().setText("");
        act3TextField.getEditor().setText("");
        act4TextField.getEditor().setText("");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(CabinViewController.masterCamperMap.values().size());
        findUnassigned();

        act4TextField.setOnKeyPressed(
                e -> {
                    switch(e.getCode()){
                        case ENTER:
                            if(hasText()){
                                Camper camper = findUnassigned();
                                if(camper != null) {
                                    setActivities(camper);
                                    System.out.println("Camper acts: " + camper.getAct1() + " " + camper.getAct2() + " " + camper.getAct3() + " " + camper.getAct4());
                                    setLabels(findUnassigned());
                                    clearEntries();
                                    CabinViewController.updateTables();
                                    act1TextField.requestFocus();
                                    CabinViewController.cabins.get(camper.getCabin()).data.remove(camper);
                                    CabinViewController.cabins.get(camper.getCabin()).data.add(camper);
                                    CabinViewController.cabins.get(camper.getCabin()).getTableCabin().sort();
                                    /*Collections.sort(CabinViewController.cabins.get(camper.getCabin()).data);*/
                                }


                            }
                            else{
                                System.out.println("Error: blank fields");
                            }

                    }
                }
        );

    }

}