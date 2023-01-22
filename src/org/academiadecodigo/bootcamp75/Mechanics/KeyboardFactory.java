package Mechanics;

import org.academiadecodigo.simplegraphics.keyboard.*;

public class KeyboardFactory {

    public static void addKeys(Keyboard keyboard, int key) {

        KeyboardEvent keyboardEvent = new KeyboardEvent();
        keyboardEvent.setKey(key);
        keyboardEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent);
    }
}
