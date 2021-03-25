package com.ssafy.yourwine.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrapeDTO {
	private Long grapeId;
	private String kname;
	private String ename;
	private String detail;
}
