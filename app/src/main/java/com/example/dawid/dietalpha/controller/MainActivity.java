package com.example.dawid.dietalpha.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dawid.dietalpha.R;
import com.example.dawid.dietalpha.model.ItemData;
import com.example.dawid.dietalpha.model.JSONParser;
import com.example.dawid.dietalpha.model.SubstituteAdapter;
import com.example.dawid.dietalpha.model.VolleySingleton;

import org.json.JSONObject;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements SubstituteAdapter.OnAddButtonClickedListener {

    public static final int PICK_BASE = 1;
    public static final int PICK_COUN = 2;
    private Toolbar tbr;

    private TextView tvBaseName;
    private TextView tvBaseAmount;
    private TextView tvBaseCal;
    private TextView tvBasePro;
    private TextView tvBaseFat;
    private TextView tvBaseCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BASE VIEW
        tvBaseName = (TextView)findViewById(R.id.tvBaseName);
        tvBaseAmount = (TextView)findViewById(R.id.tvBaseAmount);
        tvBaseCal = (TextView)findViewById(R.id.tvBaseCal);
        tvBaseCar = (TextView)findViewById(R.id.tvBaseCar);
        tvBaseFat = (TextView)findViewById(R.id.tvBaseFat);
        tvBasePro = (TextView)findViewById(R.id.tvBasePro);


        //TOOLBAR
        tbr = (Toolbar)findViewById(R.id.toolbar);
        tbr.setTitle("Wyszukaj produkt bazowy: ");
        setSupportActionBar(tbr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            startActivityForResult(new Intent(this, SelectProductActivity.class), PICK_BASE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.PICK_BASE) {
            Log.d("SUCCESS", "CODE OK");
            if (resultCode == RESULT_OK) {
                String id = data.getStringExtra("id");
                final int weigth = data.getIntExtra("amount", -1);

                RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
                JsonObjectRequest requestJSON = new JsonObjectRequest(Request.Method.GET, "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=fV2CXoFnfQ2x6eZCwIEinskdRaiPmj8WpqtjPKIx&nutrients=203&nutrients=204&nutrients=205&nutrients=208&ndbno="+id
                        , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ItemData res = JSONParser.parseNutrients(jsonObject, weigth);
                        tvBaseName.setText(res.getName());
                        tvBaseCal.setText(String.format("%.2f", res.getCal()) + "kcal/");
                        tvBaseCar.setText("wegle: " + String.format("%.2f", res.getCarbo()) + "g");
                        tvBaseFat.setText("tluszcze: " + String.format("%.2f", res.getFat()) + "g");
                        tvBasePro.setText("proteiny: " + String.format("%.2f", res.getPro()) + "g");
                        tvBaseAmount.setText(res.getWeigth() + "g");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(requestJSON);
            }
        }
    }

    @Override
    public void onAddNewItemButtonClicked() {
        Toast.makeText(this, "Adding new item", Toast.LENGTH_SHORT).show();
    }
}
