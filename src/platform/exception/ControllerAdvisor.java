package platform.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import platform.service.CodeSnippetsService;

@ControllerAdvice
public class ControllerAdvisor {

  private Map<String, String> createErrorResponse(HttpStatus status, String message) {
    Map<String, String> response = new LinkedHashMap<>();

    response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern(
        CodeSnippetsService.DATE_PATTERN)));
    response.put("status", String.valueOf(status.value()));
    response.put("message", message);

    return response;
  }

  @ExceptionHandler(value = {NumberFormatException.class, IndexOutOfBoundsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleBadRequestById() {

    return createErrorResponse(HttpStatus.BAD_REQUEST, "id must be from 1 to max integer value");
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> handleNoSuchElementById() {

    return createErrorResponse(HttpStatus.BAD_REQUEST, "no such code snippet in database");
  }

}
