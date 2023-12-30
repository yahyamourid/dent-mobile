package com.moujib.dents_mobile.api;

import com.moujib.dents_mobile.beans.StudentPW;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AngleRetrofit {




    @POST("add/{sid}/{pid}")
    Call<StudentPW> create(@Path("sid") Long studentId, @Path("pid") Long pwId, @Query("studentId") Long queryStudentId, @Query("pwId") Long queryPwId, @Body StudentPW studentPW);
}
