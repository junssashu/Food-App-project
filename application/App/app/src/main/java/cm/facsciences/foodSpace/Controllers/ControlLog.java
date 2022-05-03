package cm.facsciences.foodSpace.Controllers;

import android.content.Context;

import org.json.JSONArray;

import cm.facsciences.foodSpace.MainActivity;
import cm.facsciences.foodSpace.Models.Consultant;

public final class ControlLog {
    private static ControlLog instance =null;
    private static Consultant person;
    private static AccessLoginBD access;
    private static Context contexte;

    private ControlLog(){
        super();
    }

    /**
     * création de l'instance
     * @return l'instance
     */
    public static final ControlLog getInstance(Context contexte){
        if (contexte != null){
            ControlLog.contexte=contexte;
        }
        if (ControlLog.instance == null){
            ControlLog.instance= new ControlLog();
             access = new AccessLoginBD();
             access.envoi("dernier", new JSONArray());
        }
        return ControlLog.instance;
    }

    /**
     *
     * @param name name of a user
     * @param age  age of a user
     * @param tel  PhoneNumber of a user
     * @param email Email of a user
     * @param login Login of a user
     * @param password  password of a user
     * @param sex   sex 1 pour homme et 0 pour femme
     */

    public void CreatePerson(int id, String name, int age, String tel, String email, String login, String password, Integer sex ){
        person = new Consultant (id, name,age,tel,email,login,password,sex);
        access.envoi("enreg", person.convertToJsonArray());
    }

    public void Setperson (Consultant person){
        ControlLog.person = person;
        ((MainActivity)contexte).recupPerson();
    }
}
