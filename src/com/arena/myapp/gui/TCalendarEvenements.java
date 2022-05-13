/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;


import com.arena.myapp.entities.Tournois;
import com.arena.myapp.gui.BaseForm;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.arena.myapp.services.TournoisService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TCalendarEvenements extends BaseForm {
 Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    TournoisService serviceEvenements = TournoisService.getInstance();
    List<Tournois> listEvents = serviceEvenements.AfficherTournois();
    boolean testbtnrempli = false;
    Button btnrempl;

    public TCalendarEvenements() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TCalendarEvenements(com.codename1.ui.util.Resources resourceObjectInstance) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        //installSidemenu(resourceObjectInstance);
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Calendrier Des Evenements");
        
        addComponent(containerglobal);

        Calendar cal = new Calendar() {

            ArrayList<String[]> data = new ArrayList<>();
            int i, j, columns;

            @Override
            protected void updateButtonDayDate(Button dayButton, int currentMonth, int day) {

                dayButton.setText("" + day);

                for (Tournois m : listEvents) {
                    java.util.Calendar c = java.util.Calendar.getInstance();
                   // c.setTime(m.getDateDebut());
                    int dd = c.get(java.util.Calendar.DAY_OF_MONTH);
                    int mm = c.get(java.util.Calendar.MONTH);
                    if (day == dd && currentMonth == mm) {
                         Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
                        dayButton.getStyle().setFgColor(0x00FF00);
                        dayButton.getStyle().setFont(mediumBoldSystemFont);
                    }
                }
                
                dayButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(testbtnrempli){
                            Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
                        btnrempl.getStyle().setFgColor(0x00FF00);
                        btnrempl.getStyle().setFont(mediumBoldSystemFont);
                        testbtnrempli=false;
                        }
                        java.util.Calendar c = java.util.Calendar.getInstance().getInstance();
                        c.setTime(getDate());

                        int m1 = (c.get(java.util.Calendar.MONTH));
                        int y1 = (c.get(java.util.Calendar.YEAR));

                        if (m1 == currentMonth) {

                            SpanLabel programmes = new SpanLabel("");
                            
                                     String string = "January 2, 2022";
                                  DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
                                    Date date;
                            try {
                                date = format.parse(string);
                            
                            
                            
                            for (Tournois m : listEvents) {
                              //  String dt1 = L10NManager.getInstance().formatDateShortStyle(m.getDateDebut());
                               String dt1 = L10NManager.getInstance().formatDateShortStyle(date);
                                String dt2 = L10NManager.getInstance().formatDateShortStyle(getDate());
                                if (dt1.equals(dt2)) {
                                    programmes.setText(programmes.getText() + "\n" + m.getTitre());

                                }
                            }
                        
                        } catch (ParseException ex) {
                            }
                        
                        
                            if (!programmes.getText().equals("")) {
                                Dialog.show("Programme", programmes, new Command("OK"));
                                testbtnrempli = true;
                                btnrempl = dayButton;
                            } else {
                                Dialog.show("Programme", new Label("Pas d'Evenement pour ce jour"), new Command("OK"));
                            }
                        }

                    }

                });

            }

            @Override
            protected void bindDayListener(Component cmp, ActionListener l) {
                if (cmp instanceof Button) {
                    ((Button) cmp).addActionListener(l);

                    try {
                        cmp.getStyle().setFgColor(0x00FF00);
                        if (testbtnrempli) {
                            Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
                        btnrempl.getStyle().setFgColor(0x00FF00);
                        btnrempl.getStyle().setFont(mediumBoldSystemFont);
                            testbtnrempli = false;
                        }

                    } catch (Exception e) {

                    }
                }
            }
        };
        containerglobal.add(cal);
    }
    
    
}
