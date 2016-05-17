package com.kazucocoa.mygithubsearcha;

import android.support.annotation.NonNull;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

import okio.BufferedSource;

public class Contributor {

    public static final class Contribution {
        String login;
        int contributions;
    }

    static public Contributor contributor(BufferedSource bufferedSource) throws IOException {
        JsonAdapter<Contributor> adapter =
                new Moshi.Builder().build().adapter(
                        Types.newParameterizedType(String.class, Contributor.class)
                );
        return adapter.fromJson(bufferedSource);
    }

    static public List<Contribution> contributors(BufferedSource bufferedSource) throws IOException {
        JsonAdapter<List<Contribution>> adapter =
                new Moshi.Builder().build().adapter(
                        Types.newParameterizedType(List.class, Contribution.class)
                );
        return adapter.fromJson(bufferedSource);
    }

    @NonNull
    static public String constibutorToString(Contribution contributor) {
        JsonAdapter<Contribution> adapter =
                new Moshi.Builder().build().adapter(Types.newParameterizedType(Contribution.class));
        return adapter.toJson(contributor);
    }

}
