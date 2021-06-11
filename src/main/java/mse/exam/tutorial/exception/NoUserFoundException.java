package mse.exam.tutorial.exception;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException()
    {
        super("사용자를 찾을 수 없습니다");
    }
}
