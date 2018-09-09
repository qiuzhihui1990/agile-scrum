package tech.qiuz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.qiuz.bean.Answer;
import tech.qiuz.service.MoodService;

/**
 * @author qiuxiki
 */
@Controller
public class QuestionController {

  @Autowired
  private MoodService moodService;

  @RequestMapping("/today")
  String getQuestion() {
    return "question";
  }

  @RequestMapping(value = "/answer", method = RequestMethod.POST)
  @ResponseBody
  public boolean answer(Answer answer) {
    return moodService.save(answer);
  }
}