package com.nvchung.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int EXTERNAL_STORAGE_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO b3 Using
        if (PermissionUtils.isStoragePermissionsGranted(this)) {
            dosomthing();
        }else {
            PermissionUtils.requestPermissions(this, EXTERNAL_STORAGE_PERMISSIONS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

        }
    }

    private void dosomthing() {

    }

    //TODO b4 getper in Request

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSIONS:
                boolean gotPermission = grantResults.length > 0;

                for (int result : grantResults) {
                    gotPermission &= result == PackageManager.PERMISSION_GRANTED;
                }

                if (gotPermission) {
                   dosomthing();
                } else {
                    Toast.makeText(this, getString(R.string.storage_permission_denied), Toast.LENGTH_LONG).show();
                    //
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
