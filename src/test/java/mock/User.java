package mock;

import java.util.Objects;

public class User {

    private String username;
    private Long id;

    public User(String username, Long id){
        this.username = username;
        this.id = id;
    }

    public User(Long id){
        this.id = id;
    }

    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, id);
    }
}
