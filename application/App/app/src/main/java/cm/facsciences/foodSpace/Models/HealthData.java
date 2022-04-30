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
}
