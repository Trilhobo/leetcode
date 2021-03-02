package design.adapter;

/**
 * @author luliangliang9534@100.me
 */
public class Test {

    public static void main(String[] args) {
        Swan swan = new Swan();
        System.out.println("This is a swan");
        swan.bark();
        swan.fly();

        System.out.println("This is a swan cosplay duck");
        Duck adapter = new GooseAdapter(swan);
        adapter.quack();
        adapter.fly();

        System.out.println("This is a DonaldDuck");
        Duck duck = new DonaldDuck();
        duck.fly();
        duck.quack();

        System.out.println("This is a duck cosplay goose");
        Goose goose = new DuckAdapter(duck);
        goose.bark();
        goose.fly();

    }
}
