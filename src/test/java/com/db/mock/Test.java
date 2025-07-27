package com.db.mock;

import com.db.mock.comon.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: caozm
 * @create: 2022-06-17 22:49
 * @Version 1.0
 * @description:
 **/
public class Test {

    /**
     * 方式二
     * 在方式一的基础上使用批处理
     * 使用PreparedStatement ps;的
     * ps.addBatch();      将sql语句打包到一个容器中
     * ps.executeBatch();  将容器中的sql语句提交
     * ps.clearBatch();    清空容器，为下一次打包做准备
     * 这三个方法实现sql语句打包，累计到一定数量一次提交
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Connection conn = DbUtil.getConnection();//获取数据库连接
        String sql = "insert into t_test(id, name,current_time) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);//取消自动提交
            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, i);
                ps.setObject(2, i);
                ps.setObject(2, new Timestamp(new Date().getTime()));
                ps.addBatch();
                if (i % 500 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            ps.clearBatch();
            conn.commit();//所有语句都执行完毕后才手动提交sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        System.out.println("百万条数据插入用时：" + (System.currentTimeMillis() - start)+"【单位：毫秒】");
    }

}