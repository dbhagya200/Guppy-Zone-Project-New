package lk.ijse.backend.advisor;




import lk.ijse.backend.util.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseUtil exceptionHandler(Exception e){
        return new ResponseUtil(
                500,e.getMessage(),null
        );
    }
}
