package junit;

/**
 * Created by a.nigam on 14/12/16.
 */
public class User {
    private final String name;

    public User(String username) {
        name = username;
    }

    public String configFileName() {
        return name;
    }
}
