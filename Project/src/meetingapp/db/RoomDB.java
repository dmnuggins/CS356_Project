package meetingapp.db;

import meetingapp.entity.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Created by cthill on 8/7/16.
 */
public class RoomDB extends FileDB {
    protected static final RoomDB instance = new RoomDB();
    protected enum Field {
        ID,
        CAP
    }

    protected RoomDB() {
        super("room");
    }

    public static final RoomDB getInstance() {
        return instance;
    }

    protected Room readRecord(int length) throws IOException{
        long end = file.getFilePointer() + length;

        int idLen = seekField(Field.ID.ordinal(), end);
        if (idLen != 4) {
            return null;
        }
        int id = file.readInt();

        int capLen = seekField(Field.CAP.ordinal(), end);
        int cap = 0;
        if (capLen > 0) {
            cap = file.readInt();
        }

        return new Room(id, cap);
    }

    public void writeRecord(Entity e) {
        Room r = (Room) e;
        try {
            eraseRecord(r.getID());

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(r.getID());

            writeFieldHeader(Field.CAP.ordinal(), 4);
            file.writeInt(r.capacity);

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
