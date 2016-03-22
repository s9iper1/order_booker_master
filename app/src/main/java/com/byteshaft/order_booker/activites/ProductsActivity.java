package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;


public class ProductsActivity extends AppCompatActivity {

    private GridView mGridView;
    private int[] imageId = {
            R.drawable.shawarma,
            R.drawable.hollywood,
            R.drawable.latour,
            R.drawable.choueiry,
            R.drawable.subz,
            R.drawable.falafelelbeyt,
            R.drawable.ricardo_snack,
            R.drawable.bur,
            R.drawable.poulet,
            R.drawable.charboul,
            R.drawable.tanbakji,
            R.drawable.croissant,
            R.drawable.pizzaria,
            R.drawable.sandwitchs,
            R.drawable.dagher,
            R.drawable.adokit,
            R.drawable.dip,
            R.drawable.mrgrill,



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        CustomGrid adapter = new CustomGrid(ProductsActivity.this, imageId);
        mGridView = (GridView) findViewById(R.id.grid);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(position);
                AppGlobals.setCurrentSelectedStore(getItemNameFromPosition(position));
                startActivity(new Intent(getApplicationContext(), ProductsDetailActivity.class));
            }
        });
    }

    private String getItemNameFromPosition(int position) {
        switch (position) {
            case 0:
                return "shawarma";
            case 1:
                return "hollywood";
            case 2:
                return "latour";
            case 3:
                return "Choueiry";
            case 4:
                return "Subz";
            case 5:
                return "falafel el beyt";
            case 6:
                return "ricardo_snack";
            case 7:
                return "burger_house";
            case 8:
                return "le_poulet";
            case 9:
                return "snack_charboul";
            case 10:
                return "tan bakji";
            case 11:
                return "croissant";
            case 12:
                return "pizzaria";
            case 13:
                return "Adonis";
            case 14:
                return "dagher";
            case 15:
                return "adokit";
            case 16:
                return "dip N dip";
            case 17:
                return "mrgrill";



                    }
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(getApplicationContext(), PreMainActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), PreMainActivity.class);
        startActivity(intent);
    }
}
