package service;
/**
 * @author ctp
 */

import mapper.Mapper;
import org.apache.ibatis.session.SqlSession;
import pojo.User;
import util.SqlSessionFactoryUtil;

import java.util.List;

public class UserService {
    /**
     * 登陆
     * @param user
     * @return
     */
    public boolean login(User user){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            User u=mapper.findUser(user);
            if(u!=null){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return false;
    }

    /**
     * 检查用户名是否存在
     * @param user
     * @return
     */
    public boolean check(User user){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            User u=mapper.checkUser(user);
            if(u!=null){
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);

            mapper.addUser(user);
            sqlSession.commit();
            return true;
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return false;
    }

    /**
     * 更新密码
     * @param user
     */
    public boolean updatePass(User user){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.updateUserPass(user);
            System.out.println(i);
            sqlSession.commit();
            return  true;
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
        return false;
    }
    public boolean updateRole(User user){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.updateUserRole(user);
            System.out.println(i);
            sqlSession.commit();
            return  true;
        }catch (Exception e){
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
        return false;
    }
    /**
     * 删除
     * @param
     */
    public boolean delete(int id){
        SqlSession sqlSession=null;
        try{
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.deleteUser(id);
            System.out.println(i);
            sqlSession.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
        return false;
    }
    //index=(page-1)*4
    public List<User> findPage(int index){
        List<User>list;
        SqlSession sqlSession=SqlSessionFactoryUtil.openSqlSession();
        Mapper mapper=sqlSession.getMapper(Mapper.class);
        list=mapper.findPageUser(index);
        return list;
    }
    public static void main(String ars[]){
        UserService userService=new UserService();
        //User user=new User("pc","1pc1",1);
        System.out.println(userService.delete(7));
        List<User> list=userService.findPage(4);
        System.out.println(list);
    }
}
