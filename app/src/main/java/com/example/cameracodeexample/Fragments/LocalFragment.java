package com.example.cameracodeexample.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cameracodeexample.R;
import com.example.cameracodeexample.utils.DataBaseHandler;
import com.example.cameracodeexample.utils.LocalDataBaseAdapter;
import com.example.cameracodeexample.utils.LocalResponse;

import java.util.ArrayList;

public class LocalFragment extends Fragment {
    RecyclerView recyclerView;
    private DataBaseHandler myDatabase;
    private SQLiteDatabase db;
    private ArrayList<LocalResponse> singleRowArrayList;
    private LocalResponse singleRow;
    String image;
    int uid;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        myDatabase = new DataBaseHandler(getContext());
        db = myDatabase.getWritableDatabase();
        setData();
        return view;
    }

    private void setData() {
        db = myDatabase.getWritableDatabase();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        singleRowArrayList = new ArrayList<>();
        String[] columns = {DataBaseHandler.KEY_ID, DataBaseHandler.KEY_IMG_URL, DataBaseHandler.KEY_NAME, DataBaseHandler.KEY_DATE, DataBaseHandler.KEY_LOCATION};
        cursor = db.query(DataBaseHandler.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(DataBaseHandler.KEY_ID);
            int index2 = cursor.getColumnIndex(DataBaseHandler.KEY_IMG_URL);
            int index3 = cursor.getColumnIndex(DataBaseHandler.KEY_NAME);
            int index4 = cursor.getColumnIndex(DataBaseHandler.KEY_LOCATION);
            int index5 = cursor.getColumnIndex(DataBaseHandler.KEY_DATE);

            uid = cursor.getInt(index1);
            image = cursor.getString(index2);


            singleRow = new LocalResponse(image, uid, cursor.getString(index3), cursor.getString(index4), cursor.getString(index5));
            singleRowArrayList.add(singleRow);
        }
        if (singleRowArrayList.size() == 0) {
            //empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            LocalDataBaseAdapter localDataBaseResponse = new LocalDataBaseAdapter(getContext(), singleRowArrayList, db, myDatabase);
            recyclerView.setAdapter(localDataBaseResponse);
        }


    }
}
