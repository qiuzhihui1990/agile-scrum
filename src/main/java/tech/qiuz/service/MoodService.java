package tech.qiuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tech.qiuz.bean.Answer;

import java.util.Date;

/**
 * @author zhuihui.qiu
 */
@Service
public class MoodService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public boolean save(final Answer answer) {
    int result = jdbcTemplate
        .update("insert into mood(aid, mood, message, date) values(?,?,?,?)", answer.getAid(), answer.getMood(),
            answer.getMessage(), new Date());
    return result == 1;
  }
}