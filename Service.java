import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Service {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                r.type = roomType;
                r.pricePerNight = roomPricePerNight;
                return;
            }
        }
        rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
    }

    void setUser(int userId, int balance) {
        for (User u : users) {
            if (u.userId == userId)
                return;
        }
        users.add(new User(userId, balance));
    }

    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (!checkOut.after(checkIn)) {
            System.out.println("Invalid dates");
            return;
        }

        User user = null;
        Room room = null;

        for (User u : users)
            if (u.userId == userId)
                user = u;

        for (Room r : rooms)
            if (r.roomNumber == roomNumber)
                room = r;

        if (user == null || room == null) {
            System.out.println("User or Room not found");
            return;
        }

        for (Booking b : bookings) {
            if (b.room.roomNumber == roomNumber &&
                    checkIn.before(b.checkOut) &&
                    checkOut.after(b.checkIn)) {
                System.out.println("Room not available");
                return;
            }
        }

        long diff = checkOut.getTime() - checkIn.getTime();
        int nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int totalPrice = nights * room.pricePerNight;

        if (user.balance < totalPrice) {
            System.out.println("Insufficient balance");
            return;
        }

        user.balance -= totalPrice;
        bookings.add(new Booking(user, room, checkIn, checkOut, totalPrice));
    }

    void printAll() {
        System.out.println("---- ROOMS ----");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.println(r.roomNumber + " " + r.type + " " + r.pricePerNight);
        }

        System.out.println("---- BOOKINGS ----");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            Booking b = bookings.get(i);
            System.out.println(
                    "User " + b.user.userId +
                            " booked Room " + b.room.roomNumber +
                            " from " + b.checkIn +
                            " to " + b.checkOut +
                            " | Price: " + b.totalPrice);
        }
    }

    void printAllUsers() {
        for (int i = users.size() - 1; i >= 0; i--) {
            User u = users.get(i);
            System.out.println("User " + u.userId + " Balance: " + u.balance);
        }
    }
}
