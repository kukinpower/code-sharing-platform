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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import platform.service.CodeSnippetsService;

@Entity(name = "snippet")
//@Table(name = "snippets")
public class CodeSnippet implements Comparable<CodeSnippet> {

  @JsonIgnore
  @Id
  @Column
  private long id;

  @Column
  private String code;

  @Column
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CodeSnippet that = (CodeSnippet) o;

    if (id != that.id) {
      return false;
    }
    if (code != null ? !code.equals(that.code) : that.code != null) {
      return false;
    }
    return date != null ? date.equals(that.date) : that.date == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (code != null ? code.hashCode() : 0);
    result = 31 * result + (date != null ? date.hashCode() : 0);
    return result;
  }

}
