package com.baseh2.baseapih2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;


import java.time.ZonedDateTime;

@Data
@Entity
@Table(name="task")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "DUEDATE")
    private ZonedDateTime dueDate;
}
