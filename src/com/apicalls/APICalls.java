package com.apicalls;

import org.json.JSONObject;

/**
 * Created by Thinh-Laptop on 18.01.2017.
 */
public interface APICalls {

    JSONObject execute();

    @Override
    String toString();
}
