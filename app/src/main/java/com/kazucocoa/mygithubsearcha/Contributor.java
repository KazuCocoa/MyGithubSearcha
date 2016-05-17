package com.kazucocoa.mygithubsearcha;

import android.support.annotation.NonNull;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

import okio.BufferedSource;

public class Contributor {
    String login;
    int contributions;



    static public Contributor contributor(BufferedSource bufferedSource) throws IOException {
        JsonAdapter<Contributor> adapter =
                new Moshi.Builder().build().adapter(
                        Types.newParameterizedType(String.class, Contributor.class)
                );
        return adapter.fromJson(bufferedSource);
    }

    static public List<Contributor> contributors(BufferedSource bufferedSource) throws IOException {
        JsonAdapter<List<Contributor>> adapter =
                new Moshi.Builder().build().adapter(
                        Types.newParameterizedType(List.class, Contributor.class)
                );
        return adapter.fromJson(bufferedSource);
    }

    @NonNull
    static public String constibutorToString(Contributor contributor) {
        JsonAdapter<Contributor> adapter =
                new Moshi.Builder().build().adapter(Types.newParameterizedType(Contributor.class));
        return adapter.toJson(contributor);
    }

}
