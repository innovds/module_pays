package org.innovds.pays.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "country_id"})}, name= "States")
public class State extends AbstractPersistable<Long> {

    @NotBlank
    private String name;
    private String fips_code;
    private String iso2;
    private Long latitude;
    private Long longitude;
    private Date created_at;
    private Date updated_at;
    private Short flag;
    private String wikiDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnore()
    private org.innovds.pays.domain.Country country;
}


