package com.moujib.dents_mobile.api;

import com.moujib.dents_mobile.beans.PW;
import com.moujib.dents_mobile.beans.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApi {

   @GET("{id}")
   Call<Student>getStudentById(@Path("id") Long id);

   @PUT("student/update/{id}")
   Call <Object> updateStudent(@Path("id") Long id, @Body Student student);


   @GET("pw/{id}")
   Call<List<PW>> getPW(@Path("id")Long id);
}
