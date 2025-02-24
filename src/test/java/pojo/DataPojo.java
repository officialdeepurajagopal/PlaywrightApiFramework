package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPojo {
    private String title;
    private String body;
    private Integer userId;
    private String name;
    private String job;

}
