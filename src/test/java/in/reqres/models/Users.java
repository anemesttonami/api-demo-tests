package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Users {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<ResponseData> data;
    private ResponseSupport support;

}
