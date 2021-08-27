package org.innovds.contact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Countries")
public class Country extends AbstractPersistable<Long> {

    @NotBlank
    private String name;
    private String iso3;
    private String iso2;
    private Integer numeric_code;
    private String phone_code;
    private String capital;
    private String currency;
    private String currency_symbol;
    private String tld;
    private String native_name;
    private String region;
    private String subregion;
    private String timezones;
    private String translations;
    private Long latitude;
    private Long longitude;
    private String emoji;
    private String emojiU;
    private DateTime created_at;
    private DateTime updated_at;
    private Short flag;
    private String wikiDataId;


    @OneToMany(mappedBy = "country")
    Set<City> cities = new HashSet<>();
}


