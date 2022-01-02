import java.util.ArrayList;

public class Collider {

    public static ArrayList<Collider> colliders = new ArrayList<>();
    GameObject object;
    Vector2 size;

    Collider(GameObject g) {
        object = g;

        if(g.ascii != null) {
            size = new Vector2(g.ascii[0].length, g.ascii.length);
        } else {
            size = new Vector2();
        }

        colliders.add(this);
    }

    void Update() {
        Collider[] col = colliders.toArray(new Collider[0]);
        for (Collider c : col) {
            if(c != this) {
                //X Match
                if((object.position.x <= c.object.position.x && object.position.x + size.x >= c.object.position.x) ||
                        (object.position.x >= c.object.position.x && object.position.x <= c.object.position.x + c.size.x)
                ) {
                    //Y Match
                    if((object.position.y <= c.object.position.y && object.position.y + size.y >= c.object.position.y) ||
                            (object.position.y >= c.object.position.y && object.position.y <= c.object.position.y + c.size.y)
                    ) {
                        //COLLISION!
                        object.Collision(c.object);
                        c.object.Collision(object);
                    }
                }
            }
        }
    }

}
