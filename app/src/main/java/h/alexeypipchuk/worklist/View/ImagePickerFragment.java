package h.alexeypipchuk.worklist.View;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.ImageHelper;
import h.alexeypipchuk.worklist.Utility.StringsHelper;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;

public class ImagePickerFragment extends Fragment {

    private final static int GALLERY_REQUEST = 1;
    private final static int CAMERA_REQUEST = 2;
    private static final int CAMERA_PERMISSION_REQUEST = 1;

    private Uri imgUri;
    private TextView imgName;

    @Inject
    StringsHelper stringsHelper;
    @Inject
    ImageHelper imageHelper;

    public ImagePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        imgName = view.findViewById(R.id.imgName);
        view.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    setImgUri(intent.getData());
                }
                break;
            case CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    setImgUri(imageHelper.saveBitmap(bitmap));
                }
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ImagePickerFragment.CAMERA_PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }

    //region logic
    private void handleClick() {
        String[] options = stringsHelper.getImgPickerOptions();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.ImgDialog));

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        pickFromGallery();
                        break;
                    case 1:
                        pickFromCamera();
                        break;
                    default:
                        dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void pickFromCamera() {

        if (checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private void pickFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
    //endregion

    Uri getImgUri() {
        return imgUri;
    }

    void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
        setImgName(imgUri);
    }

    private void setImgName(Uri uri) {
        imgName.setText((uri != null) ?
                stringsHelper.getFileName(uri) : "");
    }
}
