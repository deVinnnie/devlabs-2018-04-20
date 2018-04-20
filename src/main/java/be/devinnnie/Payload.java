package be.devinnnie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {
    @JsonProperty("data")
    private String data;

    @JsonCreator
    public Payload(
            @JsonProperty("data") String data
    ) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
