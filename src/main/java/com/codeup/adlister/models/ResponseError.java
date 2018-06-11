package com.codeup.adlister.models;

import java.util.HashMap;
import java.util.Map;

public class ResponseError  extends Response {

       private String item;
       private String error;


        public ResponseError() {
            super(false);
        }

        public ResponseError(String key, String value) {
            super(false);
            this.error = value;
            this.item = key;
        }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


