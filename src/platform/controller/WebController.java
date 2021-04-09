package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.service.CodeSnippetsService;

@Controller
@RequestMapping("/code")
public class WebController {

  private final CodeSnippetsService service;

  @Autowired
  public WebController(CodeSnippetsService service) {
    this.service = service;
  }

  @GetMapping(path = "/new", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView showCreateSnippetHtmlForm() {
    return new ModelAndView("new");
  }

  @GetMapping(path = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView snippetByIdHtmlPage(@PathVariable("id") Integer id) {

    ModelAndView model = new ModelAndView("code");

    model.addObject("code", service.snippetById(id).code());
    model.addObject("date", service.snippetById(id).dateFormat());

    return model;
  }

  @GetMapping(path = "/latest", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView tenLatestSnippetsListHtmlPage() {

    ModelAndView model = new ModelAndView("latest");

    model.addObject("codeSnippets", service.tenLatestSnippetsList());

    return model;
  }

}
