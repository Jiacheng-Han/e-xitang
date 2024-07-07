package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid数据库连接池工具类
 */
public class DruidUtils {
    //1. 定义DruidDateSource对象：表示Druid数据量连接池（数据源）
    private static DruidDataSource druidDataSource;

    //2. 定义静态代码块初始化DruidDateSource对象
    static{
        try {
            //读取druid.propertis配置文件中的内容
            // 此位置加上getClassLoader()类加载器的原因，在于我么你现在读取的配置文件在resource这个目录中
            InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            //使用属性文件初始化DruidDateSource对象
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3. 创建静态方法，从连接池中获取连接对象
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭连接
     * 多态的应用：使用Statement接口做从参数，既可以传递Statement接口对象，也可以传递
     * PreparedStatement接口对象
     */
    public static void close(Statement statement, Connection connection){
        close(null,statement,connection);
    }

    /**
     * 重载的方式如果存在ResultSet那么我们调用以下方法进行关闭，
     * 如果没有调用上面的关闭方法
     * 具体关闭方法
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            System.out.println("-----关闭连接数据库失败！");
        }
    }
}
