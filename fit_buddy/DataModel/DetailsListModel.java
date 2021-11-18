package com.example.fit_buddy.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailsListModel {

    @SerializedName("Details")
    public ArrayList<Details> details;

    public static class Details {
        @SerializedName("UNIQUEID")
        public String uniqueid;
        @SerializedName("Exercise")
        public ArrayList<Exercise> exercise;

        public static class Exercise {
            @SerializedName("ExerciseNumber")
            public String exerciseNumber;
            @SerializedName("exercise")
            public String exercise;
            @SerializedName("stepContent")
            public String stepContent;
            @SerializedName("imageUrl")
            public String imageUrl;
        }
    }
}