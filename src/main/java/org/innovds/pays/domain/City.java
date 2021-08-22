package org.innovds.contact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "state_id"})})
public class City extends AbstractPersistable<Long> {

    @NotBlank
    private String name;
    private Long latitude;
    private Long longitude;
    private DateTime created_at;
    private DateTime updated_at;
    private Short flag;
    private String wikiDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @JsonIgnore()
    private State state;
}


