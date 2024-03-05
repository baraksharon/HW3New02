public class Main {
    public static void main(String[] args) {
        System.out.println("Test 1 starts");
        test1();
        System.out.println("Test 1 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 2 starts");
        test2();
        System.out.println("Test 2 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 3 starts");
        test3();
        System.out.println("Test 3 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 4 starts");
        test4();
        System.out.println("Test 4 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 5 starts");
        test5();
        System.out.println("Test 5 done");
        System.out.println("--------------------------------------------");


    }

    /**
     * Run first test of hw2
     */
    public static void test1(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room room5 = new Room("Room 5");
        Room room6 = new Room("Room 6");

        Room tempRoom = new Room("Room 5");

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);
        gameManager.addRoom(room3);
        gameManager.addRoom(room4);
        gameManager.addRoom(room5);
        gameManager.addRoom(room6);
        gameManager.removeRoom(tempRoom);
        gameManager.addRoom(room6);



        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room3, Direction.NORTH);
        gameManager.connectRooms(room3, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room3, Direction.SOUTH);

        gameManager.removeRoom(room5);
        gameManager.removeRoom(room2);
        gameManager.connectRooms(room1, room4, Direction.NORTH);
    }
    /**
     * Run second test of hw2
     */
    public static void test2(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");

        Item item1 = new Bag("Item 1", 5, 2);  // String name, int value, int size
        Item item2 = new Key("Item 2", 3);
        Item item3 = new Relic("Item 3", 9);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.addItem(room1, item1);
        gameManager.addItem(room1, item2);
        gameManager.addItem(room1, item3);

    }
    /**
     * Run third test of hw2
     */
    public static void test3(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        gameManager.addRoom(room1);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);

        gameManager.startPlayer(room1);

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);
        Item item3 = new Relic("Item 3", 7);

        Item tempItem = new Relic("Item 2", 6);

        gameManager.addItem(room1, item1);
        gameManager.addItem(room1, item2);

        gameManager.dropItem(item1);
        gameManager.pickUpItem(item1);
        gameManager.pickUpItem(item1);
        gameManager.pickUpItem(tempItem);

        tempItem = new Relic("I don't know what is the name of the relic, please find it for me", 2);

        gameManager.dropItem(tempItem);
        gameManager.pickUpItem(item3);
        gameManager.pickUpItem(item1);

        gameManager.addItem(room1, item3);
        gameManager.pickUpItem(item3);
    }
    /**
     * Run fourth test of hw2
     */
    public static void test4(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);
        Item item3 = new Relic("Item 3", 7);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.addItem(room2, item1);
        gameManager.addItem(room2, item2);
        gameManager.addItem(room1, item3);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.connectRooms(room1, room2, Direction.NORTH);

        gameManager.movePlayer(Direction.SOUTH);
        gameManager.pickUpItem(item3);
        gameManager.activatePuzzle(room1);

        gameManager.movePlayer(Direction.NORTH);
        gameManager.solvePuzzle();
        gameManager.movePlayer(Direction.NORTH);

        gameManager.dropItem(item3);
        gameManager.solvePuzzle();
        gameManager.movePlayer(Direction.SOUTH);
        gameManager.dropItem(item3);
        gameManager.disassembleItem(item1);
        gameManager.disassembleItem(item3);
        gameManager.pickUpItem(item3);
    }

    public static void test5() {
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);

        gameManager.addItem(room1, item2);
        gameManager.addItem(room1, item1);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.pickUpItem(item1);

        gameManager.removePlayer(player);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.disassembleItem(item1);
        gameManager.removePlayer(player);

        gameManager.removeRoom(room1);
        gameManager.addRoom(room1);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.disassembleItem(item2);
    }
}
