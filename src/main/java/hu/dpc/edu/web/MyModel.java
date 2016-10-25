package hu.dpc.edu.web;

import java.util.List;

/**
 * Created by vrg on 24/10/16.
 */
public interface MyModel {
    public long addUser(User user);
    public void setMessage(String message);
    public String getMessage();
    public List<User> getUsers();
    public User findUserById(long id);
    public void updateUser(User user);
}
