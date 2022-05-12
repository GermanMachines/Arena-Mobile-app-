/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Jeux;
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
public class AvisForm extends BaseForm{
         public AvisForm (Resources res, Jeux j) {
       
          super(BoxLayout.y());

        super.addSideMenu(res);
              
        setTitle("Ajouter une equipe");
        setLayout(BoxLayout.y());
        TextField tfCommentaire = new TextField("","Comment...");
        ComboBox<Map<Integer,Integer>> cbRating = new ComboBox<>(
               createListEntry(1,1),
                createListEntry(2,2),
                createListEntry(3,3),
                createListEntry(4,4),
                createListEntry(5,5)
        );
 
        Container c1 = new Container();
         c1.addAll(tfCommentaire,cbRating);
             System.out.println(j.getIdjeux()+" | "+ j.getNomjeux());
             add(c1);
}
        
        
         
         
   private Map<Integer, Integer> createListEntry(int name, int date) {
    Map<Integer, Integer> entry = new HashMap<>();
    entry.put(name, date);

    return entry;
}
}
