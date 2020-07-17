//package tech.qiuz;
//
//import org.apache.commons.logging.Log;
//import org.assertj.core.util.Lists;
//import org.h2.jdbcx.JdbcConnectionPool;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import lombok.Cleanup;
//import lombok.extern.slf4j.Slf4j;
//
//public class H2DataBaseTest {
//  private static JdbcConnectionPool pool;
//  private static Log log;
//
//  @BeforeClass
//  public static void onlyOnce() throws SQLException {
//    InputStream is = H2DataBaseTest.class.getResourceAsStream("/ops-config/sql/h2/init.sql");
//    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//    pool = JdbcConnectionPool.create("jdbc:h2:~/agile_lender", "sa", "sa");
//    @Cleanup Connection conn = pool.getConnection();
//    @Cleanup Statement stat = conn.createStatement();
//
//    List<String> ddlList = Lists.newArrayList();
//    reader.lines().forEach(line -> {
//      int lastIndex = ddlList.size() - 1;
//
//      if (ddlList.size() == 0 || ddlList.get(lastIndex).endsWith(";")) {
//        ddlList.add(line);
//      } else {
//        String tempSql = ddlList.get(lastIndex).concat(line);
//        ddlList.remove(lastIndex);
//        ddlList.add(lastIndex, tempSql);
//      }
//    });
//    log.info(ddlList.toString());
//
//    ddlList.stream().forEach(sql -> {
//      try {
//       stat.addBatch(sql);
//      } catch (SQLException e) {
//        log.error("add batch sql : {} error", sql, e);
//      }
//    });
//  stat.executeBatch();
//  }
//
//  @Before
//  public void prepareData() throws SQLException {
//    @Cleanup Connection conn = pool.getConnection();
//    @Cleanup Statement stat = conn.createStatement();
//    stat.executeUpdate("insert into user values (default, 'aaa3', 'ccc', 'ddd', 1)");
//    stat.executeUpdate("insert into user values (default, 'aaa4', 'ccc', 'ddd', 1)");
//    stat.executeUpdate("insert into mood(aid, mood, message, date) values(1,3,'22', '2018-01-02')");
//
//  }
//
//  @Test
//  public void testDBConnection() throws SQLException {
//    @Cleanup Connection conn = pool.getConnection();
//    @Cleanup Statement stat = conn.createStatement();
//    ResultSet resultSet = stat.executeQuery("select * from user");
//    while (resultSet.next()) {
//      Long id = resultSet.getLong("id");
//      String name = resultSet.getString("email");
//      log.info("{}, {}", id, name);
//    }
//
//    ResultSet resultSet1 = stat.executeQuery("select * from mood");
//    while (resultSet1.next()) {
//      Long id = resultSet1.getLong("id");
//      Long mood = resultSet1.getLong("mood");
//      log.info("{}, {}", id, mood);
//    }
//  }
//}
