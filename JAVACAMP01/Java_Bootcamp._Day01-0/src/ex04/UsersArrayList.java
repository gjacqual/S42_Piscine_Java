package ex04;

public class UsersArrayList implements UsersList{
    private User[] array = new User[10];
    private Integer numberOfUsers = 0;

    private Integer arraySize = 10;

    @Override
    public void addUser(User user) {
        if (numberOfUsers >= arraySize) {
            increaseArrayByHalf();
        }
        array[numberOfUsers] = user;
        numberOfUsers++;
    }

    @Override
    public User getUserById(Integer identifier) throws UserNotFoundException {
        for (int i = 0; i < numberOfUsers; i++) {
            if (array[i].getIdentifier().equals(identifier)) {
                return array[i];
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User getUserByIndex(Integer index) throws UserNotFoundException {
        if (index < numberOfUsers && index >= 0) {
            return array[index];
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    private void increaseArrayByHalf() {
        int newSize = arraySize + (arraySize / 2);
        User[] newArray = new User[newSize];
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
        this.arraySize = newSize;
    }

    public Integer getArraySize() {
        return arraySize;
    }
}
