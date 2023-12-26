package com.application.trainingVer2.boardAdvance.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyDTO {

	private long replyId;
	private String writer;
	private String content;
	private String passwd;
	private Date enrollDt;
	private long boardId;
	
}
