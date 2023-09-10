package com.example.mvcepic;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class acceuil extends VBox {
    private final List<Image> images = new ArrayList<>();
    private int currentIndex = 0;

    private final ImageView imageView = new ImageView();


    acceuil() {
        imageView.setFitWidth(800);
        imageView.setFitHeight(500);

        images.add(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\slide\\img1.jpg"));
        images.add(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\slide\\img2.jpg"));
        images.add(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\slide\\img3.jpg"));
        images.add(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\slide\\img4.jpg"));
        images.add(new Image("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\slide\\img5.jpg"));


        // Set the first image
        imageView.setImage(images.get(currentIndex));

        // Create a timeline to control the slide animation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {
                    // Get the next image index
                    currentIndex = (currentIndex + 1) % images.size();
                    // Set the next image
                    imageView.setImage(images.get(currentIndex));
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat the animation indefinitely
        timeline.play(); // Start the animation

        // upcoming game
        Label lb_next=new Label("Coming soon ");
        lb_next.setFont(Font.font("MONOSPACED BOLD",30));
        lb_next.setPadding(new Insets(10,0,10,20));
        lb_next.setTextFill(Color.WHITE);


        soon s1=new soon("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\soon\\res.jpg","In this action-adventure game, you play as a stra ction-advme,adventure game, you play a-adventure game you play as a stra nded adventurer who has found... ","Resident evil 4");
        soon s2=new soon("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\soon\\lol.jpg","In this action-adventure game, you play as a stra ction-advme,adventure game, you play a-adventure game you play as a stra nded adventurer who has found...","League of legends 2");
        soon s3=new soon("C:\\Users\\SBS\\Desktop\\ProjetsJava\\mvcepic2\\mvcepic\\src\\img\\soon\\sims.jpg","In this action-adventure game, you play as a stra ction-advme,adventure game, you play a-adventure game you play as a stra nded adventurer who has found...","The sims  5");

        this.setPadding(new Insets(0,0,50,30));
        this.getChildren().addAll(imageView,lb_next,s1,s2,s3);
        this.setSpacing(20);

    }
}
