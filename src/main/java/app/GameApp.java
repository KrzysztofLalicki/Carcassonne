package app;
import app.model.Game;
import app.model.Player;
import javafx.application.Application;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class GameApp extends Application {
    private Stage primaryStage;
    private Game gameInstance;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        VBox vbox = startMenuLayout();
        primaryStage.setScene(new Scene(vbox,800,600));
        primaryStage.show();
    }

    private VBox startMenuLayout() {
        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        Button newGameButton = createStyledButton("Nowa gra");
        newGameButton.setOnAction(e -> {
            gameInstance = new Game();
            StackPane menuLayout = createMenuLayout("");
            primaryStage.getScene().setRoot(menuLayout);
        });

        Button exit = createStyledButton("Opuść gre");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        vbox.getChildren().addAll(label, newGameButton);
        vbox.setSpacing(10);
        vbox.getChildren().add(exit);
        primaryStage.setTitle("Carcassonne");
        return vbox;
    }

    private StackPane createMenuLayout(String text) {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS",42));

        Button addPlayer = createStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);
        addPlayer.setOnAction(e -> {
            if(gameInstance.getPlayers().size() <6) {
                StackPane newPlayerLayout = createNewPlayerLayout("");
                primaryStage.getScene().setRoot(newPlayerLayout);
            }
            else{
                StackPane menuLayout = createMenuLayout("Maksymalna liczba graczy");
                primaryStage.getScene().setRoot(menuLayout);
            }
        });

        Button startGame = createStyledButton("Zacznij gre");
        startGame.setTranslateY(-70);
        startGame.setOnAction(e -> {
            if(gameInstance.getPlayers().size() <2) {
                StackPane meuLayout = createMenuLayout("Za mała ilość graczy");
                primaryStage.getScene().setRoot(meuLayout);
            }
            else{
                Carcassonne carcassonne = new Carcassonne(gameInstance, primaryStage);
                carcassonne.start(primaryStage);
            }
        });

        Button removePlayer = createStyledButton("Usun gracza");
        removePlayer.setTranslateY(30);
        removePlayer.setOnAction(e -> {
            if(gameInstance.getPlayers().isEmpty()) {
                StackPane menuLayout = createMenuLayout("Gościu co ty chcesz usuwać!");
                primaryStage.getScene().setRoot(menuLayout);
            }
            else {
                StackPane removePlayerLayout = createremovePlayerLayout("");
                primaryStage.getScene().setRoot(removePlayerLayout);
            }
        });

        Button exit = createStyledButton("Cofnij");
        exit.setTranslateY(80);
        exit.setOnAction(e -> {
            VBox vbox = startMenuLayout();
            primaryStage.getScene().setRoot(vbox);
        });


        StackPane layout = new StackPane(gameLabel, addPlayer,startGame, removePlayer,label, exit);
        if(!text.isEmpty()) {
            Label warningLabel = new Label(text);
            warningLabel.setFont(Font.font("Comic Sans MS", 20));
            warningLabel.setTextFill(Color.RED);
            warningLabel.setTranslateY(-120);
            FadeTransition fade = new FadeTransition(Duration.seconds(3), warningLabel);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> warningLabel.setVisible(false));
            fade.play();
            layout.getChildren().add(warningLabel);
        }
        if(!gameInstance.getPlayers().isEmpty())
        {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            layout.getChildren().add(playerLabel);
            int level = -10;
            for(Player player : gameInstance.getPlayers())
            {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120,25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                level += 40;
                layout.getChildren().add(playernameButton);
            }


        }
        layout.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        return layout;
    }

    private StackPane createremovePlayerLayout(String text) {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS",42));

        Button addPlayer = createStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);

        addPlayer.setOnAction(e -> {
            if(gameInstance.getPlayers().size() <6) {
                StackPane newPlayerLayout = createNewPlayerLayout("");
                primaryStage.getScene().setRoot(newPlayerLayout);
            }
            else{
                StackPane menuRemovePlayerLayout = createremovePlayerLayout("Maksymalna liczba graczy");
                primaryStage.getScene().setRoot(menuRemovePlayerLayout);
            }
        });

        Button endRemoving = createStyledButton("Zakończ");
        endRemoving.setTranslateY(30);
        endRemoving.setOnAction(e -> {
            StackPane menuLayout = createMenuLayout("");
            primaryStage.getScene().setRoot(menuLayout);
        });


        StackPane layout = new StackPane(addPlayer, endRemoving, label, gameLabel);
        if(!text.isEmpty())
        {
            Label WarningLabel = new Label(text);
            WarningLabel.setTranslateY(-120);
            WarningLabel.setTextFill(Color.RED);
            WarningLabel.setFont(new Font("Comic Sans MS",20));
            FadeTransition fade = new FadeTransition(Duration.seconds(3), WarningLabel);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> WarningLabel.setVisible(false));
            fade.play();
            layout.getChildren().add(WarningLabel);
        }
        if(!gameInstance.getPlayers().isEmpty())
        {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            layout.getChildren().add(playerLabel);
            int level = -10;
            for(Player player : gameInstance.getPlayers())
            {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120,25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                level += 40;
                playernameButton.setOnAction(e->{
                    gameInstance.getPlayers().remove(player);
                    if(gameInstance.getPlayers().isEmpty())
                    {
                        StackPane menuLayout = createMenuLayout("");
                        primaryStage.getScene().setRoot(menuLayout);
                    }
                    else
                    {
                        StackPane removeLayout = createremovePlayerLayout("");
                        primaryStage.getScene().setRoot(removeLayout);
                    }
                });
                layout.getChildren().add(playernameButton);
            }
        }
        layout.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        return layout;

    }

    private StackPane createNewPlayerLayout(String text)
    {
        StackPane layout = new StackPane();
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS",42));
        layout.getChildren().add(label);
        layout.getChildren().add(gameLabel);

        TextField nameField = new TextField();
        nameField.setMaxHeight(40);
        nameField.setMaxWidth(150);
        nameField.setTranslateY(-70);
        nameField.setPromptText("Podaj nazwe");
        nameField.setStyle("-fx-text-fill: purple;");

        nameField.setAlignment(Pos.CENTER);
        nameField.setFont(new Font("Comic Sans MS",18));

        Button addPlayer = createStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);
        addPlayer.setOnAction(e -> {
            String stringPlayerName = nameField.getText();
            if(stringPlayerName.isEmpty()) {
                StackPane newPlayerLayout = createNewPlayerLayout("Nieprwidłowa nazwa gracza");
                primaryStage.getScene().setRoot(newPlayerLayout);
            }
            else if(stringPlayerName.length()>10)
            {
                StackPane newPlayerLayout = createNewPlayerLayout("Zadługa nazwa");
                primaryStage.getScene().setRoot(newPlayerLayout);
            }
            else if(PlayersContains(stringPlayerName)){
                StackPane newPlayerLayout = createNewPlayerLayout("Nazwa zajęta");
                primaryStage.getScene().setRoot(newPlayerLayout);
            }
            else{
                gameInstance.getPlayers().add(new Player(stringPlayerName));
                Pane menuLayout = createMenuLayout("");
                primaryStage.getScene().setRoot(menuLayout);
            }
        });

        if(!gameInstance.getPlayers().isEmpty())
        {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            layout.getChildren().add(playerLabel);
            int level = -10;
            for(Player player : gameInstance.getPlayers())
            {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120,25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                level += 40;
                layout.getChildren().add(playernameButton);
            }


        }

        Button returnToMenu = createStyledButton("Anuluj");
        returnToMenu.setTranslateY(30);
        returnToMenu.setOnAction(e -> {
            StackPane menuLayout = createMenuLayout("");
            primaryStage.getScene().setRoot(menuLayout);
        });
        layout.getChildren().add(returnToMenu);
        if(!text.isEmpty()){
            Label incorrectPlayerName = new Label(text);
            FadeTransition fade = new FadeTransition(Duration.seconds(3), incorrectPlayerName);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> incorrectPlayerName.setVisible(false));
            fade.play();
            incorrectPlayerName.setTextFill(Color.RED);
            incorrectPlayerName.setFont(new Font("Comic Sans MS",20));
            incorrectPlayerName.setTranslateY(-120);
            layout.getChildren().addAll(incorrectPlayerName);
        }
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(nameField);
        layout.getChildren().add(addPlayer);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        return layout;
    }

    public Button createStyledButton(String text) {
        Button createStyledButton = new Button(text);
        createStyledButton.setPrefSize(150, 40);
        createStyledButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,18));
        createStyledButton.setTextFill(Color.PURPLE);
        createStyledButton.setStyle("-fx-background-color: white; -fx-text-fill: purple");
        return createStyledButton;
    }

    public boolean PlayersContains(String playerName)
    {
        for(Player player : gameInstance.getPlayers())
        {
            if(player.getName().equals(playerName))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        launch();
    }
}