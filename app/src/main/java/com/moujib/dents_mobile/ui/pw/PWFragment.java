package com.moujib.dents_mobile.ui.pw;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.moujib.dents_mobile.LoginActivity;
import com.moujib.dents_mobile.MainActivity;
import com.moujib.dents_mobile.MainActivity2;
import com.moujib.dents_mobile.R;
import com.moujib.dents_mobile.adapter.AdapterPw;
import com.moujib.dents_mobile.api.RetrofitStudent;
import com.moujib.dents_mobile.api.StudentApi;
import com.moujib.dents_mobile.beans.PW;
import com.moujib.dents_mobile.beans.Student;
import com.moujib.dents_mobile.databinding.FragmentPWBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PWFragment extends Fragment {

    private FragmentPWBinding binding;

    private List<PW>  pws=new ArrayList<>();

    private ListView listView;

    private AdapterPw adapterPw;
    private long studentId;
    private ImageButton btngo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPWBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        // Accédez à la propriété studentId de MainActivity
        listView=root.findViewById(R.id.listview);





        adapterPw=new AdapterPw(pws,getContext());
     adapterPw.setOnItemClickListener(new AdapterPw.OnItemClickListener() {
         @Override
         public void onItemClick(byte[] pdfBytes, String pdfFileName) {
             downloadPdf(pdfBytes, pdfFileName);
         }
     });

        adapterPw.setOnItemClickListener2(new AdapterPw.OnItemClickListener2() {
            @Override
            public void onItemClick(long pwId) {
                // Logique à effectuer lors du clic sur le bouton "btngo"
                launchAnotherActivity(pwId);
            }
        });




        listView.setAdapter(adapterPw);

        fetchpw();


        return root;
    }
    private void launchAnotherActivity(long pwId) {
        Intent intent = new Intent(requireContext(), MainActivity2.class);
        intent.putExtra("studentId", studentId);
        intent.putExtra("pwId", pwId);
        startActivity(intent);
    }


    public void fetchpw() {
        MainActivity mainActivity = (MainActivity) requireActivity();
        studentId = mainActivity.getStudentId();

        StudentApi studentApi = RetrofitStudent.getClient().create(StudentApi.class);
        Call<List<PW>> call = studentApi.getPW(studentId);
        call.enqueue(new Callback<List<PW>>() {
            @Override
            public void onResponse(Call<List<PW>> call, Response<List<PW>> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    List<PW> pwList = response.body();
                    Log.d("pw", pwList.toString());

                    if (pwList != null) {
                        // Update the pws list and notify the adapter
                        pws.clear();
                        pws.addAll(pwList);
                        adapterPw.notifyDataSetChanged();

                        //Toast.makeText(requireActivity(), "pw ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("response", response.toString());
                    // Handle unsuccessful response
                    Toast.makeText(requireActivity(), "no pwd", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PW>> call, Throwable t) {
                // Handle failure
            }
        });
    }
    private void downloadPdf(byte[] pdfBytes, String pdfFileName) {
        // Écrivez les bytes du PDF dans un fichier temporaire
        File tempFile = new File(requireContext().getExternalCacheDir(), pdfFileName);
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(pdfBytes);
            fos.close();

            // Créez une Uri à partir du fichier temporaire
            Uri uri = FileProvider.getUriForFile(requireContext(), requireContext().getApplicationContext().getPackageName() + ".provider", tempFile);

            // Créez l'intent pour ouvrir le fichier avec l'application par défaut du système
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // Lancez l'activité avec l'intent
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez les erreurs d'écriture de fichier ici
        }
    }

    private Uri createUriFromBytes(byte[] pdfBytes, String pdfFileName) {
        try {
            // Écrivez les bytes du PDF dans un fichier temporaire
            File tempFile = File.createTempFile("temp_pdf", ".pdf", requireContext().getExternalCacheDir());
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(pdfBytes);
            fos.close();

            // Retournez l'URI du fichier temporaire
            return Uri.fromFile(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez les erreurs d'écriture de fichier ici
            return null;
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}