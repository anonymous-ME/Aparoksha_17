package app.fahmi.affanafahmi.aparoksha17.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Affan Ahmad Fahmi on 5/2/17.
 */

public class Permission {
    public static final int LOCATION = 0x1;
    public static final int CALL = 0x2;
    public static final int WRITE_EXST = 0x3;
    public static final int READ_EXST = 0x4;
    public static final int CAMERA = 0x5;
    public static final int ACCOUNTS = 0x6;
    public static final int GPS_SETTINGS = 0x7;

    public boolean isPermissionGranted(Context context,int TYPE) {
        switch (TYPE) {
            case LOCATION : if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case CALL : if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PRIVILEGED) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case WRITE_EXST : if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case READ_EXST : if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case CAMERA : if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case ACCOUNTS : if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCOUNT_MANAGER) == PackageManager.PERMISSION_GRANTED)
                                return true;
                            else
                                return false;
            case GPS_SETTINGS : if (ContextCompat.checkSelfPermission(context, Manifest.permission.LOCATION_HARDWARE) == PackageManager.PERMISSION_GRANTED)
                                    return true;
                                else
                                    return false;
            default:
                return false;
        }
    }
    public void requestPermission(Context context,int TYPE) {
        switch (TYPE) {
            case LOCATION : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                break;
            case CALL : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PRIVILEGED},1);
                break;
            case WRITE_EXST : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                break;
            case READ_EXST : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                break;
            case CAMERA : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CAMERA},1);
                break;
            case ACCOUNTS : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.ACCOUNT_MANAGER},1);
                break;
            case GPS_SETTINGS : if (!isPermissionGranted(context,TYPE))
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.LOCATION_HARDWARE},1);
                break;
        }
    }
}
