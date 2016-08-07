import java.io.IOException;
import java.util.*;

/**
 * Created by cthill on 8/7/16.
 */
public class MeetingDB extends FileDB {
    protected static final MeetingDB instance = new MeetingDB();
    protected enum Field {
        ID,
        ROOM,
        START,
        END
    }

    protected MeetingDB() {
        super("meetingdb.txt");
    }

    public static final MeetingDB getInstance() {
        return instance;
    }

    public List<Meeting> loadAll() {
        List<Meeting> l = new ArrayList<Meeting>();

        try {
            file.seek(0);
            while (!eof()) {
                int length = file.readInt();
                l.add(readRecord(length));
            }

            return l;
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    public Meeting load(int id) {
        int length = seekRecord(id);
        if (length > 0) {
            try {
                return readRecord(length);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
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
        Date s = new Date(file.readLong());

        int etimeLen = seekField(Field.END.ordinal(), end);
        if (etimeLen != 8) {
            return null;
        }
        Date e = new Date(file.readLong());

        Meeting m = new Meeting();
        m.id = id;
        m.room = r;
        m.start = s;
        m.end = e;

        return m;
    }

    public void save(Meeting m) {
        try {
            eraseRecord(m.id);

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(m.id);

            writeFieldHeader(Field.ROOM.ordinal(), 4);
            file.writeInt(m.room);

            writeFieldHeader(Field.START.ordinal(), 8);
            file.writeLong(m.start.getTime());

            writeFieldHeader(Field.END.ordinal(), 8);
            file.writeLong(m.end.getTime());

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
