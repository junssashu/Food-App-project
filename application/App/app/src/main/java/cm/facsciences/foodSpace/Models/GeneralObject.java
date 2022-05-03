package cm.facsciences.foodSpace.Models;

public abstract class GeneralObject {

    private String name;


    public GeneralObject(String name) {
        this.name = name;
    }

    protected GeneralObject() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
