package sample;

public class User {

    private int id ;
    private String login;
    private String password;
    private String action = null;
    private Boolean Image = false;
    public Boolean getImage() {
        return Image;
    }

    public void setImage(Boolean image) {
        Image = image;
    }


    public void setAction(String action) {
        this.action = action;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

