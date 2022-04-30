package cm.facsciences.foodSpace.Models;

import java.util.List;

public class Disease extends GeneralObject{

    private List<Symptoms> Symptoms;

    public Disease(List<Symptoms> symptoms, String name) {
        this.Symptoms = symptoms;
        this.setName(name);
    }

    public List<Symptoms> getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(List<Symptoms> symptoms) {
        Symptoms = symptoms;
    }
}
