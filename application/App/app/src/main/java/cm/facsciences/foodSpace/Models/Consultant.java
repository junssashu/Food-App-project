package cm.facsciences.foodSpace.Models;

public class Consultant extends GeneralObject {
    private int age;
    private String tel;
    private String email;
    private String login;
    private String password;

    public int getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void apreciate (Foods food){
        // code
    }

    public Foods research (Foods food){
        //code
        return food;
    }
}
