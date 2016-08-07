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
        super("roomdb.txt");
    }

    public static final RoomDB getInstance() {
        return instance;
    }

    public List<Room> loadAll() {
        List<Room> l = new ArrayList<Room>();

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

    public Room load(int id) {
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

        Room r = new Room();
        r.num = id;
        r.capacity = cap;

        return r;
    }

    public void save(Room r) {
        try {
            eraseRecord(r.num);

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(r.num);

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
