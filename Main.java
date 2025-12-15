import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        service.bookRoom(1, 2, new Date(126, 5, 30), new Date(126, 6, 7));
        service.bookRoom(1, 2, new Date(126, 6, 7), new Date(126, 5, 30));
        service.bookRoom(1, 1, new Date(126, 6, 7), new Date(126, 6, 8));
        service.bookRoom(2, 1, new Date(126, 6, 7), new Date(126, 6, 9));
        service.bookRoom(2, 3, new Date(126, 6, 7), new Date(126, 6, 8));

        service.setRoom(1, RoomType.SUITE, 10000);

        service.printAll();
        service.printAllUsers();
    }
}
