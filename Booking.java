import java.util.Date;

public class Booking {
    User user;
    Room room;
    Date checkIn;
    Date checkOut;
    int totalPrice;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int totalPrice) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }
}
