
public class CastException extends ClassCastException {
    public CastException() {
        super();
    }
    public CastException(User U) throws CastException {
        if (!(U instanceof Librarian) && !(U instanceof Student)) {
            throw new CastException();
        }
        else{
            System.out.println("Transformation completed");	
        }
    }
}

