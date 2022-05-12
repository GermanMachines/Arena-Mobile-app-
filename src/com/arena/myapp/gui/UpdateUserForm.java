/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.User;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.UserService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class UpdateUserForm extends BaseForm {
    Form current;
     UpdateUserForm(Resources res,User a) {
            super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        tb.setTitle("Liste des equipe");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
         
        TextField tfNom = new TextField(a.getNom(),"nom",20,TextField.ANY);
         tfNom.setUIID("TextFieldBlack"); 
        
        TextField tfsurnom = new TextField(a.getSurnom(),"surnom",20,TextField.ANY);
         tfsurnom.setUIID("TextFieldBlack");
        TextField tfemail = new TextField(a.getEmail(),"email",20,TextField.ANY);
         tfemail.setUIID("TextFieldBlack");
         TextField tfimage = new TextField(a.getImage(),"image",20,TextField.ANY);
         tfimage.setUIID("TextFieldBlack");
         TextField tfmdp = new TextField(a.getMdp(),"mot de passe",20,TextField.ANY);
         tfmdp.setUIID("TextFieldBlack");
          TextField tftelephone = new TextField(a.getTelephone(),"telephone",20,TextField.ANY);
         tftelephone.setUIID("TextFieldBlack");
         TextField tfblock = new TextField(a.getBlock(),"block",20,TextField.ANY);
         tfblock.setUIID("TextFieldBlack");
            TextField tfrole = new TextField(a.getRole(),"role",20,TextField.ANY);
         tfrole.setUIID("TextFieldBlack");
         TextField tfusername = new TextField(a.getSurnom(),"username",20,TextField.ANY);
         tfusername.setUIID("TextFieldBlack");
            TextField tfroles = new TextField(a.getRoles(),"roles",20,TextField.ANY);
         tfroles.setUIID("TextFieldBlack");
        
      
     
          
        
        
      
        
         tfNom.setSingleLineTextArea(true);
        tfsurnom.setSingleLineTextArea(true);
  tfimage.setSingleLineTextArea(true);
   tfemail.setSingleLineTextArea(true);
      tfmdp.setSingleLineTextArea(true);
   tftelephone.setSingleLineTextArea(true);
   tfrole.setSingleLineTextArea(true);
   tfblock.setSingleLineTextArea(true);
      tfroles.setSingleLineTextArea(true);
         tfusername.setSingleLineTextArea(true);



   
  
    
    
        Button btnValider = new Button("Modifier user");
        btnValider.setUIID("Button");
        btnValider.addPointerPressedListener(e -> {
        
            a.setNom(tfNom.getText());
            a.setSurnom(tfsurnom.getText());
           
            a.setImage(tfimage.getText());
             a.setEmail(tfemail.getText());
                          a.setMdp(tfmdp.getText());
             a.setTelephone(tftelephone.getText());
             a.setRole(tfrole.getText());
             a.setBlock(tfblock.getText());
             a.setRoles(tfroles.getText());
                          a.setUsername(tfusername.getText());



             
             if( UserService.getInstance().modifier(a)){
                               
                      
                      new listUserForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("user modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new listUserForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new listUserForm(res,a).show();
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
       addAll(tfNom,tfsurnom,tfimage,tfemail,tfmdp,tftelephone,tfrole,tfblock, tfroles, tfusername, btnValider,btnAnnuler);
       show();
     }
    
}
