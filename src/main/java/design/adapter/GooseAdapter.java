package design.adapter;

/**
 * @author luliangliang9534@100.me
 */
public class GooseAdapter implements Duck{

    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        for (int i = 0; i < 2; i++) {
            goose.bark();
        }
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            goose.fly();
        }
    }
}
