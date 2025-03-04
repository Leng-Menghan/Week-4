package Exception;

public class EmailException extends IllegalArgumentException {
    public EmailException(String message) {
        super(message);
    }

    public EmailException(String input, String formaString) throws EmailException {
        if(!input.matches(formaString))
        {
            throw new EmailException("Must be a valid email");
        }
    }
}
