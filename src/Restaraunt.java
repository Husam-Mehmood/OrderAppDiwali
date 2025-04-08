import java.util.LinkedList;
import java.util.Queue;

public class Restaraunt {
    private final Queue<OrderItem> orderQueue = new LinkedList<>();
    private boolean isOrderReady = false;
    private OrderItem currentOrder;

    public synchronized void placeOrder(OrderItem item) {
        orderQueue.add(item);
        notifyAll();
    }

    public synchronized OrderItem getNextOrder() {
        return orderQueue.poll();
    }

    public synchronized boolean hasMoreOrders() {
        return !orderQueue.isEmpty();
    }

    public synchronized void waitForOrderCompletion() throws InterruptedException {
        while (!isOrderReady) {
            wait();
        }
        isOrderReady = false;
    }

    public synchronized void notifyOrderReady() {
        isOrderReady = true;
        notify();
    }

    public void setCurrentOrder(OrderItem item) {
        currentOrder = item;
    }

    public OrderItem getCurrentOrder() {
        return currentOrder;
    }
}