package meetingapp.db;

import meetingapp.entity.Entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cthill on 8/7/16.
 */
public abstract class FileDB {
    protected String filename;
    protected RandomAccessFile file;
    protected enum Field {
        ID
    }

    public FileDB(String fn) {
        filename = fn;
        try {
            file = new RandomAccessFile(filename + ".dbfile", "rw");
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNextID() {
        int nextID = -1;
        try {
            file.seek(0);
            while (!eof()) {
                int length = file.readInt();
                long start = file.getFilePointer();
                int idlen = seekField(Field.ID.ordinal(), start + length);
                if (idlen > 0) {
                    int id = file.readInt();
                    if (id > nextID) {
                        nextID = id;
                    }
                }
                file.seek(start + length); //seek to next record
            }

            return nextID + 1;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    abstract void writeRecord(Entity e);

    abstract Entity readRecord(int length) throws IOException;

    public Entity load(int id) {
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

    public List<Entity> loadAll() {
        List<Entity> l = new ArrayList<>();

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
    };

    public void save(Entity e) {
        writeRecord(e);
    }

    //seeks a record by id and returns its length. Returns -1 if it does not exist
    protected int seekRecord(int id) {
        try {
            file.seek(0);
            while (!eof()) {
                //read record length
                int length = file.readInt();
                long start = file.getFilePointer();

                //seek ID field
                seekField(Field.ID.ordinal(), start + length);
                int idRead = file.readInt();

                if (idRead == id) {
                    file.seek(start);
                    return length;
                } else {
                    file.seek(start + length); //seek to next record
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return -1;
    }

    public boolean eraseRecord(int ID) throws IOException {
        int length = seekRecord(ID);
        //record exists
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

            return true;
        }

        return false;
    }

    //seeks to starting point of specific field in record
    protected int seekField(int f, long end) throws IOException {
        while (file.getFilePointer() < end) {
            int l = file.readInt();
            int ordinal = file.readInt();
            long fieldStart = file.getFilePointer();

            if (f == ordinal) {
                //file.seek(fieldStart);
                return l;
            }

            file.skipBytes(l);
        }

        return -1;
    }

    protected void writeFieldHeader(int f, int length) throws IOException {
        file.writeInt(length);
        file.writeInt(f);
    }

    protected void writeString(int f, String s) throws IOException {
        writeFieldHeader(f, s.length());
        file.writeBytes(s);
    }

    protected boolean eof() throws IOException {
        return file.getFilePointer() == file.length();
    }
}
