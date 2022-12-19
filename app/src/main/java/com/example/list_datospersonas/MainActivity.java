package com.example.list_datospersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.list_datospersonas.WebServices.Asynchtask;
import com.example.list_datospersonas.WebServices.WebService;
import com.example.list_datospersonas.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView lstUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstUsuarios = (ListView)findViewById(R.id.lstUser);

        //View header = getLayoutInflater().inflate(R.layout.lyitemusers, null);
        //lstOpciones.addHeaderView(header);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista =  new JSONObject(result);
        JSONArray JSONlistaUsuarios=  JSONlista.getJSONArray("data");

        ArrayList<Usuario> LstUser = Usuario.JsonObjectsBuild(JSONlistaUsuarios);

        com.kdpr.practicaimage.adaptadores.AdaptadorUsuario adapatorUsuario = new com.kdpr.practicaimage.adaptadores.AdaptadorUsuario(this, LstUser);

        lstUsuarios.setAdapter(adapatorUsuario);

    }
}