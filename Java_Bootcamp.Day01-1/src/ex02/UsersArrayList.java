package ex02;

public class UsersArrayList implements UsersList{
    private User[] usersList = new User[10];
    private int capacity = 0;

    User getUsersList(int index){
        return usersList[index];
    }

    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }

    void resize(){
        User[] newUsersList = new User[usersList.length * 2];
        for (int i = 0; i < usersList.length; i++){
            newUsersList[i] = usersList[i];
        }
        usersList = newUsersList;
    }

    public void addUser(String name){
        User newUser = new User(name, 0);
        usersList[capacity++] = newUser;
        if (capacity == usersList.length)
            resize();
    }

    public void addUser(User newUser){
        usersList[capacity++] = newUser;
        if (capacity == usersList.length)
            resize();
    }
    public User retrieveUserByID(int id) {
        int foundIndex = -1;
        for (int i = 0; i < capacity; i++){
            if (usersList[i].getId() == id){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            System.out.println("ID не существует!");
            throw new UserNotFoundException("ID не существует!");
        }
        return usersList[foundIndex];
    }
    public User retrieveUserByIndex(int index){
        if (index < 0 || index > capacity - 1 ) {
            System.out.println("Index не существует!");
            throw new UserNotFoundException("Index не существует!");
        }
        return usersList[index];
    }
    public int retrieveNumberOfUsers(){
        return capacity;
    }

}
