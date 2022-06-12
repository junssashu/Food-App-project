package cm.facsciences.foodSpace.Models;

import java.util.List;

public class HealthData extends GeneralObject {

    private List<String> antecedents;
    private List<Disease> Diseases;

    public List<String> getAntecedents() {
        return antecedents;
    }

    public List<Disease> getDiseases() {
        return Diseases;
    }

    public void setAntecedents(List<String> antecedents) {
        this.antecedents = antecedents;
    }

    public void setDiseases(List<Disease> diseases) {
        Diseases = diseases;
    }

    public boolean delAntecedents (){
        //code
        return false;
    }

    public boolean delDisease (){
        //code
        return false;
    }

    public HealthData(List<String> antecedents, List<Disease> diseases, String name) {
        this.antecedents = antecedents;
        this.Diseases = diseases;
        this.setName(name);
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }
}
