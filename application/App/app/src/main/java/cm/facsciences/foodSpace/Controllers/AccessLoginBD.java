package cm.facsciences.foodSpace.Controllers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cm.facsciences.foodSpace.Models.Consultant;
import cm.facsciences.foodSpace.Tools.AccessLoginBdHTTP;
import cm.facsciences.foodSpace.Tools.AsyncResponse;

public class AccessLoginBD implements AsyncResponse {

    private static final String SERVERADDR = "http://192.168.0.18/LoginDatafood/serveurConnexion.php ";
    private ControlLog controle;

    public  AccessLoginBD(){
        controle = controle.getInstance(null);
    }

    /**
     * Retour du serveur distant
     * @param output
     */
    @Override
    public void processing(String output) {
        Log.d("serveur", "*******************"+output);
        // découpage de message reçu
        String[] message = output.split("%");
        // dans le message[0] : "enreg", "dernier","Erreur !"
        // dans le message[1]: le reste du message

        // verification de la présence des 2 cases

        if (message.length>1){
            if (message[0].equals("enreg")){
                Log.d("enregistrement", "*******************"+message[1]);
            }else{
                if (message[0].equals("dernier")){
                    Log.d("enregistrement", "*******************"+message[1]);
                    try {
                        JSONObject info = new JSONObject(message[1]);
                        // Recupération des données utiles
                          Integer id = info.getInt("id");
                          String name = info.getString("name");
                          Integer age = info.getInt("age");
                          String tel = info.getString("tel");
                          String email = info.getString("email");
                          String login = info.getString("login");
                          String pwd = info.getString("pwd");
                          Integer sex = info.getInt("sex");
                        Consultant person = new Consultant(id,name,age,tel,email,login,pwd,sex);
                        controle.Setperson(person);

                    } catch (JSONException e) {
                        Log.d("enregistrement", "conversion JSON impossible*******************"+e.toString());                      }
                }else{
                    if(message[0].equals("Erreur !"))
                        Log.d("enregistrement", "*******************"+message[1]);
                }
            }
        }

    }

    public  void envoi(String operation, JSONArray dataJson){
        AccessLoginBdHTTP accesDonnees = new AccessLoginBdHTTP();
        // lien de delegation
        accesDonnees.delegate = this;
        //ajout des paramètres
        accesDonnees.addparam("operation", operation);
        accesDonnees.addparam("mdata", dataJson.toString());
        //appel du serveur
        accesDonnees.execute(SERVERADDR);
    }
}
