package com.example.mvcepic;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class buttoneffectsadmin implements EventHandler<MouseEvent> {

    AdminSide app;

    public buttoneffectsadmin(AdminSide app) {
        this.app = app;
    }

    @Override

    public void handle(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
            btn.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(8), null)));

        }
        if (mouseEvent.getEventType() == mouseEvent.MOUSE_EXITED) {

            btn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), null)));
        }
        if (mouseEvent.getEventType() == mouseEvent.MOUSE_CLICKED) {
            System.out.println(btn.getText() + " clicked");
        }

    }

    {
    }
}
