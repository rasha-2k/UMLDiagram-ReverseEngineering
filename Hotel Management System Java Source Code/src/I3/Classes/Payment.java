package I3.Classes;

import java.util.ArrayList;
import java.util.List;

public class Payment {

    private Booking booking;
    private List<ExtraOrders> orders = new ArrayList<>();
    private int totalRentPrice;
    private int daysStayed;
    private String paymentDate;
    private String paymentMethod;
    private boolean hasDiscount;
    private float discount;
    private int totalBill;

    public Payment(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int calculateTotalBill() {
        int orderTotal = orders.stream()
                .mapToInt(order -> order.getQuantity() * order.getItem().getPrice())
                .sum();
        totalBill = orderTotal + totalRentPrice;
        return totalBill;
    }

    public List<ExtraOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<ExtraOrders> orders) {
        this.orders = orders;
    }

    public int getTotalRentPrice() {
        return totalRentPrice;
    }

    public void setTotalRentPrice(int totalRentPrice) {
        this.totalRentPrice = totalRentPrice;
    }

    public int getDaysStayed() {
        return daysStayed;
    }

    public void setDaysStayed(int daysStayed) {
        this.daysStayed = daysStayed;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "booking=" + booking +
                ", orders=" + orders +
                ", totalRentPrice=" + totalRentPrice +
                ", daysStayed=" + daysStayed +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", hasDiscount=" + hasDiscount +
                ", discount=" + discount +
                ", totalBill=" + totalBill +
                '}';
    }
}
