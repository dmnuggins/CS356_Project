package meetingapp.entity;

import meetingapp.db.RoomDB;
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

    protected void save() {
        RoomDB.getInstance().save(this);
    }

    public static Room get(int id) {
        return (Room) RoomDB.getInstance().load(id);
    }

    public static List<Room> getAll() {
        return (List<Room>)(List<?>) RoomDB.getInstance().loadAll();
    }
}
