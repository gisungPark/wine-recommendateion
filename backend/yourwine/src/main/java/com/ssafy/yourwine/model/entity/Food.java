package com.ssafy.yourwine.model.entity;
import lombok.*;
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
    private Integer foodId;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String img;
    
    
}