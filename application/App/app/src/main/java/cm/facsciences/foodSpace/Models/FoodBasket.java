package cm.facsciences.foodSpace.Models;

public class FoodBasket extends GeneralObject {

    private Foods mFoods;

    public FoodBasket(Foods mFoods, String name) {
        this.mFoods = mFoods;
        this.setName(name);
    }

    public Foods getFoods() {
        return mFoods;
    }

    public void setFoods(Foods foods) {
        mFoods = foods;
    }
}
