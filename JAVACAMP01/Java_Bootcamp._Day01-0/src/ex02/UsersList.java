package ex02;

public interface UsersList {
    void addUser(User user);
    User getUserById(Integer identifier) throws UserNotFoundException;
    User getUserByIndex(Integer index) throws UserNotFoundException;
    Integer getNumberOfUsers();
}
