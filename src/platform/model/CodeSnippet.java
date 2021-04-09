package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import platform.service.CodeSnippetsService;

public class CodeSnippet implements Comparable<CodeSnippet> {

  private String code;
  private LocalDateTime date;

  public CodeSnippet() {
    this.date = LocalDateTime.now();
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
  @JsonFormat(pattern = CodeSnippetsService.DATE_PATTERN)
  @JsonProperty("date")
  public LocalDateTime date() {
    return date;
  }

  public String dateFormat() {
    return date.format(DateTimeFormatter.ofPattern(CodeSnippetsService.DATE_PATTERN));
  }

  @JsonSetter
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @Override
  public int compareTo(CodeSnippet codeSnippet) {
    return this.date.compareTo(codeSnippet.date);
  }

}
