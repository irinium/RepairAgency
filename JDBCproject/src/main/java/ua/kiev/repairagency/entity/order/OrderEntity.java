package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.Entity;

public class OrderEntity extends Entity {
    private Long id;
    private String title;
    private Long price;
    private Long userId;
    private Long applianceId;
    private Long masterId;

    public OrderEntity(Long id, Long applianceId, Long price, Long userId, Long masterId, String title) {
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

    public OrderEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(Long applianceId) {
        this.applianceId = applianceId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((applianceId == null) ? 0 : applianceId.hashCode());
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
        OrderEntity other = (OrderEntity) obj;
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
        if (applianceId == null) {
            if (other.applianceId != null)
                return false;
        } else if (!applianceId.equals(other.applianceId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", title=" + applianceId.toString() + ", price=" + price + "master=" + masterId + "]";
    }
}
