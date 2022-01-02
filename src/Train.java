import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Train extends GameObject {

    int direction = 1;
    int speedPerSecond = 15;

    Train(Vector2 p, boolean forward) {
        position = p;
        isVisible = true;
        ascii = new char[][] {
                {'_','_','_','_',' ',' ',' ',' ',' ',' ', ' '},
                {'|','D','D','|','_','_','_','_','T','_', ' '},
                {'|','_',' ','|','_','_','_','_','_','|', '<'},
                {' ',' ','@','-','@','-','@','-','o','o','\\'}
        };
        children = new ArrayList<GameObject>();
        collider = new Collider(this);

        speedPerSecond = new Random().nextInt(15, TrainSpawner.MAX_TRAIN_SPEED);

        if(!forward) {
            turn();
        }

        int randomNum = ThreadLocalRandom.current().nextInt(1, (int) TrainSpawner.MAX_CAR_COUNT + 1);
        for(int i = 0; i < randomNum; i++) {
            if(children.size() == 0) {
                children.add(new Car(null, this));
            } else {
                children.add(new Car((Car) children.get(children.size() - 1), this));
            }
        }
    }

    @Override
    public void Update() {
        move();
    }

    void move() {
        position.x += direction * speedPerSecond * Time.DeltaTime;
    }

    void turn() {
        for (int j = 0; j < ascii.length; j++) {
            for (int i = 0; i < ascii[j].length / 2; i++) {
                char temp = turnLetter(ascii[j][i]);
                ascii[j][i] = turnLetter(ascii[j][ascii[j].length - 1 - i]);
                ascii[j][ascii[j].length - 1 - i] = temp;
            }
        }
        direction *= -1;
    }

    private char turnLetter(char l) {
        switch (l) {
            case '<':
                return '>';
            case '>':
                return '<';
            case '/':
                return '\\';
            case '\\':
                return '/';
            default:
                return l;
        }
    }
}