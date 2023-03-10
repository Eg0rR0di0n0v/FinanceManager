package ru.netology.jsonData;

import com.google.gson.annotations.SerializedName;

public class jsonData {
    @SerializedName("title")
    public String title;
    @SerializedName("date")
    public String date;
    @SerializedName("sum")
    public Integer sum;
}

