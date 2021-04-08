package platform.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import platform.model.CodeSnippet;

@Component
public class CodeSnippetsService {

  private final List<CodeSnippet> codeSnippets;

  public CodeSnippetsService() {
    codeSnippets = new ArrayList<>();
  }

  public CodeSnippet get(int id) {
    return codeSnippets.get(id - 1);
  }

  public AtomicInteger add(CodeSnippet codeSnippet) {
    codeSnippets.add(codeSnippet);
    return new AtomicInteger(codeSnippets.size());
  }

  public List<CodeSnippet> latest() {
    if (codeSnippets.isEmpty()) {
      return Collections.emptyList();
    }

    List<CodeSnippet> list = codeSnippets.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    return list.subList(0, Math.min(list.size(), 10));
  }

}
