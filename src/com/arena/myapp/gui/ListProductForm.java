/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Post;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.arena.myapp.entities.Product;
import com.arena.myapp.services.ServicePost;
import com.arena.myapp.services.ServiceProduct;
import com.arena.myapp.utils.Session;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import java.util.ArrayList;

/**
 *
 * @author tarek
 */
public class ListProductForm extends BaseForm{
     
      public ListProductForm(Resources res,Product pr){

        super(BoxLayout.y());

        getContentPane().setScrollVisible(true);
       if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }

        
 

        ServicePost as = new ServicePost();

      
      
              //Appel affichage methode
        ArrayList<Product>list = ServiceProduct.getInstance().getProduct(); 
             int i = 0;
             for (Product p : list) {
            i++;
            String urlImage = "http://127.0.0.1:8000/uploads/" + p.getImage();
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addProduct(urlim, p, res, i);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
        
      }
      
      
      
     private void addProduct(Image img, Product p, Resources res, int i) {

        int height = Display.getInstance().convertToPixels(16f);
        int width = Display.getInstance().convertToPixels(12f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        Label ProductText = new Label("Product " + i, "NewsTopLine2");
        Label name = new Label("Product Name: " + p.getName(), "NewsTopLine2");
        Label price = new Label("Price: " + p.getPrice(), "NewsTopLine2");
        Label qty = new Label("Quantity: " + p.getQty(), "NewsTopLine2");
        Label desc = new Label("Description: " + p.getDesc(), "NewsTopLine2");
        Label productImage = new Label("Image: " + p.getImage(), "NewsTopLine2");
        Label idCat = new Label("Category: " + p.getCat(), "NewsTopLine2");
        Label rate = new Label("Rate: " + p.getRate(), "NewsTopLine2");
        Label margin = new Label("", "NewsTopLine2");

        createLineSeparator();

        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);

        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);

        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Suppression");

            if (dig.show("Suppression", "Vous voulez supprimer ce produit ?", "Annuler", "Oui")) {
                dig.dispose();
            } else {
                dig.dispose();
            }
            //n3ayto l suuprimer men service Reclamation
            if (ServiceProduct.getInstance().deleteProduct(p.getId())) {
                new ListProductForm(res,p).show();
            }

        });

        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);

        lModifier.addPointerPressedListener(l -> {
            System.out.println("hello update");
            new UpdateProductForm(res, p).show();
        });
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                BoxLayout.encloseX(name),
                BoxLayout.encloseX(price),
                BoxLayout.encloseX(qty),
                BoxLayout.encloseX(desc),
                BoxLayout.encloseX(productImage),
                BoxLayout.encloseX(idCat),
                BoxLayout.encloseX(rate),
                BoxLayout.encloseX(lSupprimer, lModifier)));

        add(cnt);
        System.out.println(cnt);
    }
}
