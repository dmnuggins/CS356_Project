import java.io.IOException;

/**
 * Created by cthill on 8/6/16.
 */
public class LoginDB extends FileDB{
    private static final LoginDB instance = new LoginDB();
    private enum Field {
        ID,
        USER,
        PASS
    }

    protected LoginDB() {
        super("logindb.txt");
    }

    public static final LoginDB getInstance() {
        return instance;
    }

    public Login load(int id) {
        int length = seekRecord(id);
        if (length > 0) {
            try {
                long start = file.getFilePointer();

                int userLen = seekField(Field.USER.ordinal(), length);
                String user = "";
                if (userLen > -1) {
                    byte b[] = new byte[userLen];
                    file.read(b);
                    user = new String(b);
                }

                int passLen = seekField(Field.PASS.ordinal(), length);
                String pass = "";
                if (passLen > -1) {
                    byte b[] = new byte[passLen];
                    file.read(b);
                    pass = new String(b);
                }



                Login l = new Login();
                l.employeeID = id;
                l.username = user;
                l.password = pass;

                return l;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    public void save(Employee e) {
        try {
            int length = seekRecord(e.ID);
            //record alreay exists
            if (length > 0) {
                //save start of record
                long start = file.getFilePointer() - 4;
                //skip to next record
                file.skipBytes(length);

                if (!eof()) {
                    //copy rest of file into byte array
                    int blen = (int) (file.length() - file.getFilePointer());
                    byte b[] = new byte[blen];
                    file.read(b);

                    //truncate file
                    file.seek(start);
                    file.setLength(start);
                    //write data back
                    file.write(b);
                } else {
                    file.seek(start);
                    file.setLength(start);
                }
            }

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(e.ID);

            writeFieldHeader(Field.USER.ordinal(), e.name.length());
            file.writeBytes(e.name);

            writeFieldHeader(Field.PASS.ordinal(), 1);
            file.writeBoolean(e.isAdmin);

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

