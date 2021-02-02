package exception;

public class NegativeNumberException extends Exception{
    private static final long serialVersionUID = 1L;
    private final int e;
    public NegativeNumberException(int e) {
        super();
        this.e = e;
    }

    public int getExpression() {
        return e;
    }
}
