package ex02;

public interface UsersList {
    void addUser(String name);
    User retrieveUserByID(int id);
    User retrieveUserByIndex(int index);
    int retrieveNumberOfUsers();
}
