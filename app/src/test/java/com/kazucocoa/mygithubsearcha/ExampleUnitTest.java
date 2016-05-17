package com.kazucocoa.mygithubsearcha;

import org.junit.Test;

import java.util.List;

import okio.Buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void contributorsTest() throws Exception {
        String json = "[{\"login\": \"my\", \"contributions\": 1}]";
        List<Contributor.Contribution> c = Contributor.contributors(new Buffer().writeUtf8(json));
        assertThat(c.get(0).login, is("my"));
    }

    @Test
    public void contributorsTestInData() throws Exception {
        String json = "{\"data\": {\"login\": \"my\", \"contributions\": 1}}";
        Contributor.Data d = Contributor.data(new Buffer().writeUtf8(json));
        assertThat(d.data, is(notNullValue()));
        assertThat(d.errors, is(nullValue()));
        assertThat(d.data.login, is("my"));
    }

    @Test
    public void errorsTestInData() throws Exception {
        String json = "{\"errors\": [\"error1\", \"error2\"]}";
        Contributor.Data d = Contributor.data(new Buffer().writeUtf8(json));
        assertThat(d.data, is(nullValue()));
        assertThat(d.errors, is(notNullValue()));
        assertThat(d.errors.get(0), is("error1"));
        assertThat(d.errors.get(1), is("error2"));
    }
}