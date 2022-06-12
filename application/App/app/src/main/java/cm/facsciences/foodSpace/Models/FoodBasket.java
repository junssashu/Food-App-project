package cm.facsciences.foodSpace.Models;

public class FoodBasket extends GeneralObject {

    private Foods mFoods;
    public FoodBasket(Foods foods, String name) {
        super(name);
        mFoods = foods;
    }



    public Foods getFoods() {
        return mFoods;
    }

    public void setFoods(Foods foods) {
        mFoods = foods;
    }
}
