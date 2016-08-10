package meetingapp.db;

import meetingapp.entity.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by cthill on 8/6/16.
 */
public class LoginDB extends FileDB {
    protected static final LoginDB instance = new LoginDB();
    protected enum Field {
        ID,
        USER,
        PASS
    }

    protected LoginDB() {
        super("login");
    }

    public static final LoginDB getInstance() {
        return instance;
    }

    public List<Login> loadAll() {
        List<Login> l = new ArrayList<Login>();

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

    public Login load(int id) {
        int length = seekRecord(id);
        if (length > 0) {
            try {
                readRecord(length);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    protected Login readRecord(int length) throws IOException{
        long end = file.getFilePointer() + length;

        int idLen = seekField(Field.ID.ordinal(), end);
        if (idLen != 4) {
            return null;
        }
        int id = file.readInt();

        int userLen = seekField(Field.USER.ordinal(), end);
        String user = "";
        if (userLen > -1) {
            byte b[] = new byte[userLen];
            file.read(b);
            user = new String(b);
        }

        int passLen = seekField(Field.PASS.ordinal(), end);
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
    }

    public void save(Login l) {
        try {
            eraseRecord(l.employeeID);

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(l.employeeID);

            writeFieldHeader(Field.USER.ordinal(), l.username.length());
            file.writeBytes(l.username);

            writeFieldHeader(Field.PASS.ordinal(), l.password.length());
            file.writeBytes(l.password);

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

