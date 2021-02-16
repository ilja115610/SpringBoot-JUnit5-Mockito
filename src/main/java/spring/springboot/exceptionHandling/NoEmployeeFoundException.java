package spring.springboot.exceptionHandling;

public class NoEmployeeFoundException extends RuntimeException {


    public NoEmployeeFoundException(String message) {
        super(message);
    }
}
