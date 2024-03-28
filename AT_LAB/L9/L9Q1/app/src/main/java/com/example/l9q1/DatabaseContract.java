package com.example.l9q1;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class SurveyEntry implements BaseColumns {
        public static final String TABLE_NAME = "survey";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_RESPONSE = "response";
    }
}
