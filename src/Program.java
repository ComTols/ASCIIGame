public class Program {

    public static boolean RUN = true;

    public static void main(String[] args) {
        Console c = new Console(1800, 1800/16*9);
        Time.Start();

        while (RUN) {
            Time.BeforeUpdate();

            c.Update();
            c.LastUpdate();

            Input.LastUpdate();
            c.draw();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        c.gameOver();
    }
}
