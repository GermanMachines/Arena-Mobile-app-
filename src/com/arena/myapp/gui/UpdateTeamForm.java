/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.services.EquipeService;
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
public class UpdateTeamForm extends BaseForm {
      Form current;
     UpdateTeamForm(Resources res,Equipe a) {
            super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        tb.setTitle("Liste des equipe");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
         
        TextField tfNom = new TextField(a.getNom(),"nom",20,TextField.ANY);
         tfNom.setUIID("TextFieldBlack"); 
        
        TextField tflogo = new TextField(a.getLogo(),"logo",20,TextField.ANY);
         tflogo.setUIID("TextFieldBlack");
        
          TextField tfscore = new TextField(String.valueOf(a.getScore()),"score",20,TextField.ANY);
         tfscore.setUIID("TextFieldBlack"); 
        
         TextField tfregion = new TextField(a.getRegion(),"region",20,TextField.ANY);
         tfregion.setUIID("TextFieldBlack");
     
          
        
        
      
        
         tfNom.setSingleLineTextArea(true);
        tflogo.setSingleLineTextArea(true);
  tfscore.setSingleLineTextArea(true);
   tfregion.setSingleLineTextArea(true);
   
  
    
    
        Button btnValider = new Button("Modifier Reclammation");
        btnValider.setUIID("Button");
        btnValider.addPointerPressedListener(e -> {
        
            a.setNom(tfNom.getText());
            a.setLogo(tflogo.getText());
           
            a.setScore(Integer.parseInt(tfscore.getText()));
             a.setRegion(tfregion.getText());
             if( EquipeService.getInstance().modifier(a)){
                               
                      
                      new ListTeamForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("equipe modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListTeamForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListTeamForm(res,a).show();
       });
       
      Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
        Label l6 = new Label("");
         Label l7 = new Label("");
          Label l8 = new Label("");
     
       
        Label l1 = new Label();
      
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
       addAll(tfNom,tflogo,tfscore,tfregion, btnValider,btnAnnuler);
       show();
     }
}

