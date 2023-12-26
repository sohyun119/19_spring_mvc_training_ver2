package com.application.trainingVer2.boardAdvance.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class MainBoardDTO {

	private long boardId;
	private String writer;
	private String subject;
	private String content;
	private String passwd;
	private int readCnt;
	private Date enrollDt;
	
}
