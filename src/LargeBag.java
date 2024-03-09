public class LargeBag extends Bag{
    protected String name;
    protected int value;
    protected int cap;
    protected Item[] inventory;
    public final int defultCap= 5;
    public LargeBag(String name, int value, int cap){
        super(name, value, cap);
        if(cap < defultCap)
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
    public int countItemsInBag(Item[]items){
        return  super.countItemsInBag(items);
    }
    @Override
    public  int countFreePlaces(Item[]items){
        return super.countFreePlaces(items);
    }
    public  int getIndexForCurrentBag(Item[] it){
        int index= 0;
        int place= -1;
        while(index< it.length && place == -1){
            if(it[index] == null){
                place = index;
            } else {
                index++;
            }
        }
        return place;
    }

    @Override
    public void useItem(Player player) {
        if(isNearBy(this,player)){ //The bag is nearby
            int spaceInNewFuturePlayerBag= this.countFreePlaces(this.getInventory());
            int itemsInPlayerBag= player.getPlayerBag().countItemsInBag(player.getInventory());
            boolean isTherePlace= (spaceInNewFuturePlayerBag >= itemsInPlayerBag); //(&&) varify if largeBag cant be in largeBag
            if (isTherePlace){
                int i=0;
                int j=0;
                while(i<player.getInventory().length){
                    if(player.getInventory()[i]!=null && this.getInventory()[j] == null){
                        this.getInventory()[j]=player.getInventory()[i];
                        player.getInventory()[i]=null;
                        i++;
                        j++;
                    } else {
                        if (player.getInventory()[i] == null) {
                            i++;
                        }
                        if (this.getInventory()[j] != null) {
                            j++;
                        }
                    }
                }
                if(player.getPlayerBag() instanceof Bag ){//checks if the current is bag in order to check if i can put him inside the new bag
                    int index= getIndexForCurrentBag(this.getInventory());
                    if(index != -1){
                        this.getInventory()[index]=player.getPlayerBag();
                    } else {
                        player.destroyBag(player.getPlayerBag());
                    }
                } else {
                    player.destroyBag(player.getPlayerBag());
                }
                player.setPlayerBag(this);
                System.out.println(player.getName()+" is now carrying "+this.getName()+".");
            } else {
                System.out.println(this.getName()+" is too small.");
            }
        }
        else {
            System.out.println(this.getName()+" is not near "+player.getName()+".");
        }
    }

    public int sumValuesOfLargeBagItems(){
        int sum=0;
        for (int i=0; i<this.inventory.length; i++){
            if(inventory[i] != null){
                sum+= inventory[i].getValue();
            }
        }
        return sum;
    }
    @Override
    public boolean equals(Object otherLargeBag1){
        if (!(otherLargeBag1 instanceof LargeBag)){
            return false;
        }
        LargeBag otherLargeBag2 = (LargeBag) otherLargeBag1;
        return (this.getValue() == otherLargeBag2.getValue() &&
                this.sumValuesOfLargeBagItems() == otherLargeBag2.sumValuesOfLargeBagItems() &&
                this.getInventory().length == otherLargeBag2.getInventory().length);
    }

    @Override
    public int hashCode(){
        int result = 31; // Different initial prime number
        int multiplier = 53; // Different multiplier
        result = multiplier * result + value;
        result = multiplier * result + cap;
        for (Item item : inventory){
            if (item != null) {
                result = multiplier * result + item.hashCode();
            }
        }
        return result;
    }

}