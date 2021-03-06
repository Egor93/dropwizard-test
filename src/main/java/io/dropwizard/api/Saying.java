package io.dropwizard.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.glassfish.jersey.model.internal.RankedComparator;

@JsonPropertyOrder({"TESTSTRING,id,content"})
public class Saying {
    private long id;

    private String teststr;
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.teststr="Teststring from Saying.class";
        this.content = content;
    }

    @JsonProperty(value = "id")
    public long getId() {
        return id;
    }

    @JsonProperty(value = "TESTSTRING",index = 1)
    public String getteststr() {
        return teststr;
    }

    @JsonProperty(value = "content")
    public String getContent() {
        return content;
    }
}