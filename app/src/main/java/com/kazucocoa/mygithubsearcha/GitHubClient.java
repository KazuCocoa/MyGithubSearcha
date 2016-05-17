package com.kazucocoa.mygithubsearcha;

import android.support.annotation.NonNull;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class GitHubClient {
    private static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";

    public GitHubClient() {
        // no
    }

    public void Github() throws Exception {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                List<Contributor> co = Contributor.contributors(body.source());
                body.close();

                Collections.sort(co, new Comparator<Contributor>() {
                    @Override
                    public int compare(Contributor lhs, Contributor rhs) {
                        return lhs.contributions - rhs.contributions;
                    }
                });

                for (Contributor contributor : co) {
                    System.out.println(contributor.login + ": " + contributor.contributions);
                    System.out.println("=======JSON==========");
                    System.out.println(Contributor.constibutorToString(contributor));

                }
            }
        });
    }
}
