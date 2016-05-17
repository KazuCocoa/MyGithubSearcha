package com.kazucocoa.mygithubsearcha;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okio.BufferedSource;

public class Contributor {

    public static final class Data {
        Contribution data;
        List<String> errors;

        public Data(@Nullable Contribution data, @Nullable List<String> errors) {
            if (data == null) {
                this.data.login = "";
                this.data.contributions = 0;
            } else {
                this.data = data;
            }

            if (errors == null) {
                this.errors = Collections.emptyList();
            } else {
                this.errors = errors;
            }
        }
    }

    public static final class Contribution {
        String login;
        int contributions;
    }


    static public Data data(BufferedSource bufferedSource) throws IOException {
        JsonAdapter<Data> adapter =
                new Moshi.Builder().build().adapter(Data.class);
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
