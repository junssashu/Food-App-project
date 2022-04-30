package cm.facsciences.foodSpace.Models;

import java.util.List;

public class Foods extends GeneralObject{

    private String Flavour;
    private List<Components> components;

    public Foods(String flavour, List<Components> components, String name) {
        this.Flavour = flavour;
        this.components = components;
        this.setName(name);
    }

    public String getFlavour() {
        return Flavour;
    }

    public void setFlavour(String flavour) {
        Flavour = flavour;
    }

    public List<Components> getComponents() {
        return components;
    }

    public List<Foods> setFoods(){
        //code
        return null;
    }

}
