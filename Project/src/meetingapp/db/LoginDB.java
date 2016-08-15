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

        return new Login(id, user, pass);
    }

    public void writeRecord(Entity e) {
        Login l = (Login) e;
        try {
            eraseRecord(l.getEmployeeID());

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(l.getEmployeeID());

            writeString(Field.USER.ordinal(), l.getUsername());

            writeString(Field.PASS.ordinal(), l.getPassword());

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

