package com.jfsaaved.piccarta.main;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;

import com.jfsaaved.piccarta.R;

//import com.jfsaaved.piccarta.R;


/**
 * Created by Joseph on 2016-01-31.
 */
public class CameraControl {
    static Camera mCamera;
    Preview mPreview;

    private boolean safeCameraOpen(int id,Context context) {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            mCamera = Camera.open(id);
            qOpened = (mCamera != null);
        } catch (Exception e) {
            Log.e(context.getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCameraAndPreview() {

        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }


    /**
     * When this function returns, mCamera will be null.
     */
    protected static void stopPreviewAndFreeCamera() {

        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            mCamera.release();

            mCamera = null;
        }
    }
}
