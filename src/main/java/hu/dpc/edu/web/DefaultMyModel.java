package hu.dpc.edu.web;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by vrg on 24/10/16.
 */
@Service
public class DefaultMyModel implements MyModel {

    private String message;
    private AtomicLong counter = new AtomicLong();
    private Map<Long,User> userMap = new HashMap<>();

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<User> getUsers() {
        return userMap.values()
                .stream()
                .map(user -> user.clone())
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(long id) {
        final User user = userMap.get(id);
        if (user != null) {
            return user.clone();
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        final User managedUser = userMap.get(user.getId());
        if (managedUser == null) {
            throw new IllegalArgumentException("User with the given id does not exist.");
        }
        managedUser.setFirstName(user.getFirstName());
        managedUser.setLastName(user.getLastName());
    }

    @Override
    public long addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        final long id = counter.incrementAndGet();
        final User managedUser = user.clone();
        managedUser.setId(id);
        userMap.put(id, managedUser);
        return id;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
