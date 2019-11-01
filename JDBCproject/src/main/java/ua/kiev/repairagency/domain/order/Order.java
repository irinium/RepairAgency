package ua.kiev.repairagency.domain.order;

public class Order {
    private Long id;
    private String title;
    private Long price;
    private Long userId;
    private Long applianceId;
    private Long masterId;

    public Order(Long id, Long applianceId, Long price, Long userId, Long masterId, String title) {
        this.id = id;
        this.applianceId = applianceId;
        this.price = price;
        this.userId = userId;
        this.masterId = masterId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getMasterId() {
        return masterId;
    }

    public Order setMasterId(Long masterId) {
        this.masterId = masterId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Order setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getApplianceId() {
        return applianceId;
    }

    public Order setApplianceId(Long applianceId) {
        this.applianceId = applianceId;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", title=" + title + ", price=" + price + "master=" + masterId + "]";
    }
}
