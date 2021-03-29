package com.ssafy.yourwine.model.entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    @Column(name = "food_id")
    private Long foodId;
    private String name;
    private String detail;
    
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	private List<WineFoodMatch> wineFoodMatch = new ArrayList<WineFoodMatch>();
    
}