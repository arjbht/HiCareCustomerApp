package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.offer.Offer;

public class OffersViewModel implements Parcelable{
    private String ImageUrl;
    private String UrlLink;
    private String Title;
    private String Description;
    private String ExpiryDate;
    private String Discount;

    public OffersViewModel() {
        this.ImageUrl = "NA";
        this.UrlLink = "NA";
        this.Title = "NA";
        this.Description = "NA";
        this.ExpiryDate = "NA";
        this.Discount = "NA";
    }

    protected OffersViewModel(Parcel in) {
        ImageUrl = in.readString();
        UrlLink = in.readString();
        Title = in.readString();
        Description = in.readString();
        ExpiryDate = in.readString();
        Discount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ImageUrl);
        dest.writeString(UrlLink);
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(ExpiryDate);
        dest.writeString(Discount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OffersViewModel> CREATOR = new Creator<OffersViewModel>() {
        @Override
        public OffersViewModel createFromParcel(Parcel in) {
            return new OffersViewModel(in);
        }

        @Override
        public OffersViewModel[] newArray(int size) {
            return new OffersViewModel[size];
        }
    };

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getUrlLink() {
        return UrlLink;
    }

    public void setUrlLink(String urlLink) {
        UrlLink = urlLink;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public void clone(Offer offer){
        this.Title = offer.getTitle();
        this.Description = offer.getDescription();
        this.ImageUrl = offer.getImageUrl();
        this.UrlLink = offer.getUrlLink();
        this.ExpiryDate = offer.getExpiryDate();
        this.Discount = offer.getDiscount();
    }


}
