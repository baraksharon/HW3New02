public class Key extends Item{
    protected String name;
    protected int value;
    protected boolean keyStatus;
    public  Key(String name,int value){
        super(name,value);
        keyStatus= false;
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

    public Boolean getkeyStatus(){
        return this.keyStatus;
    }
    @Override
    public void useItem(Player player){
        Room currentRoom= player.getCurrentRoom();
        if(currentRoom.getRoomKey() != null){
            System.out.println(player.getCurrentRoom().getRoomName()+" was already unlocked.");
        } else {
            boolean isKeyNearby= isNearBy(this, player);
            if(isKeyNearby){
                for (int i=0; i<currentRoom.getListItems().length; i++){ // Checking if the item is in the Room
                    if(currentRoom.getListItems()[i].equals(this)){
                        player.getCurrentRoom().setRoomKey(this);
                        player.getCurrentRoom().setKeyRoomStatus(true);
                        currentRoom.getListItems()[i]= null;
                    }
                }
                for(int j=0; j<player.getInventory().length; j++){  // Checking if the item is in the player's bag
                    if(player.getInventory()[j].equals(this)) {
                        player.getCurrentRoom().setRoomKey(this);
                        player.getCurrentRoom().setKeyRoomStatus(true);
                        player.getInventory()[j]= null;
                    }
                }
                System.out.println(player.getName()+" used "+this.name+" in "+player.getCurrentRoom()+".");
                if(player.getCurrentRoom().getPuzzleStatus())
                {
                    player.getCurrentRoom().setPuzzleStatus(false);
                }
            }
            else {
                System.out.println(this.name+" is not near "+player.getName()+".");
            }
        }

    }

    @Override
    public boolean equals(Object otherKey1) {
        if (!(otherKey1 instanceof Key)) {
            return false;
        }
        Key otherKey2 = (Key) otherKey1;
        return (this.getValue() == otherKey2.getValue());
    }

    @Override
    public int hashCode() {
        int result = 41;
        result = 31 * result + name.hashCode();
        result = 31 * result + value;
        result = 31 * result + (keyStatus ? 1 : 0);
        return result;
    }

}