import java.util.LinkedList;
import java.util.Queue;

interface QueueBehaviour {
    void enqueue(String person);
    String dequeue();
    boolean isEmpty();
}

interface MarketBehaviour {
    void addPerson(String person);
    void removePerson(String person);
    void acceptOrder(String order);
    void fulfillOrder();
}

public class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<String> queue;
    private Queue<String> orders;

    public Market() {
        this.queue = new LinkedList<>();
        this.orders = new LinkedList<>();
    }

    @Override
    public void enqueue(String person) {
        queue.add(person);
        System.out.println(person + " добавлен в очередь.");
    }

    @Override
    public String dequeue() {
        String person = queue.poll();
        if (person != null) {
            System.out.println(person + " покинул очередь.");
        }
        return person;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void addPerson(String person) {
        enqueue(person);
    }

    @Override
    public void removePerson(String person) {
        if (queue.remove(person)) {
            System.out.println(person + " был удален из очереди.");
        } else {
            System.out.println(person + " не найден в очереди.");
        }
    }

    @Override
    public void acceptOrder(String order) {
        orders.add(order);
        System.out.println("Заказ принят: " + order);
    }

    @Override
    public void fulfillOrder() {
        String order = orders.poll();
        if (order != null) {
            System.out.println("Заказ выполнен: " + order);
        } else {
            System.out.println("Нет заказов для выполнения.");
        }
    }

    public void update() {
        System.out.println("Обновление состояния магазина...");
        if (!isEmpty()) {
            String person = dequeue();
            System.out.println(person + " обрабатывается.");
        }
        fulfillOrder();
    }

    public static void main(String[] args) {
        Market market = new Market();
        market.addPerson("Петр");
        market.addPerson("Мария");
        market.acceptOrder("Заказ 1");
        market.acceptOrder("Заказ 2");

        market.update();
        market.update();
        market.update();
    }
}
