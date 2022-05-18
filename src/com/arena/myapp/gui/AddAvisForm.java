/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.services.AvisService;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DeathKnight
 */
public class AddAvisForm extends BaseForm{

         public AddAvisForm (Resources res, Jeux j) {
       
          super(BoxLayout.y());

            if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
              
        setTitle("Ajouter une Avis");
        setLayout(BoxLayout.y());
        TextField tfCommentaire = new TextField("","Comment...");
         tfCommentaire.setUIID("TextFieldBlack"); 
        ComboBox<Map<Integer,Integer>> cbRating = new ComboBox<>(
               createListEntry(1,1),
                createListEntry(2,2),
                createListEntry(3,3),
                createListEntry(4,4),
                createListEntry(5,5)
        );
        AvisService as = new AvisService();
 
        Container c1 = new Container();
        
        SpanLabel nomJeux = new SpanLabel("Nom Jeux: " + j.getNomjeux());
        Button rate = new Button("Rate");
         c1.addAll(nomJeux,tfCommentaire,cbRating,rate);
             System.out.println(j.getIdjeux()+" | "+ j.getNomjeux());
             add(c1);
             rate.addActionListener(e -> {
                 
                int score = cbRating.getModel().getSelectedIndex() +1;
                String commentaire =tfCommentaire.getText();
                 int idJeux = j.getIdjeux();
                 System.out.println(score);
                 
                 int iduser = Session.getInstance().getLoggedInUser().getId();
                 Avis a = new Avis(score,commentaire,iduser,idJeux);
                 as.ajoutAvis(a);
             });
}
        
        
         
         
   private Map<Integer, Integer> createListEntry(int name, int date) {
    Map<Integer, Integer> entry = new HashMap<>();
    entry.put(name, date);

    return entry;
    }
}
