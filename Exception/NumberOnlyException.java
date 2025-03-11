package Exception;
public class NumberOnlyException extends NumberFormatException {
    public NumberOnlyException(String message) {
        super(message);
    }

    public NumberOnlyException(String inpuString, String formaString) throws NumberOnlyException {
        if(!inpuString.matches(formaString))
        {
            throw new NumberOnlyException("Number only");
        }
    }
    
    public NumberOnlyException(double input) throws NumberOnlyException {
        if(input<=0){
            throw new NumberOnlyException("Number can not smaller than Zero");
        }
    }

}