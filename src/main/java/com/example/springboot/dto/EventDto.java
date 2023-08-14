package com.example.springboot.dto;

import com.example.springboot.models.Club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "startTime cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @NotNull(message = "endTime cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    @NotEmpty(message = "Type cannot be empty")
    private String type;
    @NotEmpty(message = "URL cannot be empty")
    private String photoUrl;
    private LocalDateTime createdOn;
    private  LocalDateTime updatedOn;
    private Club club;
}
