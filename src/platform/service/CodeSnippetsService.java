package platform.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import platform.model.CodeSnippet;

@Component
public class CodeSnippetsService {

  public final static String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

  private final List<CodeSnippet> codeSnippets;

  public CodeSnippetsService() {
    codeSnippets = new ArrayList<>();
  }

  public CodeSnippet snippetById(int id) {
    if (id == 0) {
      throw new IndexOutOfBoundsException();
    } else if (codeSnippets.size() <= id) {
      throw new NoSuchElementException();
    }
    return codeSnippets.get(id - 1);
  }

  public String addNewSnippet(CodeSnippet codeSnippet) {
    codeSnippets.add(codeSnippet);
    return String.valueOf(codeSnippets.size());
  }

  public List<CodeSnippet> tenLatestSnippetsList() {
    if (codeSnippets.isEmpty()) {
      return Collections.emptyList();
    }

    List<CodeSnippet> list = codeSnippets.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    return list.subList(0, Math.min(list.size(), 10));
  }

}
