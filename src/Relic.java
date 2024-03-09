public class Relic extends Item{
    protected String name;
    protected int value;
    // protected boolean isRelicUsed;

    public  Relic(String name,int value){
        super(name,value);
        //isRelicUsed=false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue(){
        return value;
    }
    @Override
    public boolean isNearBy(Item item, Player player) {
        return super.isNearBy(item, player);
    }

    @Override
    public void useItem(Player player){
        boolean isRelicNearby= isNearBy(this, player);
        if(isRelicNearby){
            Relic equalRelic= (Relic) findEqual(this, player);
            System.out.println(player.getName()+" is inspecting "+equalRelic.getName()+".");
        } else {
            System.out.println(this.getName()+" is not near "+player.getName()+".");
        }
    }

    @Override
    public boolean equals(Object otherRelic1) {
        if (!(otherRelic1 instanceof Relic)) {
            return false;
        }
        Relic otherRelic2 = (Relic) otherRelic1;
        return (this.getValue() == otherRelic2.getValue());
    }
    @Override
    public int hashCode() {
        int result = 19; // Different initial prime number
        int multiplier = 43; // Different multiplier
        result = multiplier * result + value;
        return result;
    }

}
