package com.ostan.heretestapp.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marco on 16/12/2016.
 */

public class PermissionsHandler {


    IPermissionDependent permissionDependent;

    public PermissionsHandler(IPermissionDependent permissionDependent) {
        this.permissionDependent = permissionDependent;
    }

    // permissions request code
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;


    /**
     * Check and handle default permissions for provded Dependant
     * */
    public void checkPermissions(){
        if(permissionDependent == null) {
            return;
        }
        if(!isValidInput(permissionDependent.getRequiredPermissions())) {
            return;
        }

        checkPermissions(permissionDependent.getRequiredPermissions());
    }

    /**
     * Check and handle custom permissions for provided Dependant
     * @param requiredPermissions - String array ot the string names of the permissions
     * */

    public void checkPermissions(String[] requiredPermissions){

        if (!isValidInput(requiredPermissions)){
            Log.i(PermissionsHandler.class.getName(),"Invalid input for permissions checker");
            return;
        }
        final List<String> missingPermissions = new ArrayList<String>();

        // check all required dynamic permissions
        for (final String permission : requiredPermissions) {
            final int result = ContextCompat.checkSelfPermission(permissionDependent.getActivity(), permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(permissionDependent.getActivity(), permissions, REQUEST_CODE_ASK_PERMISSIONS);

        } else {
            final int[] grantResults = new int[requiredPermissions.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, requiredPermissions,
                    grantResults);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted

                        permissionDependent.requiredPermissionsMissing();
                        return;
                    }
                }
                // all permissions were granted
                permissionDependent.permissionsGranted();
                break;
        }
    }

    /**
     * Check the string array for being not null and not empty
     * */
    private boolean isValidInput(String[] input){
        if(input == null) {
            return false;
        }
        if (input.length == 0) {
            return false;
        }
        return true;
    }

    public interface IPermissionDependent {

        /**
         * Called by permissions handler when one or more permissions are missing
         * */
        public void requiredPermissionsMissing();

        /**
         * Called by permissions handler when all of the permissions are granted
         * */
        public void permissionsGranted();

        /**
         * Here PermissionsDependent provides his Activity for permission handler to function properly
         * */
        public Activity getActivity();

        /**
         * Dependent can hae his default permissions and in case he has, they can be provided here
         */
        public String[] getRequiredPermissions();
    }

}
