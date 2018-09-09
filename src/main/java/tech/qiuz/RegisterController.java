package tech.qiuz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.qiuz.bean.User;
import tech.qiuz.service.RegisterService;

/**
 * @author qiuxiki
 */
@Controller
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Hello World!";
  }

  @RequestMapping("/register")
  String getRegister() {
    return "register";
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  @ResponseBody
  public boolean register(User user) {
    return registerService.save(user);
  }
}