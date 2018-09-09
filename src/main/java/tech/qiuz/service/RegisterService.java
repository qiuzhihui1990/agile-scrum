package tech.qiuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import tech.qiuz.bean.Employees;
import tech.qiuz.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhuihui.qiu
 */
@Service
public class RegisterService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Employees> getList(){
    String sql = "SELECT ID,FNAME,LNAME,STORE_ID, DEPARTMENT_ID   FROM EMPLOYEES";
    return (List<Employees>) jdbcTemplate.query(sql, new RowMapper<Employees>(){

      @Override
      public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employees stu = new Employees();
        stu.setId(rs.getLong("ID"));
        stu.setFname(rs.getString("FNAME"));
        stu.setLname(rs.getString("LNAME"));
        stu.setDepartmentId(rs.getLong("DEPARTMENT_ID"));
        stu.setStoreId(rs.getLong("STORE_ID"));
        return stu;
      }

    });
  }

  public boolean save(final User user) {
    int result = jdbcTemplate
        .update("insert into user(email, password, name, team_id) values(?,?,?,?)", user.getEmail(), user.getPassword(),
            user.getName(), user.getTeamId());
    return result == 1;
  }
}