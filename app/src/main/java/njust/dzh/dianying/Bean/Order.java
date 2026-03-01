package njust.dzh.dianying.Bean;

public class Order {
    // order number
    private String id;
    // order time
    private String time;
    // order price
    private String price;
    // order content
    private String content;

    public Order(String id, String time, String price, String content) {
        this.id = id;
        this.time = time;
        this.price = price;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
