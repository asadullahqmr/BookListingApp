package com.example.booklistingapp.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.booklistingapp.models.BookInfo;
import com.example.booklistingapp.models.Rack;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookListingPageViewModel extends ViewModel {
    private MutableLiveData<List<Rack>> allRacks;
    public LiveData<List<Rack>> getAllRacks(){
        return allRacks;
    }

    public void init(Context ctxt){
        if(allRacks != null)
            return;
        else
        {
            String json = loadJsonFromAsset(ctxt);
            List<Rack> allRacksFromJson = jsonToColumn(json);
            if(allRacksFromJson == null)
                return;
            allRacks = new MutableLiveData<>();
            allRacks.setValue(allRacksFromJson);
            int i = 1+1;
        }
    }

    private List<Rack> jsonToColumn(String json){
        List<Rack> allRacksFromJson = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++)
            {
                String cover = arr.getJSONObject(i).getString("cover");
                String title = arr.getJSONObject(i).getString("title");
                BookInfo currentBook = new BookInfo();
                currentBook.setBookInfo(i, title, cover);

                //Rack aggregation:
                if(i%2 == 0) //If i is an even number (the first book of the rack)
                {
                    Rack rack = new Rack();
                    rack.setBookOne(currentBook);
                    allRacksFromJson.add(rack); //Create a new rack
                }
                else
                {
                    //Fill out the existing rack
                    BookInfo bookOne = new BookInfo();
                    bookOne.setBookInfo(allRacksFromJson.get(i/2).getBookOne());
                    Rack rack = new Rack();
                    rack.setBooks(bookOne, currentBook);
                    allRacksFromJson.set(i/2, rack);
                }
                //end rack aggregation.
            }
            return allRacksFromJson;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String loadJsonFromAsset(Context ctxt){
        String json = null;
        try {
            InputStream is = ctxt.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
