package mapper;

import pojo.Role;
import pojo.User;
import pojo.UserInfo;

import java.util.List;

public interface Mapper {
    /**
     *
     * @param user
     * @return
     */
    public User findUser(User user);
    public User checkUser(User user);
    public void addUser(User user);
    public  int updateUserPass(User user);
    public  int updateUserRole(User user);
    public  int deleteUser(int id);
    public List<User> findPageUser(int index);

    /**
     *
     * @param role
     */
    public void addRole(Role role);
    public  int updateRole(Role role);
    public  int deleteRole(int id);
    public Role findRole(int id);
    public List<Role> findPageRole(int index);

    /**
     *
     * @param userInfo
     */
    public void addUserInfo(UserInfo userInfo);
    public  int updateUserInfo(UserInfo userInfo);
    public UserInfo findUserInfo(int id);
    public List<UserInfo> findPageUserInfo(int index);

}
