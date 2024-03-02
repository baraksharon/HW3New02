import java.sql.SQLOutput;

public class Bag extends Item {
    protected String name;
    protected int value;
    protected int cap;
    protected Item[] inventory;

    public Bag(String name, int value, int cap) {
        super(name, value);
        this.inventory = new Item[cap];
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

    public int countItemsInBag(){
        int counter= 0;
        for(int i=0; i<this.getInventory().length; i++){
            if(this.getInventory()[i] != null){
                counter += 1;
            }
        }
        return counter;
    }

    public  int countFreePlaces(){
        return (this.getInventory().length- this.countItemsInBag());
    }


    public int countingBags(){
        int sum= 0;
        for(int i=0; i< this.getInventory().length; i++){
            if(this.getInventory()[i] instanceof Bag || this.getInventory()[i] instanceof LargeBag){
                sum+= 1;
            }
        }
        return sum;
    }

    @Override
    public void useItem(Player player) {
        if(isNearBy(this,player)){ //The bag is nearby
            int spaceInNewFuturePlayerBag= this.countFreePlaces();
            int itemsInPlayerBag= player.getPlayerBag().countItemsInBag();
            int sumOfBags= this.countingBags();
            boolean isTherePlace= (spaceInNewFuturePlayerBag >= itemsInPlayerBag); // - sumOfBags ?
            if (isTherePlace){ // && sumOfBags == 0
                int j=0;
                for(int i=0; i< player.getInventory().length; i++){
                    if (this.getInventory()[j] == null) {
                        if (player.getInventory()[i] != null){
                            if (player.getInventory()[i] instanceof Bag || player.getInventory()[i] instanceof LargeBag){
                                System.out.println("error");//checking with gal
                            }else{
                                this.getInventory()[j]= player.getInventory()[i];
                            }
                        }
                    } else{
                        j++;
                        i--;
                    }
                }
            } else {
                System.out.println(this.getName()+" is too small.");
            }
        }
        else {
            System.out.println(this.getName()+" is not near "+player.getName()+".");
        }

    }

    // Method to sum values of bag items
    public int sumValuesOfBagItems() {
        int sum = 0;
        for (int i = 0; i < this.inventory.length; i++) {
            sum += inventory[i].getValue();
        }
        return sum;
    }

    @Override
    public boolean equals(Object otherBag1) {
        if (!(otherBag1 instanceof Bag)) {
            return false;
        }
        Bag otherBag2 = (Bag) otherBag1;
        return (this.getValue()==otherBag2.getValue()&& this.sumValuesOfBagItems() == otherBag2.sumValuesOfBagItems() &&
                this.getInventory().length == otherBag2.getInventory().length);
    }

    @Override
    public int hashCode() {
        int result = 29; // Different initial prime number
        int multiplier = 47; // Different multiplier

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
