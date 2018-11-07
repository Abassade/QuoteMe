package com.example.abass.quoteme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class QuoteActivity extends AppCompatActivity {
    private static final String TAG = "QuoteActivity";

    TextView quote_edt, name_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        quote_edt = findViewById(R.id.quote);
        name_edt = findViewById(R.id.name);

        Intent intent = getIntent();
        String quote_fetch = intent.getExtras().getString("quote");
        String name_fetch = intent.getExtras().getString("name");

        Log.i(TAG, quote_fetch);
        Log.i(TAG, name_fetch);

        quote_edt.setText(quote_fetch);
        name_edt.setText(name_fetch);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bitmap bitmap = loadBitmapFromView();
        int id = item.getItemId();
        switch (id){
            case R.id.save_quote:
                saveTheQuote(bitmap);
                if(saveTheQuote(bitmap)){
                    Toast.makeText(getBaseContext(), "Quote Saved to QuoteMe Folder ", Toast.LENGTH_LONG).show();
                }
                else{ Toast.makeText(getBaseContext(), "Quote not Saved ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.share_quote:
                shareTheQuote();
                break;
                default:return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareTheQuote() {
    }

    private Boolean saveTheQuote(Bitmap bitmap) {

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/QuoteMe");
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String fname = "Image-" + n + ".jpg";
            File file = new File(myDir, fname);
            //  Log.i(TAG, "" + file);
            if (file.exists()){
                file.delete();}
//        if (!file.exists()) {
//            file.getParentFile().mkdirs();
//        }
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
                MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            } catch (Exception e) {
                e.printStackTrace();

        }
        return true;
    }

    public Bitmap loadBitmapFromView() {
        View rootView = findViewById(R.id.ground_view);
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
}
