package com.moujib.dents_mobile.ui.student;

import static android.companion.CompanionDeviceManager.RESULT_OK;
import static android.content.Context.CONTEXT_INCLUDE_CODE;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.moujib.dents_mobile.LoginActivity;
import com.moujib.dents_mobile.MainActivity;
import com.moujib.dents_mobile.R;
import com.moujib.dents_mobile.api.LoginApi;
import com.moujib.dents_mobile.api.RetrofitStudent;
import com.moujib.dents_mobile.api.StudentApi;
import com.moujib.dents_mobile.beans.Student;
import com.moujib.dents_mobile.databinding.NavStudentBinding;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentFragment extends Fragment {

    private StudentViewModel mViewModel;
    private ImageButton imageButton;

    private ImageView imageView;

    private Button btnupdate;
    private Bitmap selectedBitmap;

    private NavStudentBinding binding;
    private TextInputEditText emailEditText, fnomEditText, lnomEditText, numberEditText;
    Student student;


    private long studentId;


    public static StudentFragment newInstance(long studentId) {
        StudentFragment fragment = new StudentFragment();
        Bundle args = new Bundle();
        args.putLong("studentId", studentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NavStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Error likely occurs here
        imageButton = root.findViewById(R.id.btnimg);
        imageView = root.findViewById(R.id.img);

        btnupdate=root.findViewById(R.id.btnupdate);
        MainActivity mainActivity = (MainActivity) requireActivity();


        TextInputLayout emailLayout = root.findViewById(R.id.email);
        TextInputLayout fnomLayout = root.findViewById(R.id.fnom);
        TextInputLayout lnomLayout = root.findViewById(R.id.lnom);
        TextInputLayout numberLayout = root.findViewById(R.id.number);

        emailEditText = (TextInputEditText) emailLayout.getEditText();
        fnomEditText = (TextInputEditText) fnomLayout.getEditText();
        lnomEditText = (TextInputEditText) lnomLayout.getEditText();
        numberEditText = (TextInputEditText) numberLayout.getEditText();



         btnupdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 updateStudent();

             }
         });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                choosePhoto();
            }
        });
        fetchStudent();

        return root;
    }



    public void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,CONTEXT_INCLUDE_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONTEXT_INCLUDE_CODE && resultCode == RESULT_OK && data != null) {
            // L'utilisateur a choisi une image depuis la galerie
            imageView.setVisibility(View.VISIBLE);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), data.getData());
                selectedBitmap = bitmap;
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateStudent() {
        Student student1 = new Student();
        student1.setEmail(emailEditText.getText().toString());
        student1.setLast_name(lnomEditText.getText().toString());
        student1.setFirst_name(fnomEditText.getText().toString());
        student1.setNumber(numberEditText.getText().toString());

        // Vérifier si une nouvelle image a été sélectionnée
        if (selectedBitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Convertir les bytes de l'image en une chaîne Base64
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            student1.setPhoto(encodedImage);
        } else {
            // Si aucune nouvelle image n'a été sélectionnée, utilisez l'ancienne photo existante
            if (student != null && student.getPhoto() != null) {
                student1.setPhoto(student.getPhoto());
            }
        }

        StudentApi studentApi = RetrofitStudent.getClient().create(StudentApi.class);
        Call<Object> call = studentApi.updateStudent(studentId, student1);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object message = response.body();
                    Toast.makeText(requireActivity(), "Update successful: ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // Handle failure...
                Log.d("erreur", t.toString());
                Toast.makeText(requireActivity(), "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }







    private void fetchStudent() {
        StudentApi studentApi = RetrofitStudent.getClient().create(StudentApi.class);
        MainActivity mainActivity = (MainActivity) requireActivity();

        // Accédez à la propriété studentId de MainActivity
         studentId = mainActivity.getStudentId();

        Call<Student> call = studentApi.getStudentById(studentId);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()) {
                    // Handle successful login response
                    student = response.body();
                    Log.d("responsevvvvvvvvvvvvv", student.getEmail());
                    if (student != null) {
                        // Student exists, populate the form
                        populateFormWithStudentData();

                        Toast.makeText(requireActivity(), "Fetch data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("responsestttttttttttttttttt", response.toString());
                    // Handle unsuccessful login response
                    Toast.makeText(requireActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("erreur", t.toString());
                // Handle failure (e.g., network issues)
                Toast.makeText(requireActivity(), "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // La permission a été accordée
        } else {
            // La permission a été refusée
            Toast.makeText(requireContext(), "Permission refusée pour accéder au stockage externe", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        if (byteArray != null) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }

    private void populateFormWithStudentData() {
        if (emailEditText != null) emailEditText.setText(student.getEmail());
        if (fnomEditText != null) fnomEditText.setText(student.getFirst_name());
        if (lnomEditText != null) lnomEditText.setText(student.getLast_name());
        if (numberEditText != null) numberEditText.setText(String.valueOf(student.getNumber()));

        // Décoder la chaîne base64 en tableau d'octets
        if (student.getPhoto() != null) {
            byte[] photoBytes = Base64.decode(student.getPhoto().substring(student.getPhoto().indexOf(",") + 1), Base64.DEFAULT);
            imageView.setImageBitmap(convertByteArrayToBitmap(photoBytes));
        }
    }




}