package cm.facsciences.foodSpace.Models;

public class Components extends GeneralObject{

    private float quantity;

    public Components(float quantity, String name) {
        this.quantity = quantity;
        this.setName(name);
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
