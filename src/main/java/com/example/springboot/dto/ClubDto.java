package com.example.springboot.dto;


import com.example.springboot.models.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {


    private long id;

    @NotEmpty(message = "Club Title should not be empty")
    private String Title;

    @NotEmpty(message = "Photo Link shoud not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content shoud not be empty")
    private String content;

    private UserEntity createdBy;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
