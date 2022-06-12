package cm.facsciences.foodSpace.Models;

public class Symptoms extends GeneralObject{

    private String description;

    public Symptoms(String description, String name) {
        this.description = description;
        this.setName(name);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
