/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Tournois;
import com.arena.myapp.entities.User;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.JeuxService;
import com.arena.myapp.services.TournoisService;
import com.arena.myapp.services.UserService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static java.lang.Integer.parseInt;

/**
 *
 * @author LENOVO
 */
public class AddTournoisForm extends BaseForm {

       public AddTournoisForm (Resources res) {
        
          super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      tb.setTitle("Liste des users");
//     getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

      
        setTitle("Ajouter un Tournois ");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Titre ");
         tfNom.setUIID("TextFieldBlack"); 
         
        TextField DateDebut = new TextField("","DateDebut");
         DateDebut.setUIID("TextFieldBlack");
         
             TextField DateFin = new TextField("","DateFin");
         DateFin.setUIID("TextFieldBlack"); 
                  TextField descriptiontournois = new TextField("","descriptiontournois");                 
         descriptiontournois.setUIID("TextFieldBlack"); 
         
                         TextField type = new TextField("","type");                 
         type.setUIID("TextFieldBlack");
         
                      TextField nbrparticipants = new TextField("","nbrparticipants");
         nbrparticipants.setUIID("nbrparticipants"); 
         
                      TextField winner = new TextField("","winner");
         winner.setUIID("winner"); 
                      TextField status = new TextField("","status");
         status.setUIID("TextFieldBlack"); 
         
         
           TextField idjeux = new TextField("","idjeux");
         idjeux.setUIID("TextFieldBlack");
         

      
        
       
      

        Button btnValider = new Button("Ajouter Tournois");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
             
                      
                        Tournois a;
                        a = new Tournois(tfNom.getText() , DateDebut.getText(), DateFin.getText() , descriptiontournois.getText(),type.getText(),parseInt(nbrparticipants.getText()) ,winner.getText() , status.getText() ,parseInt(idjeux.getText())  );
                        
                        if( TournoisService.getInstance().addTournois(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("Tournois ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    }
                    
                }
                
        });    
        
        
        addAll(tfNom , DateDebut , DateFin , descriptiontournois , nbrparticipants ,  winner , status ,idjeux , btnValider);
   
     }
    
}