package com.example.jdbc.repository;



import com.example.jdbc.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Repository注解：标注这是一个持久化操作对象
 */
@Repository
public class UserRepository {
    //注入JdbcTemplate模板对象
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入数据
     * @return 插入影响的行数
     */
    public Integer insertUser(String login_name,String username,String password){
        String sql = "insert into tb_user(login_name, username, password) values(?, ?, ?)";
        Object args[] = new Object[]{login_name,username,password};
        //参数一：插入数据的SQL语句，参数二：对应SQL语句中占位符？的参数
        return jdbcTemplate.update(sql, args);
    }


    /**
     * 查询所有数据
     * @return:list<User>
     */
    /*
    public List<User> findStu(){
        String sql = "select * from tb_user";
        //定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        //执行查询方法
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        return user;
    }*/


    /***
     * 根据userName查询数据
     * @param username
     * @return User对象
     */
    public User selectByUsername(String username){
        //定义SQL语句
        String sql = "select * from tb_user where username = ?";
        //定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        //执行查询方法
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        return user;
    }

    /**
     * 根据id查询数据
     * @param id
     * @return User对象
     */
    public User findUserById(Integer id){
        //定义SQL语句
        String sql = "select * from tb_user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        //执行查询方法
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    /**
     * 查询所有数据
     * @return 包含User对象的List集合
     */
    public List<User> findAll(){
        //定义SQL语句
        String sql = "select * from tb_user";
        //声明结果集的映射rowMapper,将结果集的数据映射成User对象数据
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    public void delete(final Integer id){
        //定义SQL语句
        String sql = "delete from tb_user where id=?";
        //执行
        jdbcTemplate.update(sql, new Object[]{id});
    }

    /**
     * 修改数据
     * @param
     */
    /*
    public void update(final User user){
        //定义SQL语句
        String sql = "update tb_user set username=?, login_name=?, where id=?";
        //执行
        jdbcTemplate.update(sql, new Object[]{user.getUsername(),user.getLoginName(), user.getId()});
    }*/
    public void update(String username,String login_name,Integer id){
        //定义SQL语句
        String sql = "update tb_user set username=?, login_name=? where id=?";
        Object args[] = new Object[]{login_name,username,id};
        //执行
        jdbcTemplate.update(sql, args);
    }

    public void upDate(String username,String login_name,String password,Integer id){
        //定义SQL语句
        String sql = "update tb_user set username=?, login_name=?,password=? where id=?";
        Object args[] = new Object[]{login_name,username,password,id};
        //执行
        jdbcTemplate.update(sql, args);
    }


    /**
     * 插入数据，获取被插入数据的主键
     * @param user
     * @return
     */
    public User insertGetKey(User user){
        //声明插入的SQL语句
        String sql = "insert into tb_user(username, login_name, password) values(?, ?, ?)";
        //定义插入数据后获取主键的对象
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //插入数据后，将被插入数据的主键返回
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getLoginName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        }, holder);
        //获取被插入数据库的主键，注入到user对象
        Integer newUserId = holder.getKey().intValue();
        user.setId(newUserId);
        return user;
    }

    /**判断字符串是否全部为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    public List<User> findByKeys(String keys) {
        String sql = null;
        if(isNumeric(keys)){
            sql = "select * from tb_user where id ="+keys+" or password like '%"+keys+"%' or username like '%"+keys+"%'";
        }else if(keys==""){
            sql = "select * from tb_user";
        } else{
            sql = "select * from tb_user where password like '%"+keys+"%' or username like '%"+keys+"%'";
        }
//        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        //执行查询方法
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        return jdbcTemplate.query(sql,rowMapper);
    }
}
