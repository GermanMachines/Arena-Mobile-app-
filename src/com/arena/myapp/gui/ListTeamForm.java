/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.services.EquipeService;
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
 * @author LENOVO
 */
public class ListTeamForm extends BaseForm {
    
      public ListTeamForm(Resources res,Equipe c ) {

        super(BoxLayout.y());
       // Toolbar tb = new Toolbar(true);
       // setToolbar(tb);
       // tb.setTitle("Liste des equipes");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        
      //  Button btnAddTask1 = new Button("Statistiques");

        //btnAddTask1.addActionListener(e -> new statistiques1().createPieChartForm().show());

        //addAll(btnAddTask1);

        EquipeService as = new EquipeService();
        ArrayList<Equipe> list = as.AfficherJeux();
       

        {

            for (Equipe a : list) {

                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Nom :" + a.getNom());
                SpanLabel cat1 = new SpanLabel("logo :" + a.getLogo());
                SpanLabel cat2 = new SpanLabel("Score :" + a.getScore());
                SpanLabel cat3 = new SpanLabel("region :" + a.getRegion());
               

                c3.add(cat);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(cat3);
               

                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette reclamation??");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                           as.Delete(a.getIdequipe());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("reclammation SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListTeamForm(res,a).show();
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
                    new UpdateTeamForm(res,a).show();
                        }

                   
                    );

        


                System.out.println("");

                add(c3);

            }

        }

    }
}

