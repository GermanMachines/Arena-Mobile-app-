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
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class ListAvisForm extends BaseForm{
    public ListAvisForm(Resources res, Avis c){
      super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        final int ADMIN = 0;
        setToolbar(tb);
        tb.setTitle("Liste des Avis");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        System.out.println(Session.getInstance().getLoggedInUser().getId());
           //  System.out.println(ss.getLoggedInUser().toString());

       

        AvisService as = new AvisService();
        ArrayList<Avis> list = as.affichageAvisFront();
        
         if (Session.getInstance().getLoggedInUser().getId() != ADMIN) {
             list = as.affichageAvisFront();
        }else{
            list = as.affichageAvis();
        }
         
         {

            for (Avis a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Nom Jeux :" + a.getNomJeux());
                SpanLabel cat1 = new SpanLabel("Score :" + a.getScore());
                SpanLabel cat2 = new SpanLabel("Commentaire :" + a.getCommentaire());

             
 
                c3.add(cat);
                c3.add(cat1);
                c3.add(cat2);
 
    Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette Avis?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.deleteAvis(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Avis SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListAvisForm(res,a).show();
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
                    new UpdateAvisForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");
                add(c3);
               
            }
            

        }

    }

    
}