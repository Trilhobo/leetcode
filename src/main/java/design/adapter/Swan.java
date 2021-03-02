package design.adapter;

/**
 * @author luliangliang9534@100.me
 */
public class Swan implements Goose{

    @Override
    public void bark() {
        System.out.println("This is swan barking");
    }

    @Override
    public void fly() {
        System.out.println("This is swan flying");
    }
}
