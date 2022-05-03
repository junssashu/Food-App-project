package cm.facsciences.foodSpace.Tools;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cm.facsciences.foodSpace.Controllers.AccessLoginBD;

public class AccessLoginBdHTTP extends AsyncTask<String, Integer, Long> {

    public AsyncResponse delegate;
    private ArrayList<NameValuePair> parametres;
    private String ret;


    public AccessLoginBdHTTP (){
        parametres=new ArrayList<NameValuePair>();
    }

    /**
     *
     * @param nom
     * @param valeur
     */
    public void addparam (String nom, String valeur){
        parametres.add(new BasicNameValuePair(nom,valeur));
    }
    /**
     * connexion en tâche de fond dans un thread séparé
     * @param strings
     * @return
     */

    @Override
    protected Long doInBackground(String... strings) {
        //objet nécessaire pour faire la connexion
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramcnx = new HttpPost(strings[0]);

        try {
            //encodage des paramètres
            paramcnx.setEntity(new UrlEncodedFormEntity(parametres));
            // connexion et envoie des réponses, attente de répondes
            HttpResponse response = cnxHttp.execute(paramcnx);
            //transformation de la reponse
            ret = EntityUtils.toString(response.getEntity() );
        } catch (UnsupportedEncodingException e) {
            Log.d("erreur encodage", "**********************"+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("erreur encodage", "**********************"+e.toString());
        } catch (IOException e) {
            Log.d("erreur encodage", "**********************"+e.toString());
        }

        return null;
    }

    /**
     * Fonction de délegation
     * @param result
     */
    @Override
    protected void onPostExecute(Long result){
        delegate.processing(ret.toString());

    }
}
