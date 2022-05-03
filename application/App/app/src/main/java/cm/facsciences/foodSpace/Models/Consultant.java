package cm.facsciences.foodSpace.Models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Consultant extends GeneralObject {
    private int id;
    private int age;
    private String tel;
    private String email;
    private String login;
    private String password;
    private int sex;
    private Consultant person;


    public Consultant(int id, String name, int age, String tel, String email, String login, String password, Integer sex) {
        super(name);
        this.id=id;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.login = login;
        this.password = password;
        this.sex=sex;
    }



    public int getId() { return id; }

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

    public int getSex() { return sex; }

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

    public void setSex(int sex) { this.sex = sex; }

    public void setId(int id) { this.id = id; }

    public  HealthData majHealthData (){
        // code
        return null;
    }

    public HealthData addHealthData (List<String> listOfDisease, List<Disease> disease){
        //code
        return null;
    }
    public boolean delHealthData(){
        //code
        return false;
    }

    /**
     * transforme toutes les données en JSON ARRAY
     * @return LIST
     */
    public JSONArray convertToJsonArray(){

        person = new Consultant(id,getName(),age,tel, email, login,password,sex);

        List laliste = new ArrayList();
        laliste.add(person);

        return new JSONArray(laliste);
    }
}
