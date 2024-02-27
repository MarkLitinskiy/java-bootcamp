package ex05;

public interface UsersList {
    void addUser(String name, double balance);
    User retrieveUserByID(int id);
    User retrieveUserByIndex(int index);
    int retrieveNumberOfUsers();
}
