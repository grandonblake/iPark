import java.util.LinkedList;
import java.util.Queue;

public class ParkingSlot{

    //states
    private String UserUsername;
    private boolean isAvailable;

    public ParkingSlot(){}
    public ParkingSlot(String UserUsername){
        this.UserUsername = UserUsername;
        isAvailable = true;
    }

    //arraylist
    private static ParkingSlot[] ParkingSlotArray = new ParkingSlot[10];

    //getters
    public String getUserUsername(){
        return UserUsername;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }

    public ParkingSlot[] getParkingSlotArray() {
        return ParkingSlotArray;
    }


    //setters
    public void setUserUsername(String set){
        UserUsername = set;
    }
    public void setIsAvailable(boolean set){
        isAvailable = set;
    }


    public static void createSlots(){
        for (int i = 0; i < 10; i++) {
            ParkingSlot slot = new ParkingSlot("");

            ParkingSlotArray[i] = slot;
        }
    }

    public boolean park(User currentlyLoggedIn){
        for (int i = 0; i < ParkingSlotArray.length; i++) {
            if(ParkingSlotArray[i].isAvailable){ //if a parkingSlot is available
                ParkingSlotArray[i].setUserUsername(currentlyLoggedIn.get("username")); //put currentlyLoggedIn object inside array
                ParkingSlotArray[i].setIsAvailable(false);

                return true;
            }
        }
        return false;
    }
}
