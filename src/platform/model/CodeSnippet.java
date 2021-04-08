package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeSnippet implements Comparable<CodeSnippet> {

  private static final String HTML_FORMAT = "<html>\n"
      + "<head>\n"
      + "    <title>Code</title>\n"
      + "</head>\n"
      + "<body>\n"
      + "<span id=\"load_date\"> %s </span>"
      + "    <pre id=\"code_snippet\">\n"
      + "%s"
      + "</pre>\n"
      + "</body>\n"
      + "</html>";

  private final static String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

  private String code;
  private LocalDateTime date;

  public CodeSnippet() {
    this.date = LocalDateTime.now();
  }

  public CodeSnippet(String code, LocalDateTime date) {
    this.code = code;
    this.date = date;
  }

  @JsonGetter
  public String code() {
    return code;
  }

  @JsonSetter
  public void setCode(String code) {
    this.code = code;
    this.date = LocalDateTime.now();
  }

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = DATE_PATTERN)
  @JsonProperty("date")
  public LocalDateTime date() {
    return date;
  }

  public String dateFormat() {
    return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
  }

  @JsonSetter
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @JsonIgnore
  public String codeHTML() {
    return String.format(HTML_FORMAT, date.format(DateTimeFormatter.ofPattern(DATE_PATTERN)), code);
  }

  @Override
  public int compareTo(CodeSnippet codeSnippet) {
    return this.date.compareTo(codeSnippet.date);
  }
}
