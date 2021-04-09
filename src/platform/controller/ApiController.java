package platform.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.model.CodeSnippet;
import platform.service.CodeSnippetsService;

@Controller
@RequestMapping("/api/code")
public class ApiController {

  private final CodeSnippetsService service;

  @Autowired
  public ApiController(CodeSnippetsService service) {
    this.service = service;
  }

  @PostMapping(path = "/new")
  public ResponseEntity<String> createNewSnippet(@RequestBody CodeSnippet codeSnippet) {

    return ResponseEntity.ok().header("Content-type",
        "application/json").body("{ \"id\" : \"" + service.addNewSnippet(codeSnippet) + "\" }");
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<CodeSnippet> snippetJsonById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.snippetById(id));
  }

  @GetMapping(value = "/latest", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<CodeSnippet>> tenLatestSnippetsJsonList() {
    return ResponseEntity.ok(service.tenLatestSnippetsList());
  }

}
