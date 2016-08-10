package meetingapp.entity;

import meetingapp.db.RoomDB;

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
}
