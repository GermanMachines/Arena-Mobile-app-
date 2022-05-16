/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Commentaire;

import com.arena.myapp.services.CommentaireService;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author DeathKnight
 */
public class ListCommentaireForm extends BaseForm{
    public ListCommentaireForm(Resources res, Commentaire c){
      super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des Commentaire");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        System.out.println(Session.getInstance().getLoggedInUser().getId());


     CommentaireService as = new CommentaireService();
        ArrayList<Commentaire> list = as.affichageCommentaireFront();
         {

            for (Commentaire a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Titre Post :" + a.getTitre());
                SpanLabel cat1 = new SpanLabel("Des_com :" + a.getDesc_com());
                SpanLabel cat2 = new SpanLabel("DateCom :" + a.getDate_com());

             
 
                c3.add(cat);
                c3.add(cat1);
                c3.add(cat2);
 
    Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette commentaire?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.deleteCommentaire(a.getId_com());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            status.setMessage("Commentaire SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListCommentaireForm(res,a).show();
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
                    //new UpdateAvisForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");
                add(c3);
               
            }
            

        }

    }

    
}