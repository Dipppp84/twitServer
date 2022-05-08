package ua.alextwit.twitserver.model.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostsAndHeartsDto {
    @Expose
    Long posts;
    @Expose
    Long hearts;
}
