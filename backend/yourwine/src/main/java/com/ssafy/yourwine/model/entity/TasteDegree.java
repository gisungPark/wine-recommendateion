package com.ssafy.yourwine.model.entity;

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
@Table(name = "TasteDegree")
public class TasteDegree {

	@Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    private Integer sweet;
    private Integer body;
    private Integer tannin;
    private Integer acidity;

}