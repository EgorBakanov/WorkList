package h.alexeypipchuk.worklist.View;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import h.alexeypipchuk.worklist.R;
import h.alexeypipchuk.worklist.Utility.StringsHelper;

public class ImagePickerFragment extends Fragment {

    private Uri imgUri;

    private TextView imgName;

    @Inject
    StringsHelper stringsHelper;

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
        Toast.makeText(getContext().getApplicationContext(),"added",Toast.LENGTH_LONG).show();
    }

    private void handleClick() {
        String[] options = stringsHelper.getImgPickerOptions();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.ImgDialog));

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(getContext().getApplicationContext(),"gallery",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext().getApplicationContext(),"camera",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        dialog.dismiss();
                }
            }
        });
        builder.show();
    }

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
