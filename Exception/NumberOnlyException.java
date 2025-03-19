package Exception;
public class NumberOnlyException extends NumberFormatException {
    public NumberOnlyException(String message) {
        super(message);
    }

    public NumberOnlyException(String inpuString, String formaString, String message) throws NumberOnlyException {
        if(!inpuString.matches(formaString))
        {
            throw new NumberOnlyException(message);
        }
    }


}