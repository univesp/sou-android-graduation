package com.soares.leonardo.soucolacaov1.testefragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.soares.leonardo.soucolacaov1.JsonClasses.DataList;
import com.soares.leonardo.soucolacaov1.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragDashboard extends Fragment {
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    int  id_test=0;
    //CONTADOR UTILIZADO PARA AVANÇAR OU DIMINUIR

    int positonStudents=0;
    public FragDashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag_dashboard, container, false);
        final TextView txtRA = (TextView) view.findViewById(R.id.txtRA);
        final TextView txtCurso = (TextView) view.findViewById(R.id.txtCurso);
        final TextView txtRG = (TextView) view.findViewById(R.id.txtRG);
        final TextView txtNome = (TextView) view.findViewById(R.id.txtNome);
        final Button next = (Button) view.findViewById(R.id.iv_next_student);

        final ImageView previous = (ImageView) view.findViewById(R.id.iv_previous_student);

        final Button previous = (Button) view.findViewById(R.id.iv_previous_student);



        mSignaturePad = (SignaturePad) view.findViewById(R.id.signaturePad);


        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(getActivity(), "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });


        mClearButton = (Button) view.findViewById(R.id.clearButton);
        mSaveButton = (Button) view.findViewById(R.id.saveButton);


        //LIMPAR O CANVAS
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });
        //EVENTO DO BOTÃO DE PROXIMO
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundles = getArguments();


                //PEGANDO OS VALORES PASSADO DA FRAGMENT (FragAlunos)
                ArrayList<String> arrayListId = bundles.getStringArrayList("array_id");
                ArrayList<String> arrayListNome = bundles.getStringArrayList("array_nome");
                ArrayList<String> arrayListRa = bundles.getStringArrayList("array_ra");
                ArrayList<String> arrayListRg = bundles.getStringArrayList("array_rg");
                ArrayList<String> arrayListCursos = bundles.getStringArrayList("array_curso");


                //ID DO ALUNO QUE FOI SELECIONADO NA FRAGMENT(FragAlunos)

                String idCurrentStudent = (bundles.getString("id").toString());
//                int test = Integer.parseInt((bundles.getString("id")));
                //  Toast.makeText(getActivity(), test, Toast.LENGTH_SHORT).show();

              String  idCurrentStudent = (bundles.getString("id"));
//                int test = Integer.parseInt((bundles.getString("id")));
                //  Toast.makeText(getActivity(), String.valueOf(idCurrentStudent), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), idCurrentStudent, Toast.LENGTH_SHORT).show();


                //PERCORRENDO O ARRAY
                for (int i = 0; i <= arrayListId.size() - 1; i++) {


                    //VERIFICANDO SE A POSSICÇAO DO ALUNO ESTA IGUAL AO INDICE DO ARRAY
                    if (idCurrentStudent.equals(arrayListId.get(i))) {

                        id_test=id_test+i+1;





                        //id_test=i+1;

                       // idCurrentStudent=+1;

                       // int ts =i+1;

                   //     Toast.makeText(getActivity(), idCurrentStudent, Toast.LENGTH_SHORT).show();



//String t =arrayListNome.get(i+1);
                        //    Toast.makeText(getActivity(), arrayListId.get(i+1), Toast.LENGTH_SHORT).show();

                        // String teste =arrayListId.get(i+1);

                        // positonStudents += i+1;


                        //Toast.makeText(getActivity(), teste, Toast.LENGTH_SHORT).show();


                        txtNome.setText(arrayListNome.get(id_test));
                        txtCurso.setText(arrayListCursos.get(id_test));
                        txtRA.setText(arrayListRa.get(id_test));
                        txtRG.setText(arrayListRg.get(id_test));


//                        if (positonStudents == arrayListId.size() - 1) {
//
//                            next.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    //  Toast.makeText(getActivity(), positonStudents, Toast.LENGTH_SHORT).show();
//                                  Toast.makeText(getActivity(), "Lista de alunos finalizada!!", Toast.LENGTH_SHORT).show();
//
//                                }
//                            });
//
//                        }

                    }
                    // positonStudents=0;


                        //txtNome.setText(arrayListNome.get(ts));
                      //  txtCurso.setText(arrayListCursos.get(ts));
                       // txtRA.setText(arrayListRa.get(ts));
                      //  txtRG.setText(arrayListRg.get(ts));


                        if (positonStudents == arrayListId.size() - 1) {

                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //  Toast.makeText(getActivity(), positonStudents, Toast.LENGTH_SHORT).show();
                                  Toast.makeText(getActivity(), "Lista de alunos finalizada!!", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                    }
                    // positonStudents=0;


                }

            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundles = getArguments();


                ArrayList<String> arrayListId = bundles.getStringArrayList("array_id");
                ArrayList<String> arrayListNome = bundles.getStringArrayList("array_nome");
                ArrayList<String> arrayListRa = bundles.getStringArrayList("array_ra");
                ArrayList<String> arrayListRg = bundles.getStringArrayList("array_rg");
                ArrayList<String> arrayListCursos = bundles.getStringArrayList("array_curso");

//


                String idCurrentStudent = (bundles.getString("id").toString());


                for (int i = 0; i <= arrayListId.size() - 1; i++) {


                    if (idCurrentStudent.equals(arrayListId.get(i))) {


                        int positonStudents = i + 1;


                        txtNome.setText(arrayListNome.get(positonStudents));
                        txtCurso.setText(arrayListCursos.get(positonStudents));
                        txtRA.setText(arrayListRa.get(positonStudents));
                        txtRG.setText(arrayListRg.get(positonStudents));


                        if (positonStudents == 0) {

                            previous.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), "Lista de alunos finalizada!!", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                    }

                }

            }
        });


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //PEGANDO A ASSINATURA E SALVANDO EM BITMAP
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                Toast.makeText(getActivity(), signatureBitmap.toString(), Toast.LENGTH_SHORT).show();


                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(getActivity(), "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(getActivity(), "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                }
            }
        });


        DataList d = new DataList();
        Bundle bundle = getArguments();

        if (bundle.getString("nome_selecionado") != null) {
            // Toast.makeText(getActivity(), "Cannot write images to external storage", Toast.LENGTH_SHORT).show();

            txtNome.setText(bundle.getString("nome_selecionado"));
            txtCurso.setText(bundle.getString("curso"));
            txtRA.setText(bundle.getString("ra"));
            txtRG.setText(bundle.getString("rg"));

        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    // SALVA O BITMAP EM JPG
    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    //SALVA O JPG NA GALERIA
    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("Assinatura"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }


    }


}
