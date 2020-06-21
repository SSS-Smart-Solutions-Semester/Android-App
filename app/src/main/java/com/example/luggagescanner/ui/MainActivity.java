package com.example.luggagescanner.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.DefaultCompany.HelloARU3D.ARCore;
import com.example.luggagescanner.data.scan.Scan;
import com.example.luggagescanner.databinding.ActivityMainBinding;
import com.example.luggagescanner.ui.home.ScanViewModel;
import com.example.luggagescanner.ui.onboarding.Onboarding;
import com.example.luggagescanner.R;
import com.example.luggagescanner.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {
    public static final String SCAN_CARD_TRANSITION_NAME = "scan_card_";
    public static final String SHOW_ONBOARDING = "show_onboarding";
    public static final int ONBOARDING_CODE = 123;
    public static final int ARCORE_CODE = 222;

    private ActivityMainBinding binding;
    private ScanViewModel scanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean showOnboarding = SharedPrefs.readBool(this, SHOW_ONBOARDING, true);

        if (showOnboarding) {
            Intent intent = new Intent(MainActivity.this, Onboarding.class);
            startActivityForResult(intent, ONBOARDING_CODE);
        }

        super.onCreate(savedInstanceState);

        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setBottomSheetForHome();

        Navigation.findNavController(this, R.id.nav_host_fragment).addOnDestinationChangedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Returned to main activity from onboarding screen
        if (requestCode == ONBOARDING_CODE) {
            SharedPrefs.saveBool(this, SHOW_ONBOARDING, false);
            Intent intent = new Intent(MainActivity.this, ARCore.class);
            startActivityForResult(intent, ARCORE_CODE);
        }
        else if (requestCode == ARCORE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                int h = data.getIntExtra(ARCore.HEIGHT, 0);
                int l = data.getIntExtra(ARCore.LENGTH, 0);
                int w = data.getIntExtra(ARCore.WIDTH, 0);
                scanViewModel.insert(new Scan(h, l, w));
            }
        }
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.homeFragment:
                setBottomSheetForHome();
                break;
            case R.id.scanFragment:
                setBottomSheetForScan();
                break;
        }
    }

    private void setBottomSheetForHome() {
        binding.bottomSheet.button.setText(R.string.button_ar);
        binding.bottomSheet.button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ARCore.class);
            startActivityForResult(intent, ARCORE_CODE);
        });
    }

    private void setBottomSheetForScan() {
        binding.bottomSheet.button.setText(R.string.button_close);
        binding.bottomSheet.button.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
        });
    }
}
