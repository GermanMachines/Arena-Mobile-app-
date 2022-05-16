/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Post;

import com.arena.myapp.services.JeuxService;
import com.arena.myapp.services.ServicePost;

import com.codename1.components.ToastBar;

import com.codename1.ui.Button;
import com.codename1.ui.Command;

import com.codename1.ui.Dialog;

import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author LENOVO
 */
public class AjoutPostForm extends BaseForm {
    
    
    
Resources theme = UIManager.initFirstTheme("/theme");

    
    
       public AjoutPostForm (Resources res) {
        
          super(BoxLayout.y());

        super.addSideMenu(res);
        
       
        
      
        setTitle("Ajouter un POST ");
        setLayout(BoxLayout.y());
        

         
          TextField titre = new TextField("", "entrer titre!!");
        titre.setUIID("TextFieldBlack");
        
        TextField auteur = new TextField("", "entrer auteur!!");
        auteur.setUIID("TextFieldBlack");
        
        TextField imageP = new TextField("", "entrer image!!");
        imageP.setUIID("TextFieldBlack");
        
        TextField DAte = new TextField("", "entrer date!!");
        DAte.setUIID("TextFieldBlack");
        
        TextField rate = new TextField("", "entrer rate!!");
        rate.setUIID("TextFieldBlack");
        

        Button btnValider = new Button("Ajouter Post");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if(titre.getText().equals("") || auteur.getText().equals("")) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                 }
             else
                {
             
                      

              Post c = new Post(String.valueOf(titre.getText()).toString(),String.valueOf(auteur.getText()).toString(),String.valueOf(imageP.getText()).toString(),String.valueOf(DAte.getText()).toString(),Integer.valueOf( rate.getText()) );

                        if( ServicePost.getInstance().ajoutPost(c)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("Jeux ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    }
                    
                }
                
        });    
        
   

        addAll(titre , auteur, imageP,DAte,rate,btnValider);
        
        
            
   
       
       
        
     }
       
       

       
       
}
