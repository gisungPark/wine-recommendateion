package com.ssafy.yourwine.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TopTen")
public class TopTen {

	@Id
    @Column(name = "wine_id")
    private Long wineId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Wine wine;

    private double score;
    private Integer price;

}