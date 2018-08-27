package com.example.mdmuktadir.weatherforecastapplication;

import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;
    private ArrayList<Weather> weatherArrayList=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.idListView);

        URL weatherUrl=NetworkUtility.buildUrlForWeather();
        new FetchWeatherDetails().execute(weatherUrl);
        Log.d(TAG," onCreate weatherUrl: "+weatherUrl);
    }

    //Lets write our anonymous inner class extending asyncTask to fetch json in our app

    private class FetchWeatherDetails extends AsyncTask<URL,Void,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL weatherUrl=urls[0];
            String weatherSearchResult=null;

            try{
                weatherSearchResult=NetworkUtility.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG,"weather search result: "+weatherSearchResult);

            return weatherSearchResult;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if ((weatherSearchResults!=null)&&(!weatherSearchResults.equals(""))){
                weatherArrayList=parseJSON(weatherSearchResults);
                Iterator iterator=weatherArrayList.iterator();
                while (iterator.hasNext()){
                    Weather weatherInIterotor= (Weather) iterator.next();

                    Log.i(TAG,"onPostExecute: Date: "+weatherInIterotor.getDate()+
                            "Min: "+weatherInIterotor.getMinTemp()+
                            "Max: "+weatherInIterotor.getMaxTemp()+
                            "Link: "+weatherInIterotor.getLink()+
                            "Day Condition: "+weatherInIterotor.getDayCondition()+
                            "Night Condition: "+weatherInIterotor.getNightCondition());


                }
            }
            super.onPostExecute(weatherSearchResults);
        }
    }
    //JSON is Parsing
    private ArrayList<Weather> parseJSON(String weatherSearchResults) {
        if (weatherArrayList!=null){
            weatherArrayList.clear();
        }
        if (weatherSearchResults!=null){
            try{
                JSONObject rootObject=new JSONObject(weatherSearchResults);
                JSONArray results=rootObject.getJSONArray("DailyForecasts");

                for (int i = 0; i <results.length() ; i++) {
                    Weather weather=new Weather();

                    JSONObject resultsObj=results.getJSONObject(i);

                    String date=resultsObj.getString("Date");
                    weather.setDate(date);

                 Log.d(TAG,"parseJSON date: "+date);

                    JSONObject temperatureObj=resultsObj.getJSONObject("Temperature");

                    String minTemperature=temperatureObj.getJSONObject("Minimum").getString("Value");
                    weather.setMinTemp(minTemperature);

                 Log.d(TAG,"parseJSON minTemperature: "+minTemperature);

                    String maxTemperature=temperatureObj.getJSONObject("Maximum").getString("Value");
                    weather.setMaxTemp(maxTemperature);

                 Log.d(TAG,"parseJSON maxTemperature: "+maxTemperature);

                    String link=resultsObj.getString("Link");
                    weather.setLink("");

                 Log.d(TAG,"parseJSON Link: "+link);
                 //String date=resultsObj.getString("Date");

                    JSONObject conditionObj=resultsObj.getJSONObject("Day");

                    String dayCondition=conditionObj.getString("IconPhrase");
                    weather.setDayCondition(dayCondition);

                    Log.d(TAG,"parseJSON dayCondition: "+dayCondition);

                    JSONObject n_conditionObj=resultsObj.getJSONObject("Night");

                    String nightCondition=n_conditionObj.getString("IconPhrase");
                    weather.setNightCondition(nightCondition);

                    Log.d(TAG,"parseJSON nightCondition: "+nightCondition);





                  Log.d(TAG,"parseJSON date: "+date+" parseJSON minTemperature:  "+minTemperature+
                    " parseJSON maxTemperature: "+maxTemperature+" parseJSON Link: "+link+" parseJSON dayCondition: "+dayCondition+
                          " parseJSON nightCondition: "+nightCondition);

                    weatherArrayList.add(weather);

                    //to verify the arraylist contents we use iterator
                    //just for testing stuff!!
                    Iterator iterator=weatherArrayList.iterator();
                    while (iterator.hasNext()){
                        Weather weatherInIterotor= (Weather) iterator.next();

                        Log.i(TAG,"parseJson: Date: "+weatherInIterotor.getDate()+
                                        " Min: "+weatherInIterotor.getMinTemp()+
                                        " Max: "+weatherInIterotor.getMaxTemp()+
                                        " Link: "+weatherInIterotor.getLink()+
                                        " Day Condition: "+weatherInIterotor.getDayCondition()+
                                        " Night Condition: "+weatherInIterotor.getNightCondition());
                    }

                }
              if (weatherArrayList!=null){
                 WeatherAdapter weatherAdapter=new WeatherAdapter(this,weatherArrayList);
                    listView.setAdapter(weatherAdapter);
               }
               return weatherArrayList;



            } catch (JSONException e) {
                e.printStackTrace();
            }
      }
       return null;
    }
 }
