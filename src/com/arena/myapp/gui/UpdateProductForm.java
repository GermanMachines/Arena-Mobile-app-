/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.arena.myapp.entities.Product;
import com.arena.myapp.services.ServiceProduct;

/**
 *
 * @author HP
 */
public class UpdateProductForm extends BaseForm {

    Form current;

    public UpdateProductForm(Resources res, Product p) {
        super("Products", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Update Product");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        TextField tfID = new TextField(String.valueOf(p.getId()), "ArtilceID");
        tfID.setVisible(false);
        TextField name = new TextField(p.getName(), "name", 20, TextField.ANY);
        TextField price = new TextField(Integer.toString(p.getPrice()), "price", 20, TextField.ANY);
        TextField qty = new TextField(Integer.toString(p.getQty()), "qty", 20, TextField.ANY);
        TextField description = new TextField(p.getDesc(), "Description", 20, TextField.ANY);
        TextField image = new TextField(p.getImage(), "image", 20, TextField.ANY);
        TextField idCat = new TextField(Integer.toString(p.getIdCategory()), "idcat", 20, TextField.ANY);
        TextField rate = new TextField(String.valueOf(p.getRate()), "rate", 20, TextField.ANY);

        name.setUIID("NewsTopLine");
        price.setUIID("NewsTopLine");
        qty.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        image.setUIID("NewsTopLine");
        idCat.setUIID("NewsTopLine");
        rate.setUIID("NewsTopLine");

        name.setSingleLineTextArea(true);
        price.setSingleLineTextArea(true);
        qty.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);
        idCat.setSingleLineTextArea(true);
        rate.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        btnModifier.addPointerPressedListener(l -> {

            p.setName(name.getText());
            System.out.println(name.getText());
            p.setPrice(Integer.valueOf(price.getText()));
            p.setQty(Integer.valueOf(qty.getText()));
            p.setDesc(description.getText());
            p.setImage(image.getText());
            p.setIdCategory(Integer.valueOf(idCat.getText()));
            p.setRate(Integer.valueOf(rate.getText()));

            if (ServiceProduct.getInstance().updateProduct(p)) {
                new ListProductForm(res,p).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListProductForm(res,p).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(name),
                createLineSeparator(),
                new FloatingHint(price),
                createLineSeparator(),
                new FloatingHint(qty),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(idCat),
                createLineSeparator(),
                new FloatingHint(rate),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
