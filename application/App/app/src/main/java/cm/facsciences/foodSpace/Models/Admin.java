
package cm.facsciences.foodSpace.Models;

import java.util.List;

public class Admin extends Consultant{


    private String grade;

    public Admin(String name, String age, String tel, String email, String login, String password, String grade, Integer sex) {
        super(email, password, age);
        this.grade = grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void determineDisease(){
        //code
    }

    public Disease setDisease( List<Symptoms> listSymptoms ){
        //code

        return null;
    }

    public Foods setFoods (List<Foods> listFoods){
        // code
        return null;
    }

    public Foods updateFoods (List<Foods> listFoods){
        // code
        return null;
    }

    public boolean deleteFoods (Foods food){
        // code
        return false ;
    }
}