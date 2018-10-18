package kh.edu.rupp.ckcc.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private SimpleDraweeView imgProfile;
    private TextView txtName;
    private TextView txtMajor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        imgProfile = findViewById(R.id.img_profile);
        txtName = findViewById(R.id.txt_name);
        txtMajor = findViewById(R.id.txt_major);

        // Load profile from web service
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://test.js-cambodia.com/ckcc/profile.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // Method 1: using json object
                /*
                try {
                    String name = response.getString("name");
                    String major = response.getString("major");
                    txtName.setText(name);
                    txtMajor.setText(major);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */

                // Method 2: using Gson library
                Gson gson = new Gson();
                Profile profile = gson.fromJson(response.toString(), Profile.class);
                imgProfile.setImageURI(profile.getImageUrl());
                txtName.setText(profile.getName());
                txtMajor.setText(profile.getMajor());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "Load data error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

    }
}
