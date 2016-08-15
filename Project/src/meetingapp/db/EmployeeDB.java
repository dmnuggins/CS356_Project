package meetingapp.db;

import meetingapp.entity.Employee;
import meetingapp.entity.Entity;

import java.io.IOException;
import java.util.*;

/**
 * Created by cthill on 8/6/16.
 */
public class EmployeeDB extends FileDB {
    protected static final EmployeeDB instance = new EmployeeDB();
    protected enum Field {
        ID,
        NAME,
        ADMIN,
        RESERVED
    }

    protected EmployeeDB() { super("employee"); }

    public static final EmployeeDB getInstance() { return instance; }

    protected Employee readRecord(int length) throws IOException{
        long end = length + file.getFilePointer();

        int idLen = seekField(Field.ID.ordinal(), end);
        if (idLen != 4) {
            return null;
        }
        int id = file.readInt();

        int nameLen = seekField(Field.NAME.ordinal(), end);
        String name = "";
        if (nameLen > -1) {
            byte nameBytes[] = new byte[nameLen];
            file.read(nameBytes);
            name = new String(nameBytes);
        }

        int adminLen = seekField(Field.ADMIN.ordinal(), end);
        boolean isAdmin = false;
        if (adminLen > 0) {
            isAdmin = file.readBoolean();
        }

        Employee e = new Employee(id, name, isAdmin);

        int reservedLen = seekField(Field.RESERVED.ordinal(), end);
        if (reservedLen > 0) {
            Date d = new Date();
            d.setYear(file.readInt());
            d.setMonth(file.readInt());
            d.setDate(file.readInt());
            e.reserveDate(d);
        }

        return e;
    }

    public void writeRecord(Entity ent) {
        Employee e = (Employee) ent;
        try {
            eraseRecord(e.getID());

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(e.getID());

            writeString(Field.NAME.ordinal(), e.getName());

            writeFieldHeader(Field.ADMIN.ordinal(), 1);
            file.writeBoolean(e.getIsAdmin());

            writeFieldHeader(Field.RESERVED.ordinal(), e.getReserved().size() * 3 * 4); //3 ints per date, 4 bytes per int
            for (int i = 0; i < e.getReserved().size(); i++) {
                Date d = e.getReserved().get(i);
                file.writeInt(d.getYear());
                file.writeInt(d.getMonth());
                file.writeInt(d.getDate());
            }

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

