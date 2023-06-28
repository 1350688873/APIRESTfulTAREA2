package com.example.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btmostrar (View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://jsonplaceholder.typicode.com/users"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView respuesta = (TextView) findViewById(R.id.txtmensaje);
        StringBuilder stringBuilder = new StringBuilder();
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phone = jsonObject.getString("phone");
            String email = jsonObject.getString("email");

            stringBuilder.append("Nombre: ").append(name).append("\n");
            stringBuilder.append("Phone: ").append(phone).append("\n");
            stringBuilder.append("Correo: ").append(email).append("\n\n");
        }

        respuesta.setText(stringBuilder.toString());
    }
}