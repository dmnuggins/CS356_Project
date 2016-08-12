package meetingapp.db;

import meetingapp.entity.*;
import java.io.IOException;

/**
 * Created by cthill on 8/6/16.
 */
public class ParticipantDB extends FileDB {
    protected static final ParticipantDB instance = new ParticipantDB();
    protected enum Field {
        ID,
        EMPLOYEE,
        MEETING,
        ISOWNER,
        ACCEPTED,
        SEEN,
        SEENOWNER
    }

    protected ParticipantDB() {
        super("participant");
    }

    public static final ParticipantDB getInstance() {
        return instance;
    }

    protected Participant readRecord(int length) throws IOException{
        long end = length + file.getFilePointer();

        int len = seekField(Field.ID.ordinal(), end);
        if (len != 4) {
            return null;
        }
        int id = file.readInt();

        len = seekField(Field.EMPLOYEE.ordinal(), end);
        if (len != 4) {
            return null;
        }
        int empid = file.readInt();

        len = seekField(Field.MEETING.ordinal(), end);
        if (len != 4) {
            return null;
        }
        int mid = file.readInt();


        len = seekField(Field.ISOWNER.ordinal(), end);
        boolean isOwner = false;
        if (len > 0) {
            isOwner = file.readBoolean();
        }

        len = seekField(Field.ACCEPTED.ordinal(), end);
        boolean acc = false;
        if (len > 0) {
            acc = file.readBoolean();
        }

        len = seekField(Field.SEEN.ordinal(), end);
        boolean seen = false;
        if (len > 0) {
            seen = file.readBoolean();
        }

        len = seekField(Field.SEENOWNER.ordinal(), end);
        boolean seen2 = false;
        if (len > 0) {
            seen2 = file.readBoolean();
        }

        return new Participant(id, empid, mid, isOwner, acc, seen, seen2);
    }

    public void writeRecord(Entity e) {
        Participant em = (Participant) e;
        try {
            eraseRecord(em.getID());

            long start = file.getFilePointer();
            file.writeInt(0); //placeholder for length

            writeFieldHeader(Field.ID.ordinal(), 4);
            file.writeInt(em.getID());

            writeFieldHeader(Field.EMPLOYEE.ordinal(), 4);
            file.writeInt(em.getEmployeeID());

            writeFieldHeader(Field.MEETING.ordinal(), 4);
            file.writeInt(em.getMeetingID());

            writeFieldHeader(Field.ISOWNER.ordinal(), 1);
            file.writeBoolean(em.getIsOwner());

            writeFieldHeader(Field.ACCEPTED.ordinal(), 1);
            file.writeBoolean(em.getAccepted());

            writeFieldHeader(Field.SEEN.ordinal(), 1);
            file.writeBoolean(em.getSeen());

            writeFieldHeader(Field.SEENOWNER.ordinal(), 1);
            file.writeBoolean(em.getSeenByOwner());

            long end = file.getFilePointer();
            file.seek(start);
            file.writeInt((int) (end - start - 4));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

