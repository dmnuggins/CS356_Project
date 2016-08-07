/**
 * Created by cthill on 8/6/16.
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}