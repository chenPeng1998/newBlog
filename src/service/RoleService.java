package service;

import mapper.Mapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Role;
import util.SqlSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    /**
    * 添加
    */
    public boolean addRoles(Role role){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);

            mapper.addRole(role);
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
    public  boolean findRole(int id){
        SqlSession sqlSession=null;
        try {
            sqlSession= SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            Role u=mapper.findRole(id);
            System.out.println(u);
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
     * 修改
     */
    public boolean updateRole(Role role){
        SqlSession sqlSession=null;
        try {
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.updateRole(role);
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
    public boolean delete(int id){
        SqlSession sqlSession=null;
        try{
            sqlSession=SqlSessionFactoryUtil.openSqlSession();
            Mapper mapper=sqlSession.getMapper(Mapper.class);
            int i=mapper.deleteRole(id);
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
    public List<Role>findPage(int index){
        List<Role>list;
        SqlSession sqlSession=SqlSessionFactoryUtil.openSqlSession();
        Mapper mapper=sqlSession.getMapper(Mapper.class);
        list=mapper.findPageRole(index);
        return list;
    }
    public static void main(String ars[]){
       RoleService service=new RoleService();
      List<Role>list= service.findPage((2-1)*4);
      System.out.println(list);

    }
}
