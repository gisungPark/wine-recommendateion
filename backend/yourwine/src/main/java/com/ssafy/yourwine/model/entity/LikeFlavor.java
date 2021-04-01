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
@Table(name = "LikeFlavor")
public class LikeFlavor {

    @EmbeddedId
    @Column(name = "like_id", unique = true)
    private FlavorKey likeId = new FlavorKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flavor_id", insertable=false, updatable=false)
    private Flavor flavor;

}