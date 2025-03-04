package Exception;
public class LimitOptionStudentException extends IllegalArgumentException {
    public LimitOptionStudentException(String message) {
        super(message);
    }

    public LimitOptionStudentException(int input) throws LimitOptionStudentException {
        if(input>8 || input<1){
            throw new LimitOptionStudentException("Number must be between 1 and 8");
        }
    }
}
