import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input {

    private static ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();

    public static void LastUpdate() {
        downKeys.clear();
    }

    public static void KeyPressed(int keyCode) {
        if (!pressedKeys.contains(keyCode)) {
            pressedKeys.add(keyCode);
            downKeys.add(keyCode);
        }
    }

    public static void KeyReleased(int keyCode) {
        if (pressedKeys.contains(keyCode)) {
            pressedKeys.remove(pressedKeys.indexOf(keyCode));
        }
    }

    public static boolean GetButton(int keyCode) {
        for (int pressed : pressedKeys) {
            if(pressed == keyCode) {
                return true;
            }
        }
        return false;
    }

    public static boolean GetButtonDown(int keyCode) {
        for (int down : downKeys) {
            if(down == keyCode) {
                return true;
            }
        }
        return false;
    }

    public static int GetAxis(String axis) {
        if(axis == "Vertical") {
            if(GetButton(KeyEvent.VK_W)) return -1;
            if(GetButton(KeyEvent.VK_UP)) return -1;
            if(GetButton(KeyEvent.VK_S)) return 1;
            if(GetButton(KeyEvent.VK_DOWN)) return 1;
        } else if (axis == "Horizontal") {
            if(GetButton(KeyEvent.VK_D)) return 1;
            if(GetButton(KeyEvent.VK_RIGHT)) return 1;
            if(GetButton(KeyEvent.VK_A)) return -1;
            if(GetButton(KeyEvent.VK_LEFT)) return -1;
        }
        return 0;
    }

}
