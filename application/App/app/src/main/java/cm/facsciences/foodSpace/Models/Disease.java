package cm.facsciences.foodSpace.Models;

import java.util.List;

public class Disease extends GeneralObject{

    private List<Symptoms> Symptoms;

    public List<cm.facsciences.foodSpace.Models.Symptoms> getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(List<Symptoms> symptoms) {
        Symptoms = symptoms;
    }
}
