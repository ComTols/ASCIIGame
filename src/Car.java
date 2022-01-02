import java.util.concurrent.ThreadLocalRandom;

public class Car extends GameObject{

    //Minimum 8
    int length = 8;
    Train train;

    Car(Car frontPos, Train t) {
        isVisible = true;
        length = ThreadLocalRandom.current().nextInt(8, 15 + 1);
        ascii = new char[4][length];
        for (int i = 0; i < ascii[0].length; i++) {
            ascii[0][i] = '_';
        }
        ascii[1][0] = '|';
        ascii[1][ascii[1].length - 1] = '|';
        ascii[2][0] = '|';
        for (int i = 1; i < ascii[2].length - 1; i++) {
            ascii[2][i] = '_';
        }
        ascii[2][ascii[2].length - 1] = '|';
        ascii[3][2] = 'O';
        ascii[3][ascii[2].length - 3] = 'O';

        if(t.direction > 0) {
            if(frontPos == null) {
                position = new Vector2(t.position.x - length - 1, t.position.y);
            } else {
                position = new Vector2(frontPos.position.x - length - 1, frontPos.position.y);
            }
        } else {
            if(frontPos == null) {
                position = new Vector2(t.position.x + 12, t.position.y);
            } else {
                position = new Vector2(frontPos.position.x + frontPos.length + 1, frontPos.position.y);
            }
        }
        collider = new Collider(this);
        train = t;
    }

    @Override
    public void Update() {
        move();
    }

    void move() {
        position.x += train.direction * train.speedPerSecond * Time.DeltaTime;
    }
}