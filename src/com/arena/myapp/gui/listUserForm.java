/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

/**
 *
 * @author LENOVO
 */



import com.arena.myapp.entities.User;
import com.arena.myapp.services.UserService;
import com.arena.myapp.utils.Session;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import java.util.ArrayList;


public class listUserForm extends BaseForm {
    
    public listUserForm(Resources res, User c) {

        super(BoxLayout.y());
       // Toolbar tb = new Toolbar(true);
      //  setToolbar(tb);
       // tb.setTitle("Liste des Client");
        getContentPane().setScrollVisible(true);
        
        
         if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
        
      //  Button btnAddTask1 = new Button("Statistiques");

        //btnAddTask1.addActionListener(e -> new statistiques1().createPieChartForm().show());

        //addAll(btnAddTask1);
        UserService as = new UserService();
        ArrayList<User> list = as.AfficherJeux();

        {

            for (User a : list) {

                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Nom :" + a.getNom());
                SpanLabel cat1 = new SpanLabel("prenom :" + a.getSurnom());
                SpanLabel cat2 = new SpanLabel("username :" + a.getSurnom());
                SpanLabel cat3 = new SpanLabel("num :" + a.getEmail());
                SpanLabel cat4 = new SpanLabel("sexe :" + a.getImage());
                SpanLabel cat5 = new SpanLabel("email :" + a.getTelephone());
                SpanLabel cat6 = new SpanLabel("password :" + a.getMdp());
                SpanLabel cat7 = new SpanLabel("role :" + a.getRole());
                SpanLabel cat8 = new SpanLabel("roles :" + a.getRoles());
                SpanLabel cat9 = new SpanLabel("username :" + a.getUsername());
                SpanLabel cat10 = new SpanLabel("telephone :" + a.getTelephone());
                SpanLabel cat11 = new SpanLabel("id_equipe :" + a.getIdEquipe());


                

                
                

                c3.add(cat);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
                c3.add(cat5);
                c3.add(cat6);
                c3.add(cat7);
                c3.add(cat8);
                c3.add(cat9);
                c3.add(cat10);
                c3.add(cat11);
if (c.getUsername().equals(a.getUsername())) {

                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce user?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.Delete1(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("client SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new listUserForm(res,a).show();
                        }

                    }
                    );

        
                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                });
               Button Modifier = new Button("Modifier ");
                    c3.add(Modifier);
                     Modifier.getAllStyles().setBgColor(0xF36B08);
              Modifier.addActionListener(e -> {
                   new UpdateUserForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");

                add(c3);

            }
            }}

        }

    }

   
//}

