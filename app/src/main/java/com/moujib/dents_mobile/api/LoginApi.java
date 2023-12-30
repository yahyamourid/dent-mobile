package com.moujib.dents_mobile.api;


import com.moujib.dents_mobile.beans.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("student/login")
    Call<Student> login(@Body Student student);
}
