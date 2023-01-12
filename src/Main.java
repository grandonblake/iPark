public class Main {
    public static void main(String[] args) {

        User admin = new User("admin", "admin", "admin");
        User admin2 = new User("adminUsername2", "adminPassword2", "admin");
        User.UserUsernameArraylist.add(admin);
        User.UserUsernameArraylist.add(admin2);

        new User.LoginScreen();

        ParkingSlot.createSlots();
    }
}