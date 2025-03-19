package Exception;

public class EmailInputException extends IllegalArgumentException{
    public EmailInputException(String message) {
        super(message);
    }
    public EmailInputException(String input, String formaString) throws EmailInputException {
        if(!input.matches(formaString))
        {
            throw new EmailInputException("Invalid Email");
        }
    }
}
