package com.example.mvcepic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChatClient  {
    private TextField inputField;
    private Button sendButton;
    private VBox chatArea;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    String username;


    public ChatClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            CloseEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    private void sendMessage() {

            try {
                bufferedWriter.write(username);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                Scanner scanner = new Scanner(System.in);
                while (socket.isConnected()) {
                    String messageTosend = scanner.nextLine();
                    bufferedWriter.write(username + ": " + messageTosend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }

            } catch (IOException e) {
                CloseEverything(socket, bufferedWriter, bufferedReader);
            }

    }

    public void ListenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);

                    } catch (IOException e) {
                        CloseEverything(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    public void CloseEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {


        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your username for the groupd chat");
        String username=scanner.nextLine();
        Socket socket= null;
        try {
            socket = new Socket("localhost",1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChatClient client=new ChatClient(socket,username);
        client.ListenForMessage();
        client.sendMessage();
    }


    public void start(Stage primaryStage) throws IOException {
        // Create UI elements
        Label titleLabel = new Label("Chat Room");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        chatArea = new VBox();
        chatArea.setSpacing(10);
        chatArea.setPadding(new Insets(10));
        chatArea.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 1px;");
        chatArea.setAlignment(Pos.TOP_LEFT);
        ScrollPane chatScrollPane = new ScrollPane(chatArea);
        chatScrollPane.setFitToWidth(true);
        inputField = new TextField();
        inputField.setPrefWidth(300);
        sendButton = new Button("Send");
        sendButton.setOnAction(event -> sendMessage());

        // Create the layout
        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        root.setCenter(chatScrollPane);
        HBox inputBox = new HBox(10, inputField, sendButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        root.setBottom(inputBox);

        // Create the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Room");
        primaryStage.show();

        // Connect to the server



    }
}
