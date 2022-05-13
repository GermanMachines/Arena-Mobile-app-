/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.JeuxService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class UpdateJeuxForm extends BaseForm {
      Form current;
     UpdateJeuxForm(Resources res,Jeux a) {
            super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        tb.setTitle("Liste des equipe");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
         
        TextField tfNom = new TextField(a.getNomjeux(),"nom",20,TextField.ANY);
         tfNom.setUIID("TextFieldBlack"); 
        
        TextField tflogo = new TextField(a.getImagejeux(),"Image",20,TextField.ANY);
         tflogo.setUIID("TextFieldBlack");
        
      
          
        
         tfNom.setSingleLineTextArea(true);
        tflogo.setSingleLineTextArea(true);
 
   
  
    
    
        Button btnValider = new Button("Modifier Jeux");
        btnValider.setUIID("Button");
        btnValider.addPointerPressedListener(e -> {
        
            a.setNomjeux(tfNom.getText());
            a.setImagejeux(tflogo.getText());
           
    
             if( JeuxService.getInstance().modifier(a)){
                               
                      
                      new ListJeuxForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Jeux modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListJeuxForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListJeuxForm(res,a).show();
       });
       
      Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
        Label l6 = new Label("");
         Label l7 = new Label("");
          Label l8 = new Label("");
     
       
        Label l1 = new Label();
      
   
       addAll(tfNom,tflogo, btnValider,btnAnnuler);
       show();
     }
}

