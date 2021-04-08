package platform.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.service.CodeSnippetsService;

@Controller
public class WebController {

  private final CodeSnippetsService service;

  @Autowired
  public WebController(CodeSnippetsService service) {
    this.service = service;
  }

  @GetMapping(path = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView newCodeHTML() {
    return new ModelAndView("new");
  }


  @GetMapping(path = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView codeHTML(@PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id) {

    ModelAndView model = new ModelAndView("code");

    model.addObject("code", service.get(id).code());
    model.addObject("date", service.get(id).dateFormat());

    return model;
  }


  @GetMapping(path = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public ModelAndView latestHTML() {

    ModelAndView model = new ModelAndView("latest");

    model.addObject("codeSnippets", service.latest());

    return model;
  }

}
