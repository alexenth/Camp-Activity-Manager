package sample;

import java.util.ArrayList;

/**
 * Created by layla on 11/15/2016.
 */
public class Camper implements Comparable<Camper>{
    private ArrayList<String> activitiesRequested;
    private ArrayList<String> activitiesAssigned;
    private String act1 = "";
    private String act2;
    private String act3;
    private String act4;
    private String first;
    private String last;
    private String obNumber;
    private String cabin;
    private String counselor;

    private boolean isNew;
    private int age;
    private String birthday;
    public Camper(String first, String last, String obNumber,String cabin,String counselor,boolean isNew){
        System.out.println("Trying to create camper: "+first+last+obNumber+cabin+counselor+isNew);
        this.first = first;
        this.last = last;
        this.obNumber = obNumber;
        this.cabin = cabin;
        this.counselor = counselor;
        this.isNew = isNew;
        boolean inMaster = false;

        //Checks to see if the camper is already in the master list, if theyre not it adds them.
        for(Camper camper: CabinViewController.masterCamperMap.values()){
            if (this.equals(camper)){
                inMaster = true;
                break;
            }
        }

        CabinViewController.cabins.get(cabin).getRoster().put(obNumber,this);

        if(!inMaster){
            CabinViewController.masterCamperMap.put(obNumber,this);
        }



        System.out.println("Success");
    }

    public void tostring(){
        System.out.println(this.obNumber+this.first+this.last);
    }

    public String getAct1() {
        return act1;
    }

    public void setAct1(String act1) {
        this.act1 = act1;
    }

    public String getAct2() {
        return act2;
    }

    public void setAct2(String act2) {
        this.act2 = act2;
    }

    public String getAct3() {
        return act3;
    }

    public void setAct3(String act3) {
        this.act3 = act3;
    }

    public String getAct4() {
        return act4;
    }

    public void setAct4(String act4) {
        this.act4 = act4;
    }

    public ArrayList<String> getActivitiesRequested() {
        return activitiesRequested;
    }

    public void setActivitiesRequested(ArrayList<String> activitiesRequested) {
        this.activitiesRequested = activitiesRequested;
    }

    public ArrayList<String> getActivitiesAssigned() {
        return activitiesAssigned;
    }

    public void setActivitiesAssigned(ArrayList<String> activitiesAssigned) {
        this.activitiesAssigned = activitiesAssigned;
    }

    public String getFirst() {
        return first;
    }

    public String getFirstLast(){
        return first+" "+last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getObNumber() {
        return obNumber;
    }

    public void setObNumber(String obNumber) {
        this.obNumber = obNumber;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public int compareTo(Camper camper) {
        System.out.println(this.getObNumber().compareTo(camper.getObNumber()));

        return this.getObNumber().compareTo(camper.getObNumber());
    }
}
