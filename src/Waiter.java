public class Waiter extends Thread {
    private final Restaraunt restaurant;
    private final Chef chef;

    public Waiter(Restaraunt restaurant, Chef chef) {
        this.restaurant = restaurant;
        this.chef = chef;
    }

    public void run() {
        while (restaurant.hasMoreOrders()) {
            OrderItem item;
            synchronized (restaurant) {
                item = restaurant.getNextOrder();
                if (item == null) continue;
                restaurant.setCurrentOrder(item);
            }

            System.out.println("Waiter picked up: " + item.getItemName());
            if (!chef.isAlive()) chef.start(); // Start chef if not already running
            try {
                restaurant.waitForOrderCompletion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter served: " + item.getItemName());
        }
    }
}