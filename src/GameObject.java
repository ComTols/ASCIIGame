import java.util.ArrayList;

public class GameObject {

    String name;
    char[][] ascii = null;
    Collider collider;
    Vector2 position = new Vector2();
    boolean isVisible = false;
    ArrayList<GameObject> children;

    public void Update() {
    }

    public void Collision(GameObject other) {
    }

    public void draw(Console c) {
        if(!isVisible || ascii == null) return;
        int row = (int) Math.round(position.y);
        int column = (int) Math.round(position.x);

        for(int i = 0; i < ascii.length; i++) {
            int columnPointer = column;
            for(int j = 0; j < ascii[i].length; j++) {
                try {
                    c.field[row][columnPointer] = ascii[i][j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Nicht zeichnen
                }
                columnPointer++;
            }
            row++;
        }
    }

}
