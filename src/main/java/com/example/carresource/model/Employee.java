package com.example.carresource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean available;
    @Email
    private String username;
    @NotEmpty
    private String fullName;
    @OneToMany(mappedBy = "maintainer")
    @JsonIgnore
    private List<Ticket> tickets;
    private LocalDateTime hiringDate;
}
