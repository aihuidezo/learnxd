package com.lonely.zo.learnxd;

import com.lonely.zo.learnxd.model.User;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 9:37 2019/8/14
 * @Modified By:
 */
public class utileTest {
    @Test
    public void printInsert(){
        for (int i = 13; i <= 50; i++) {
            System.out.println("INSERT INTO `t_question` VALUES ('"+i+"', 'https://www.runoob.com/mysql/mysql-tutorial.html what it is?', '1565425240496', '1565425240496', '14', '0', '0', '0', 'question');");
        }
    }
}
