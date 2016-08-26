package meetingapp.db;

import com.sun.xml.internal.ws.api.model.MEP;
import meetingapp.entity.*;
import java.io.IOException;
import java.util.*;
import java.time.*;

/**
 * Created by cthill on 8/7/16.
 */
public class MeetingDB extends FileDB {
    protected static final MeetingDB instance = new MeetingDB();
    protected enum Field {
        ID,
        ROOM,
        START
    }

    protected MeetingDB() {
        super("meeting");
    }

    public static final MeetingDB getInstance() {
        return instance;
    }

    protected Meeting readRecord(int length) throws IOException{
        long end = file.getFilePointer() + length;

        int idLen = seekField(Field.ID.ordinal(), end);
        if (idLen != 4) {
            return null;
        }
        int id = file.readInt();

        int roomLen = seekField(Field.ROOM.ordinal(), end);
        if (roomLen != 4) {
            return null;
        }
        int r = file.readInt();

        int stimeLen = seekField(Field.START.ordinal(), end);
        if (stimeLen != 8) {
            return null;
        }
        LocalDateTime s = LocalDateTime.ofEpochSecond(file.readLong(), 0, ZoneOffset.UTC);

        return new Meeting(id, r, s);
    }

    public void writeRecord(Entity e) {
        Meeting m = (Meeting) e;
        try {
            eraseRecord(m.getID());

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(m.getID());

            writeFieldHeader(Field.ROOM.ordinal(), 4);
            file.writeInt(m.getRoomID());

            writeFieldHeader(Field.START.ordinal(), 8);
            file.writeLong(m.getStart().toEpochSecond(ZoneOffset.UTC));

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
