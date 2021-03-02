package design.adapter;

/**
 * @author luliangliang9534@100.me
 */
public class DonaldDuck implements Duck{

    @Override
    public void quack() {
        System.out.println("This is DonalDuck quack!!!");
    }

    @Override
    public void fly() {
        System.out.println("DonalDuck is flying");
    }
}
