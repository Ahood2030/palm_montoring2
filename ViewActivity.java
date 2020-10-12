package com.example.palm_montoring.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.palm_montoring.API.APIRequestData;
import com.example.palm_montoring.API.RetroServer;
import com.example.palm_montoring.Adapter.AdapterData;
import com.example.palm_montoring.Model.DataModel;
import com.example.palm_montoring.Model.ResponseModel;
import com.example.palm_montoring.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {
    private RecyclerView rvData ;
    private  RecyclerView.Adapter adData ;
    private  RecyclerView.LayoutManager lmData ;
    private int id_del ;

    private List<DataModel> listinfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        rvData=findViewById(R.id.rv_data);
        lmData=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        rereieveData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
              id_del = Integer.parseInt(String.valueOf(listinfo.get(viewHolder.getLayoutPosition())));
              deleteData();
                Log.v("myId",id_del+"");
                Toast.makeText(ViewActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rvData);

/*
        RecyclerView recyclerView = findViewById(R.id.monitoring_recycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        View_monitoringAdapter adapter=new View_monitoringAdapter(palm_montoring.getALLPalms_montoring(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);*/
    }

    public void rereieveData(){
        APIRequestData ardData = RetroServer.conecRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData=ardData.ardRetriveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ViewActivity.this,"kode : "+kode+"| Pesan : "+pesan , Toast.LENGTH_SHORT).show();

                listinfo = response.body().getData();

                adData = new AdapterData(ViewActivity.this ,listinfo);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ViewActivity.this," problem   : " +t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void  deleteData(){
        APIRequestData ardData = RetroServer.conecRetrofit().create(APIRequestData.class);
        Call<ResponseModel> del_data=ardData.ardDeleteData(id_del);

        del_data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ViewActivity.this,"kode : "+kode+"| Pesan : "+pesan , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ViewActivity.this," problem   : " +t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
