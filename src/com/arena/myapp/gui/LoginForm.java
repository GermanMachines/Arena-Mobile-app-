/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.User;
import com.arena.myapp.services.UserService;
import com.arena.myapp.utils.Session;
import java.util.ArrayList;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
//import com.mycompany.entites.User;
//import com.mycompany.service.UserService;
//import com.mycompany.utils.Session;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class LoginForm  extends BaseForm {
    
    ArrayList<User> ls = new ArrayList<>();
    Boolean auth = false;
  Session ss = new Session().getInstance();

     public LoginForm()
    {
        this(Resources.getGlobalResources());
    }
     
    public LoginForm(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
         getContentPane().setScrollVisible(true);
         
         
        super.addSideMenu(res);

        
        
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
             //   new Label("Welcome, ", "WelcomeWhite"),
             //   new Label("To ETSports", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");      
        TextField login = new TextField("", "Login") ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
       FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService serv = new UserService();
                User u = new User();
            //    u = serv.getuser(password.getText(), login.getText()) ;
           ArrayList<User> names = serv.getuser(password.getText(),login.getText());
                System.out.println("**********************");
                System.out.println(names);

               // auth = serv.authentification(login.getText(), password.getText());
                if ((names.get(0).getMdp().equals(password.getText())) && (names.get(0).getUsername().equals(login.getText()))) {
                    System.out.println("saaaa7iiiit");
                    Dialog.show("Welcome", "good", "ok", null);
                                        ss.setLoggedInUser(names.get(0));
                                        
                                        System.out.println(ss.getLoggedInUser().toString());
                                        new listUserForm(res,names.get(0)).show();
                                        
                             
                } else {       
                    Dialog.show("non", "Please verify Your Information", "ok", null);

                    System.out.println("nnn");
                  //   String c = u.getNom();
                   // System.out.println(c);
                  //  System.out.println(login.getText());
                  //  u = serv.fetch(login.getText());
                  //  ss.setLoggedInUser(u);
                  //  System.out.println(ss.getLoggedInUser().toString());
                //    new ProfileForm(resourceObjectInstance, ss).show();

                }
            }

        });
         
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
        //    new SignUpForm(resourceObjectInstance).show();
            Toolbar.setGlobalToolbar(true);
        });
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
