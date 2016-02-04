package com.jfsaaved.piccarta.main.main;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import static com.jfsaaved.piccarta.main.entities.TestEntity.stopPreviewAndFreeCamera;
import static com.jfsaaved.piccarta.main.entities.TestEntity.testCamera;

public abstract class Preview extends ViewGroup implements SurfaceHolder.Callback {
    SurfaceView testSurfaceView;
    SurfaceHolder testHolder;
    List<Camera.Size> testSupportedPreviewSizes;

    Preview(Context context) {
        super(context);

        testSurfaceView = new SurfaceView(context);
        addView(testSurfaceView);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        testHolder = testSurfaceView.getHolder();
        testHolder.addCallback(this);
        testHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void setCamera(Camera camera) {
        if (testCamera == camera) { return; }

        stopPreviewAndFreeCamera();

        testCamera = camera;

        if (testCamera != null) {
            List<Camera.Size> localSizes = testCamera.getParameters().getSupportedPreviewSizes();
            testSupportedPreviewSizes = localSizes;
            requestLayout();

            try {
                testCamera.setPreviewDisplay(testHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Important: Call startPreview() to start updating the preview
            // surface. Preview must be started before you can take a picture.
            testCamera.startPreview();
        }
    }

    public void stopPreview (){
        testCamera.stopPreview();
    }
}