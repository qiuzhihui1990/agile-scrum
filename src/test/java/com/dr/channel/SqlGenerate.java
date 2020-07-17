package com.dr.channel;

import com.dianrong.borrow.api.common.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileReader;

public class SqlGenerate {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("/Users/qiuzhihui/uuuu1.csv"));//换成你的文件名
      reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
      String line = null;
      while((line=reader.readLine())!=null){
        String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
        String insertSql = "INSERT INTO dr_mainapp_archive.dr_transaction_97 (ID, INIT_D, SCHEDULE_D, EXEC_D, COMPLETION_D, INIT_ID, FROM_ID, TO_ID, FROM_AID, TO_AID, STATUS, SUB_STATUS, RECON_STATUS, BANK_RECON_STATUS, TYPE, SUBTYPE, EXT_ID, EXT_STATUS, LP_ID, LOAN_ID, LPAY_ID, FROM_ACC_BALANCE, TO_ACC_BALANCE, OP_ACC, OUT_PAYMENT_TRANS_ID, AMOUNT, EXT_COLUMN1, EXT_COLUMN2, AID, STORY_IDS, IN_FLAG, TYPE_NAME, UPDATE_STATUS, UPDATE_TIME, CREATE_TIME) VALUES (";
        for (String i : item) {
          if (StringUtil.isEmptyString(i)) {
            i = "null";
            insertSql = insertSql.concat(",").concat(i);
          } else {
            if (i.contains("T") || i.contains("NONE"))
              i = "'" + i + "'";
            if (i.contains("/")) {
             i = "STR_TO_DATE('".concat(i).concat("', '%m/%d/%Y %h:%i:%s')");
            }
            insertSql = insertSql.concat(",").concat(i);
          }
        }
        insertSql = insertSql.concat(");");
        System.out.println(insertSql);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
