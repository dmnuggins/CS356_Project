import java.io.IOException;
import java.io.RandomAccessFile;

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
            file = new RandomAccessFile(filename, "rw");
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
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
                seekField(Field.ID.ordinal(), length);
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

    protected boolean eraseRecord(int ID) throws IOException {
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

    protected boolean eof() throws IOException {
        return file.getFilePointer() == file.length();
    }

    //seeks to starting point of specific field in record
    protected int seekField(int f, int maxBytes) throws IOException{
        int read = 0;
        while (read < maxBytes) {
            int l = file.readInt();
            int ordinal = file.readInt();
            long fieldStart = file.getFilePointer();

            if (f == ordinal) {
                //file.seek(fieldStart);
                return l;
            }

            read += 8 + l;
            file.skipBytes(l);
        }

        return -1;
    }

    protected void writeFieldHeader(int f, int length) throws IOException{
        file.writeInt(length);
        file.writeInt(f);
    }
}
