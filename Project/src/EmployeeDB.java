import java.io.IOException;

/**
 * Created by cthill on 8/6/16.
 */
public class EmployeeDB extends FileDB{
    private static final EmployeeDB instance = new EmployeeDB();
    private enum Field {
        ID,
        NAME,
        ADMIN
    }

    protected EmployeeDB() {
        super("employeedb.txt");
    }

    public static final EmployeeDB getInstance() {
        return instance;
    }

    public Employee load(int id) {
        int length = seekRecord(id);
        if (length > 0) {
            try {
                long start = file.getFilePointer();

                int nameLen = seekField(Field.NAME.ordinal(), length);
                String name = "";
                if (nameLen > -1) {
                    byte nameBytes[] = new byte[nameLen];
                    file.read(nameBytes);
                    name = new String(nameBytes);
                }

                int adminLen = seekField(Field.ADMIN.ordinal(), length);
                boolean isAdmin = false;
                if (adminLen > 0) {
                    isAdmin = file.readBoolean();
                }

                Employee e = new Employee();
                e.ID = id;
                e.name = name;
                e.isAdmin = isAdmin;

                return e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
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

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

