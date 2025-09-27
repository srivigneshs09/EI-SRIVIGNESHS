import decorator.*;

public class Main {
    public static void main(String[] args) {
        Coffee simple = new SimpleCoffee();
        System.out.println(simple.getDescription() + " ₹" + simple.getCost());

        Coffee milkCoffee = new MilkDecorator(simple);
        System.out.println(milkCoffee.getDescription() + " ₹" + milkCoffee.getCost());

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println(sugarMilkCoffee.getDescription() + " ₹" + sugarMilkCoffee.getCost());

        Coffee deluxe = new WhipCreamDecorator(sugarMilkCoffee);
        System.out.println(deluxe.getDescription() + " ₹" + deluxe.getCost());
    }
}
