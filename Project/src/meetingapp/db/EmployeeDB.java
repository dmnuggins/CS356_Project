package meetingapp.db;

import meetingapp.entity.Employee;

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

    public List<Employee> loadAll() {
        List<Employee> l = new ArrayList<Employee>();

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

    public Employee load(int id) {
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

        int reservedLen = seekField(Field.RESERVED.ordinal(), end);
        List<Date> res = new ArrayList<Date>();
        if (reservedLen > 0) {
            Date d = new Date();
            d.setYear(file.readInt());
            d.setMonth(file.readInt());
            d.setDate(file.readInt());
            res.add(d);
        }

        Employee e = new Employee();
        e.ID = id;
        e.name = name;
        e.isAdmin = isAdmin;
        e.reserved = res;

        return e;
    }

    public void save(Employee e) {
        try {
            eraseRecord(e.ID);

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(e.ID);

            writeFieldHeader(Field.NAME.ordinal(), e.name.length());
            file.writeBytes(e.name);

            writeFieldHeader(Field.ADMIN.ordinal(), 1);
            file.writeBoolean(e.isAdmin);

            writeFieldHeader(Field.RESERVED.ordinal(), e.reserved.size() * 3 * 4); //3 ints per date, 4 bytes per int
            for (int i = 0; i < e.reserved.size(); i++) {
                Date d = e.reserved.get(i);
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

