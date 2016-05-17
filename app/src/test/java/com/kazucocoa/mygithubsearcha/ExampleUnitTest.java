package com.kazucocoa.mygithubsearcha;

import org.junit.Test;

import java.util.List;

import okio.Buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void contributorsTest() throws Exception {
        String json = "[{\"login\": \"my\", \"contributions\": 1}]";
        List<Contributor.Contribution> c = Contributor.contributors(new Buffer().writeUtf8(json));
        assertThat(c.get(0).login, is("my"));
    }
}