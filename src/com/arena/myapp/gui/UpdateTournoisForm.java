/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Tournois;
import com.arena.myapp.services.JeuxService;
import com.arena.myapp.services.TournoisService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author tarek
 */
public class UpdateTournoisForm extends BaseForm{
       Form current;
     UpdateTournoisForm(Resources res,Tournois a) {
            super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        tb.setTitle("Liste des equipe");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
         
        TextField Titre = new TextField(a.getTitre(),"Titre",20,TextField.ANY);
         Titre.setUIID("TextFieldBlack"); 
        
        TextField DateDebut = new TextField(a.getDateDebut(),"DateDebut",20,TextField.ANY);
         DateDebut.setUIID("TextFieldBlack");
        
           TextField DateFin = new TextField(a.getDateFin(),"DateFin",20,TextField.ANY);
         DateFin.setUIID("TextFieldBlack"); 
        
        TextField Descriptiontournois = new TextField(a.getDescriptiontournois(),"Descriptiontournois",20,TextField.ANY);
         Descriptiontournois.setUIID("TextFieldBlack");
         
           TextField Type = new TextField(a.getType(),"Type",20,TextField.ANY);
         Type.setUIID("TextFieldBlack"); 
        

         
           TextField Nbrparticipants = new TextField(String.valueOf(a.getNbrparticipants()),"Nbrparticipants",20,TextField.ANY);
         Nbrparticipants.setUIID("TextFieldBlack");
         
           TextField Winner = new TextField(a.getWinner(),"Winner",20,TextField.ANY);
         Winner.setUIID("TextFieldBlack"); 
        
                    TextField Status = new TextField(a.getStatus(),"Status",20,TextField.ANY);
         Status.setUIID("TextFieldBlack"); 
         
        TextField Idjeux = new TextField(String.valueOf(a.getIdjeux()),"Idjeux",20,TextField.ANY);
         Idjeux.setUIID("TextFieldBlack");
      
          
        
         Titre.setSingleLineTextArea(true);
        DateDebut.setSingleLineTextArea(true);
         DateFin.setSingleLineTextArea(true);
        Descriptiontournois.setSingleLineTextArea(true);
        Type.setSingleLineTextArea(true);
        Nbrparticipants.setSingleLineTextArea(true);
        Winner.setSingleLineTextArea(true);
        Status.setSingleLineTextArea(true);
        Idjeux.setSingleLineTextArea(true);

  
    
    
        Button btnValider = new Button("Modifier Tournois");
        btnValider.setUIID("Button");
        btnValider.addPointerPressedListener(e -> {
        
            a.setTitre(Titre.getText());
            a.setDateDebut(DateDebut.getText());
            a.setDateFin(DateFin.getText());
            a.setDescriptiontournois(Descriptiontournois.getText());
            a.setType(Type.getText());
            a.setNbrparticipants(Integer.parseInt(Nbrparticipants.getText()));

            a.setWinner(Winner.getText());
            a.setStatus(Status.getText());
            
            a.setIdjeux(Integer.parseInt(Idjeux.getText()));
            
            
            
             if( TournoisService.getInstance().modifier(a)){
                               
                      
                      new ListTournoisForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Tournois modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListTournoisForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListTournoisForm(res,a).show();
       });
       
      Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
        Label l6 = new Label("");
         Label l7 = new Label("");
          Label l8 = new Label("");
     
       
        Label l1 = new Label();

   
       addAll(Titre,DateDebut, DateFin ,Descriptiontournois,Type,Nbrparticipants, Winner,Status,Idjeux,btnValider,btnAnnuler);
       show();
     }
}
