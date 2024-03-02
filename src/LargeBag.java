public class LargeBag extends Bag{
    protected String name;
    protected int value;
    protected int cap;
    protected Item[] inventory;
    public final int defultCap= 5;
    public LargeBag(String name, int value, int cap){
        super(name, value, cap);
        if(cap<defultCap)
        {
            this.inventory=new Item[defultCap];
        }
    }


    public Item[] getInventory() {
        return this.inventory;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }
    @Override
    public void useItem(Player player){

    }

    public int sumValuesOfLargeBagItems(){
        int sum=0;
        for (int i=0; i<this.inventory.length; i++){
            sum+= inventory[i].getValue();
        }
        return sum;
    }
    @Override
    public boolean equals(Object otherLargeBag1) {
        if (!(otherLargeBag1 instanceof LargeBag)) {
            return false;
        }
        LargeBag otherLargeBag2 = (LargeBag) otherLargeBag1;
        return (this.getValue()==otherLargeBag2.getValue()&&
                this.sumValuesOfLargeBagItems() == otherLargeBag2.sumValuesOfLargeBagItems() &&
                this.getInventory().length == otherLargeBag2.getInventory().length);
    }

    @Override
    public int hashCode() {
        int result = 31; // Different initial prime number
        int multiplier = 53; // Different multiplier

        result = multiplier * result + name.hashCode();
        result = multiplier * result + value;
        result = multiplier * result + cap;

        for (Item item : inventory) {
            if (item != null) {
                result = multiplier * result + item.hashCode();
            }
        }

        return result;
    }

}
