/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.User;
import com.arena.myapp.services.UserService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class AjoutUser extends BaseForm {
     public AjoutUser (Resources res) {
        
          super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      tb.setTitle("Liste des users");
//     getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

      
        setTitle("Ajouter un user");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom user");
         tfNom.setUIID("TextFieldBlack"); 
        TextField tfSurnom = new TextField("","Surnom user");
         tfSurnom.setUIID("TextFieldBlack");
          TextField tfemail = new TextField("","email user");
         tfemail.setUIID("TextFieldBlack"); 
         TextField tfimage = new TextField("","image user");
         tfimage.setUIID("TextFieldBlack"); 
       TextField tftelephone = new TextField("","telephone ");
         tftelephone.setUIID("TextFieldBlack"); 
         TextField tfrole = new TextField("","role ");
         tfrole.setUIID("TextFieldBlack"); 
TextField tfroles = new TextField("","roles ");
         tfroles.setUIID("TextFieldBlack"); 
         TextField tfblock = new TextField("","block ");
         tfblock.setUIID("TextFieldBlack"); 
         

        
        
        TextField tfusername = new TextField("","username");
                 tfusername.setUIID("TextFieldBlack"); 
        TextField tfmdp = new TextField("","mdp");
                 tfmdp.setUIID("TextFieldBlack"); 
        
       
      

        Button btnValider = new Button("Ajouter Client");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfSurnom.getText().length()==0)||(tfimage.getText().length()==0)||(tfemail.getText().length()==0)||(tfmdp.getText().length()==0)||(tftelephone.getText().length()==0)||(tfrole.getText().length()==0)||(tfblock.getText().length()==0)||(tfroles.getText().length()==0)||(tfusername.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
                    try {
                      
                        User a;
                        a = new User( tfNom.getText() , tfSurnom.getText() ,tfimage.getText(), tfemail.getText(),tfmdp.getText(),tftelephone.getText(),tfrole.getText(),tfblock.getText(),tfroles.getText(),tfusername.getText());
                        
                        if( UserService.getInstance().addUser(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Client ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                      new listUserForm(res,a).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });    
        
        
        addAll(tfNom ,tfSurnom, tfimage, tfemail,tfmdp,tftelephone, tfrole,tfblock, tfroles, tfusername, btnValider);
   
     }
    
    
}
