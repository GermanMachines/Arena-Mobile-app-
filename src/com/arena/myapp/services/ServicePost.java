/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Post;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.arena.myapp.services.ServicePost;
import com.arena.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServicePost {

  public  Boolean resultOK;
    public static ServicePost instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;
  public ArrayList<Post> Post;
    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;

    }

    public ServicePost() {
        req = new ConnectionRequest();
    }

   

  

  
    public boolean ajoutPost(Post post) {

        String url = Statics.BASE_URL + "/post/ajouterpostMobile/new?titre=" + post.getTitre() + "&auteur=" + post.getAuteur() + "&imgPost=" + post.getImg_post()
                + "&datePost=" + post.getDate_post() + "&rate=" + post.getRate();// aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

       req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;

    }

    public ArrayList<Post> AfficherPost() {

        ArrayList<Post> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/post/s/AfficherPostMobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
                    for (Map<String, Object> obj : ListOfMaps) {
                        Post c = new Post();
                        float id = Float.parseFloat(obj.get("idPost").toString());

                        String titre = obj.get("titre").toString();
                        String auteur = obj.get("auteur").toString();
                      String date = obj.get("datePost").toString();
                        String imagepost = obj.get("imgPost").toString();
                        float rate = Float.parseFloat(obj.get("rate").toString());
                        c.setId_post((int) id);
                          c.setTitre(titre);
                        c.setAuteur(auteur);
                       c.setDate_post(date);
                        c.setImg_post(imagepost);
                        c.setRate((int) rate);

                        result.add(c);
//                        System.out.println(c.getId_post() + " " + c.getTitre() + " " + c.getAuteur() + " " + c.getDate_post() + " " + c.getImg_post() + " " + c.getRate());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public boolean modifierPost(Post post) {
        String url = Statics.BASE_URL + "/post/p/updatePostMobile/" + post.getId_post()
                + "&titre=" + post.getTitre()
                + "&auteur=" + post.getAuteur()
                + "&imgPost=" + post.getImg_post()
                + "&datePost=" + post.getDate_post()
                + "&rate=" + post.getRate();
        System.out.println(url);
        req.setPost(false);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

    public boolean deletePost(int idPost) {
        System.out.println(idPost);
        String url = Statics.BASE_URL + "/post/p/deletePostMobile/" + idPost;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
}
