
public class Player extends GameObject{

    float speedPerSecond = 10;

    Player() {
        ascii = new char[][] {
                {'<','o',')', ' '},
                {' ','(',' ','\\'}
        };
        position = new Vector2(Console.columns /2,Console.rows/2);
        isVisible = true;
        collider = new Collider(this);
    }

    @Override
    public void Update() {
        move();
    }

    @Override
    public void Collision(GameObject other) {
        Program.RUN = false;
    }

    void move() {
        Vector2 newPos = new Vector2(position.x, position.y);
        newPos.y += Input.GetAxis("Vertical") * speedPerSecond * Time.DeltaTime;
        newPos.x += Input.GetAxis("Horizontal") * speedPerSecond * Time.DeltaTime;

        if(newPos.x <= 1 || newPos.x + ascii[0].length + 1 >= Console.columns) return;
        if(newPos.y <= 1 || newPos.y + ascii.length + 1 >= Console.rows) return;
        position = newPos;
    }
}
