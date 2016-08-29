package meetingapp.entity;

import meetingapp.db.RoomDB;

import java.io.IOException;
import java.util.List;

/**
 * Created by cthill on 8/7/16.
 */
public class Room extends Entity {

    public int capacity;

    public Room(int ID, int capacity) {
        super(ID);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        save();
    }

    public void save() {
        RoomDB.getInstance().save(this);
    }

    public static Room get(int id) {
        return (Room) RoomDB.getInstance().load(id);
    }

    public static List<Room> getAll() {
        return (List<Room>)(List<?>) RoomDB.getInstance().loadAll();
    }

    public void delete(){
        try {
            RoomDB.getInstance().eraseRecord(this.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
