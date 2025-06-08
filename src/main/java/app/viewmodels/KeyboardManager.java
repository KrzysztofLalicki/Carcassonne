package app.viewmodels;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class KeyboardManager {
    private static final KeyboardManager INSTANCE = new KeyboardManager();

    List<Consumer<KeyEvent>> activeKeyEventsHandlers = new CopyOnWriteArrayList<>();

    private KeyboardManager() {}

    public static KeyboardManager getInstance() {
        return INSTANCE;
    }

    public void register(Consumer<KeyEvent> keyEventHandler) {
        if(!activeKeyEventsHandlers.contains(keyEventHandler))
            activeKeyEventsHandlers.add(keyEventHandler);
    }

    public void remove(Consumer<KeyEvent> keyEventHandler) {
        activeKeyEventsHandlers.remove(keyEventHandler);
    }

    public void installOn(Scene scene) {
        scene.removeEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
    }

    private void handleKey(KeyEvent event) {
        activeKeyEventsHandlers.forEach(handler -> handler.accept(event));
    }
}