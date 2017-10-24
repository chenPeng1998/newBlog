package service;

import mapper.Mapper;
import org.apache.ibatis.session.SqlSession;
import pojo.UserInfo;
import util.SqlSessionFactoryUtil;

import java.util.List;

public class UserInfoService {
    /**
     * 添加
     */
    public boolean addUserInfo(UserInfo userInfo){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);

            mapper.addUserInfo(userInfo);
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
     * 查询
     */
    public  boolean findUserInfo(int id){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            UserInfo u=mapper.findUserInfo(id);
            if(u!=null){
                System.out.println(u);
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
     * 修改
     * 要先查出已有的数据 再改要改的数据
     */
    public boolean updateUserInfo( UserInfo userInfo ){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.updateUserInfo(userInfo);
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
     */

    public List<UserInfo> findPage(int index){
        List<UserInfo>list;
        SqlSession sqlSession=SqlSessionFactoryUtil.openSqlSession();
        Mapper mapper=sqlSession.getMapper(Mapper.class);
        list=mapper.findPageUserInfo(index);
        return list;
    }

    public static void main(String ars[]){
        UserInfoService userInfoService=new UserInfoService();
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(1);
        userInfo.setEmail("1@u.com");
        userInfo.setSex("male");
        System.out.println(userInfoService.findUserInfo(1));
        userInfoService.updateUserInfo(userInfo);
    }

}
