package com.ssafy.yourwine.model.entity;

import com.ssafy.yourwine.model.key.FlavorKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DislikeFlavor")
public class DislikeFlavor {

    @EmbeddedId
    @Column(name = "dislike_id", unique = true)
    private FlavorKey dislikeId = new FlavorKey();

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flavor_id", insertable=false, updatable=false)
    private Flavor flavor;

}