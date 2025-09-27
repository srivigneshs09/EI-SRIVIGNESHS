package decorator;

public class WhipCreamDecorator extends CoffeeDecorator {
    public WhipCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whip Cream";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 3.0;
    }
}
