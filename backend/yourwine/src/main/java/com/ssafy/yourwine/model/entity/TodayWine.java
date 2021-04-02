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
@Table(name = "TodayWine")
public class TodayWine {

	@Id
    @Column(name = "wine_id")
    private Long wineId;

}