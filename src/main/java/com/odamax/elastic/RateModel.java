package com.odamax.elastic;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by emrahkucukkaraca on 01/11/2016.
 */

@Entity
@Table(name = "RATES")


public class RateModel  {

    @Id
    @GeneratedValue(generator = "RATES_ID_SEQ")
    @SequenceGenerator(name = "RATES_ID_SEQ", sequenceName = "RATES_ID_SEQ", allocationSize = 1)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "RATE_DAY")
    private Date rateDay;
    @Column(name = "HOTEL_ID", length = 24)
    private String hotelId;
    @Column(name = "ROOM_ID", length = 24)
    private String roomId;


    //--------------------------Price Başlangıcı----------------------
    @Column(name = "PRICE")
    private BigDecimal roomPrice;
    @Column(name = "SINGLE_PRICE")
    private BigDecimal singlePrice;
    @Transient
    private Boolean editable = true;

    //--------------------------Price Bitişi--------------------------
    //--------------------------Restriction Başlangıcı----------------
    @Column(name = "MINIMUM_STAY")
    private Integer minimumStay;
    @Column(name = "MAXIMUM_STAY")
    private Integer maximumStay;
    @Column(name = "CLOSED_TO_ARRIVAL", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean closedToArrival = Boolean.FALSE;
    @Column(name = "CLOSED_TO_DEPARTURE", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean closedToDeparture = Boolean.FALSE;
    @Column(name = "CLOSE_OUT", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean closeOut = Boolean.FALSE;

    //---------------------------Restriction Bitişi-------------------
    @Column(name = "PROMOTION")
    private Long promotion;


    public RateModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRateDay() {
        return rateDay;
    }

    public void setRateDay(Date rateDay) {
        this.rateDay = rateDay;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Integer getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(Integer minimumStay) {
        this.minimumStay = minimumStay;
    }

    public Integer getMaximumStay() {
        return maximumStay;
    }

    public void setMaximumStay(Integer maximumStay) {
        this.maximumStay = maximumStay;
    }

    public Boolean getClosedToArrival() {
        return closedToArrival;
    }

    public void setClosedToArrival(Boolean closedToArrival) {
        this.closedToArrival = closedToArrival;
    }

    public Boolean getClosedToDeparture() {
        return closedToDeparture;
    }

    public void setClosedToDeparture(Boolean closedToDeparture) {
        this.closedToDeparture = closedToDeparture;
    }

    public Boolean getCloseOut() {
        return closeOut;
    }

    public void setCloseOut(Boolean closeOut) {
        this.closeOut = closeOut;
    }

    public Long getPromotion() {
        return promotion;
    }

    public void setPromotion(Long promotion) {
        this.promotion = promotion;
    }
}
