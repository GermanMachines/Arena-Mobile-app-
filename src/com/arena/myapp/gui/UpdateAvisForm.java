/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.AvisService;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DeathKnight
 */
public class UpdateAvisForm extends BaseForm {
         Form current;
     UpdateAvisForm(Resources res,Avis a) {
        super(BoxLayout.y());
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
        ComboBox<Map<Integer,Integer>> cbRating = new ComboBox<>(
               createListEntry(1,1),
                createListEntry(2,2),
                createListEntry(3,3),
                createListEntry(4,4),
                createListEntry(5,5)
        );
        
        SpanLabel jeux = new SpanLabel("Nom Jeux : " + a.getNomJeux());
        cbRating.getModel().setSelectedIndex(a.getScore());
        TextField tfCommentaire = new TextField(a.getCommentaire(),"Comment...");
        Button btnValider = new Button("Save");
        
        Container c1 = new Container();
        
      //  c1.addAll(jeux,cbRating,tfCommentaire,btnValider);
      //  add(c1);
        
        

        btnValider.setUIID("Button");

        btnValider.addPointerPressedListener(e -> {
            a.setNomJeux(a.getNomJeux());
            a.setCommentaire(tfCommentaire.getText());

            int idx = cbRating.getModel().getSelectedIndex();
            
            System.out.println(idx);
             a.setScore(idx);
             
             if( AvisService.getInstance().modifierAvis(a)){
                               
                      
                      new ListAvisForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Avis modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListAvisForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListAvisForm(res,a).show();
       });
       
   
      
     /*   Container content = BoxLayout.encloseY(
        11,12,
                new FloatingHint(tfNom),
             //   createLineSeparator(),
                 new FloatingHint(tfPrenom),
             //   createLineSeparator(),
                 new FloatingHint(tfdate),
            //    createLineSeparator(),
                 new FloatingHint(tfnum),
            //    createLineSeparator(),
                 new FloatingHint(tfsexe),
            //    createLineSeparator(),
                 new FloatingHint(tfemail),
            //    createLineSeparator(),
                 new FloatingHint(tfpassword),
             //   createLineSeparator(),
                
        btnValider,
        btnAnnuler
                
                
        );
            */  
       addAll(jeux,tfCommentaire,cbRating,btnValider,btnAnnuler);
       show();
     }
        
        
       
     
private Map<Integer, Integer> createListEntry(int name, int date) {
    Map<Integer, Integer> entry = new HashMap<>();
    entry.put(name, date);

    return entry;
    }
}
