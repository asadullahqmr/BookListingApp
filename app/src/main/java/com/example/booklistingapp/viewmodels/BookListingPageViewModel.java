package com.example.booklistingapp.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Patterns;
import com.example.booklistingapp.models.BookInfo;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookListingPageViewModel extends ViewModel {
    private MutableLiveData<List<BookInfo>> allBooksColumn1;
    public LiveData<List<BookInfo>> getAllBooksColumn1(){
        return allBooksColumn1;
    }
    private MutableLiveData<List<BookInfo>> allBooksColumn2;
    public LiveData<List<BookInfo>> getAllBooksColumn2(){
        return allBooksColumn2;
    }

    public void init(Context ctxt){
        if(allBooksColumn1 != null && allBooksColumn2 != null)
            return;
        else
        {
            String json = loadJsonFromAsset(ctxt);
            List<BookInfo> column = jsonToColumn(1, json);
            if(column == null)
                return;
            allBooksColumn1 = new MutableLiveData<>();
            allBooksColumn1.setValue(column);
            String json2 = loadJsonFromAsset(ctxt);
            List<BookInfo> column2 = jsonToColumn(2, json2);
            if(column == null)
                return;
            allBooksColumn2 = new MutableLiveData<>();
            allBooksColumn2.setValue(column2);
            MutableLiveData<List<BookInfo>> test1 = allBooksColumn1;
            MutableLiveData<List<BookInfo>> test2 = allBooksColumn2;
            int i = 1+1;
        }
    }

    public LiveData<List<BookInfo>> getColumn1(){
        return allBooksColumn1;
    }

    public LiveData<List<BookInfo>> getColumn2(){
        return allBooksColumn2;
    }

    private List<BookInfo> jsonToColumn(int columnNo, String json){
        List<BookInfo> column = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++)
            {
                //Column aggregation:
                if(columnNo == 1) //Take the even books (e.g. 0,2,4,etc.)
                {
                    if(i%2 == 0) //If i is an even number
                    {} //Proceed with the loop and take this index for the column
                    else
                        continue; //Skip this index
                }
                else if (columnNo == 2) //Take the odd books (e.g. 1,3,5,etc.)
                {
                    if(i%2 == 0) //If i is an even number
                        continue; //Skip this index
                    else
                    {} //Proceed with the loop and take this index for the column
                }
                else
                    return null; //More columns not supported.
                //end column aggregation.

                String cover = arr.getJSONObject(i).getString("cover");
                String title = arr.getJSONObject(i).getString("title");
                BookInfo bi = new BookInfo(title, cover);
                column.add(bi);
            }
            return column;
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
