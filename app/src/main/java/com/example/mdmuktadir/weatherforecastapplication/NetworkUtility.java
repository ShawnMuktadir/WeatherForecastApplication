package com.example.mdmuktadir.weatherforecastapplication;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtility {

    private static final String TAG="NetworkUtils";

    private static final String BASE_URL="http://dataservice.accuweather.com/forecasts/v1/daily/5day/28143";

    private static final String API_KEY="emJJr2a5uMXxxV2nKwoF7lTCRSl5tT6U";

    private static final String PARAM_API_KEY="apikey";

    private static final String PARAM_METRIC="metric";

    private static final String METRIC_VALUE="true";

    public static URL buildUrlForWeather(){
        Uri builtUri=Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .appendQueryParameter(PARAM_METRIC,METRIC_VALUE).build();  // URI = Helper class for building or manipulating URI references.

        URL url=null;
        try{
            url=new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG,"buildUrlForWeather: url: "+url);
        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{

        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream=urlConnection.getInputStream(); //This abstract class is the superclass of all classes representing
                                                                    // an input stream of bytes.

            Scanner scanner=new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput=scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }




    }







}
