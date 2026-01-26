package com.oneiros.growapp.ui.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import com.oneiros.growapp.R;
import com.oneiros.growapp.ui.viewmodel.RoomViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private RoomViewModel roomviewmodel;
    /**
     * Overrides the parent onCreate Method to: <br>
     *  - link the activity and its XML <br>
     *  - enable Edge-to-Edge drawing of the app (introduced android 10, mandatory since android 15) <br>
     *      ->bevor apps had a "safe box" between the sys bars <br>
     *  - add a listener which adds padding corresponding to the sys bars <br>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roomviewmodel = new ViewModelProvider(this).get(RoomViewModel.class);
    }
}