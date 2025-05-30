package app.menu;

import app.model.Game;
import app.model.Player;
import app.view.GameView;
import app.view.SideBarView;
import app.viewmodels.GameViewModel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ViewModelMenu {
    private Game game;
    private Stage primaryStage;
    Color currentColor = Color.TRANSPARENT;

    public ViewModelMenu(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
        VBox vbox = new OpenMenu(this);
        primaryStage.setScene(new Scene(vbox,800,600));
        primaryStage.show();
    }

    public Game getGame() {return game;}
    public Stage getPrimaryStage() {return primaryStage;}
    public ArrayList<Player> getPlayers() {return game.getPlayers();}


    public void setGame(Game game) {this.game = game;}
    public void setPrimaryStage(Stage primaryStage) {this.primaryStage = primaryStage;}
    public void setRoot(javafx.scene.Parent root) {
        primaryStage.getScene().setRoot(root);
    }

    //MainMenu Functions

    public void newMainMenu(String text) {
        primaryStage.getScene().setRoot(new MainMenu(text,this));
    }

    public void AddPlayerFromMainMenu() {
        if (game.getPlayers().size() < 6) {
            StackPane newPlayerLayout = new NewPlayerMenu("",this);
            primaryStage.getScene().setRoot(newPlayerLayout);
        } else {
            StackPane menuLayout = new MainMenu("Maksymalna liczba graczy",this);
            primaryStage.getScene().setRoot(menuLayout);
        }
    }

    public void StarGame(){
        if (game.getPlayers().size() < 2) {
            StackPane menuLayout = new MainMenu("Za mała ilość graczy",this);
            primaryStage.getScene().setRoot(menuLayout);
        } else {  // Zaczynamy rozgrywke
            primaryStage.setResizable(false);

            game.start();

            GameViewModel gameViewModel = new GameViewModel(game);
            GameView gameView = new GameView(new SideBarView(game.getPlayers()), gameViewModel);
            Scene scene = new Scene(gameView);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void RemovePlayerFromMainMenu(){
        if (game.getPlayers().isEmpty()) {
            StackPane menuLayout = new MainMenu("Gościu co ty chcesz usuwać!",this);
            primaryStage.getScene().setRoot(menuLayout);
        } else {
            StackPane removePlayerMenu = new RemovePlayerMenu("",this);
            primaryStage.getScene().setRoot(removePlayerMenu);
        }
    }

    public void BackToOpenMenu(){
        game = new Game();
        VBox vbox = new OpenMenu(this);
        primaryStage.getScene().setRoot(vbox);
    }

    //NewPlayerMenu Functions

    public void setCurrentColor(Color color) {
        currentColor = color;
    }
    public void AddPlayerFromNewPlayerMenu(String PlayerName) {
        if(PlayerName.isEmpty()) {
            StackPane newPlayerthis = new NewPlayerMenu("Nieprwidłowa nazwa gracza",this);
            primaryStage.getScene().setRoot(newPlayerthis);
        }
        else if(PlayerName.length()>10)
        {
            StackPane newPlayerthis = new NewPlayerMenu("Zadługa nazwa",this);
            primaryStage.getScene().setRoot(newPlayerthis);
        }
        else if(PlayersContains(PlayerName)){
            StackPane newPlayerthis = new NewPlayerMenu("Nazwa zajęta",this);
            primaryStage.getScene().setRoot(newPlayerthis);
        }
        else{
            if(!currentColor.equals(Color.TRANSPARENT) && !PlayersContains(currentColor)) {
            game.getPlayers().add(new Player(PlayerName,currentColor));
            StackPane menu = new MainMenu("",this);
            primaryStage.getScene().setRoot(menu);
            }
            else{
                Color colors[] = {Color.RED,Color.WHITE,Color.PURPLE,Color.BLUE,Color.BLACK,Color.GREEN};
                for(int i = 0; i < colors.length; i++) {
                    if(!PlayersContains(colors[i])) {
                        currentColor = colors[i];
                        break;
                    }
                }
                game.getPlayers().add(new Player(PlayerName,currentColor));
                StackPane menu = new MainMenu("",this);
                primaryStage.getScene().setRoot(menu);
            }
        }
    }

    public void BackToMainMenu(){
        StackPane menu = new MainMenu("",this);
        primaryStage.getScene().setRoot(menu);
    }

    public boolean PlayersContains(String playerName)
    {
        for(Player player : game.getPlayers())
        {
            if(player.getName().equals(playerName))
                return true;
        }
        return false;
    }

    public boolean PlayersContains(Color playerColor)
    {
        for(Player player : game.getPlayers())
        {
            if(player.getColor().equals(playerColor))
                return true;
        }
        return false;
    }

    //RemovePlayerMenu Functions

    public void RemovePlayer(Player player){
        game.getPlayers().remove(player);
        if(game.getPlayers().isEmpty())
        {
            StackPane menuLayout = new MainMenu("",this);
            primaryStage.getScene().setRoot(menuLayout);
        }
        else
        {
            StackPane removeLayout = new RemovePlayerMenu("",this);
            primaryStage.getScene().setRoot(removeLayout);
        }
    }


}
