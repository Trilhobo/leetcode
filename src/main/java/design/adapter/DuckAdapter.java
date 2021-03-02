package design.adapter;

/**
 * @author luliangliang9534@100.me
 */
public class DuckAdapter implements Goose {

    private final Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void bark() {
        for (int i = 0; i < 3; i++) {
            duck.quack();
        }
    }

    @Override
    public void fly() {
        for (int i = 0; i < 3; i++) {
            duck.fly();
        }
    }
}
