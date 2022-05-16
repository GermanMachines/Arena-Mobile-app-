/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Commentaire;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Post;
import com.arena.myapp.services.AvisService;
import com.arena.myapp.services.CommentaireService;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DeathKnight
 */
public class AddCommentaireForm extends BaseForm{
         public AddCommentaireForm (Resources res, Post p) {
       
          super(BoxLayout.y());

        super.addSideMenu(res);
              
        setTitle("Ajouter une Commentaire");
        setLayout(BoxLayout.y());
        TextField tfdesc_com = new TextField("","Comment...");
        TextField tfdatecom = new TextField("","Comment...");

        

        CommentaireService as = new CommentaireService();
 
        Container c1 = new Container();
        
        SpanLabel nompost = new SpanLabel("titre Post: " +p.getTitre());
     
        Button Comment = new Button("Commentaire");
         c1.addAll(nompost,tfdesc_com,Comment);
             System.out.println(p.getId_post()+" | "+ p.getTitre());
             add(c1);
             Comment.addActionListener(e -> {
                 
                String Desc_com =tfdesc_com.getText();
                String commentaire =tfdatecom.getText();
                 int idpost = p.getId_post();
                 
                int iduser = Session.getInstance().getLoggedInUser().getId();
                 Commentaire a = new Commentaire(iduser,Desc_com,commentaire,idpost);
                 as.ajoutCommentaire(a);
             });
}
        
        
         
         
   private Map<Integer, Integer> createListEntry(int name, int date) {
    Map<Integer, Integer> entry = new HashMap<>();
    entry.put(name, date);

    return entry;
    }
}
