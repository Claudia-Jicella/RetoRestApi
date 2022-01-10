package modelo.singleuser;

@lombok.Data

public class User {
    private Data data;
    private Support support;
    private String email;

    public void setData(Data data) {
        this.data = data;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
    }
}