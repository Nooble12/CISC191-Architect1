package edu.sdccd.cisc191.template.items;

public class SuperCheeseItem extends CheeseItem
{

    public SuperCheeseItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * @return the custom money multiplier of the cheese item. Some food items may be able to increase the factor by more than 1.
     */
    @Override
    public int getMoneyMultiplier()
    {
        int MONEY_MULTIPLIER = 4;
        return MONEY_MULTIPLIER;
    }

    /**
     * @return an integer of the percent to win.
     */
    public int getPercentToWin()
    {
        int PERCENT_TO_WIN = 95;
        return PERCENT_TO_WIN;
    }
}
