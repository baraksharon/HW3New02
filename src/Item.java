abstract class Item {
    protected String name;
    protected int value;
    public Item (String name, int value){
        this.name= name;
        this.value= value;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public boolean isNearBy(Item item, Player player){
        boolean isNearby= false;
        Room currentRoom= player.getCurrentRoom();
        for (int i=0; i<currentRoom.getListItems().length; i++){ // Chcking if the item is in the Room
            if(currentRoom.getListItems()[i] != null && currentRoom.getListItems()[i].equals(this)){
                isNearby= true;
            }
        }
        for(int j=0; j<player.getInventory().length; j++){  // Checking if the item is in the player's bag
            if(player.getInventory()[j] != null && player.getInventory()[j].equals(this)) {
                isNearby= true;
            }
        }
        return  isNearby;
    }

    public abstract void useItem(Player player);
}