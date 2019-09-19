package com.arj.hicarehygiene.network.model.offer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("UrlLink")
    @Expose
    private String urlLink;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExpiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("Discount")
    @Expose
    private String discount;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
