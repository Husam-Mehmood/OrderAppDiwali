public class Chef extends Thread {
    private final Restaraunt restaurant;

    public Chef(Restaraunt restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        while (true) {
            OrderItem item;
            synchronized (restaurant) {
                item = restaurant.getCurrentOrder();
                if (item == null) continue;
            }

            System.out.println("Chef is preparing: " + item.getItemName());
            try {
                Thread.sleep(2000); // Simulate meal preparation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Chef finished: " + item.getItemName());
            restaurant.notifyOrderReady();

            synchronized (restaurant) {
                restaurant.setCurrentOrder(null); // Mark current order as done
                if (!restaurant.hasMoreOrders()) {
                    break;
                }
            }
        }
    }
}