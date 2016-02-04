package com.jfsaaved.piccarta.main.entities;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.jfsaaved.piccarta.main.main.*;

/**
 * Created by 343076 on 01/02/2016.
 */
public class TestEntity {
    public static Camera testCamera;
    Preview testPreview;

    private boolean safeCameraOpen (int id) {
        boolean isOpened = false;

        try {
            releaseCameraAndPreview();
            testCamera = Camera.open(id);
            isOpened = (testCamera != null);
        } catch (Exception e) {
            Log.e("Piccarta", "failed to open camera");
            e.printStackTrace();
        }
        return isOpened;
    }

    private void releaseCameraAndPreview() {
        testPreview.setCamera(null);
        if (testCamera != null) {
            testCamera.release();
            testCamera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        if (holder != null) {
            // Call stopPreview() to stop updating the preview surface.
            testPreview.stopPreview();
        }
    }

    /**
     * When this function returns, testCamera will be null.
     */
    public static void stopPreviewAndFreeCamera() {

        if (testCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            testCamera.stopPreview();

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            testCamera.release();

            testCamera = null;
        }
    }
}
