package platform.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.model.CodeSnippet;
import platform.service.CodeSnippetsService;

@Controller
public class ApiController {

  private final CodeSnippetsService service;

  @Autowired
  public ApiController(CodeSnippetsService service) {
    this.service = service;
  }

  @PostMapping(path = "/api/code/new")
  public ResponseEntity<String> newCode(@RequestBody CodeSnippet codeSnippet) {

    AtomicInteger id = service.add(codeSnippet);

    return ResponseEntity.ok().header("Content-type",
        "application/json").body("{ \"id\" : \"" + id.toString() + "\" }");
  }

  @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<CodeSnippet> codeJSON(
      @PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id) {
    return ResponseEntity.ok(service.get(id));
  }

  @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<CodeSnippet>> latestJSON() {
    return ResponseEntity.ok(service.latest());
  }

}
