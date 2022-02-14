package io.dropwizard.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"DBQueryN,content"})
public class DBoutDisplayer {
    private int counter;
    private String teststr;
    private List<String> content;

    public DBoutDisplayer(long counter, List<String> content)
    {
        // Jackson deserialization
        this.counter = (int) counter;
        this.content = content;
    }


    @JsonProperty(value = "DBQueryN")
    public long getId() {
        return counter;
    }

    /*
    @JsonProperty(value = "TESTSTRING",index = 1)
    public String getteststr() {
        return teststr;
    }
*/

    @JsonProperty(value = "content")
    public List<String> getContent() {
        return content;
    }
}