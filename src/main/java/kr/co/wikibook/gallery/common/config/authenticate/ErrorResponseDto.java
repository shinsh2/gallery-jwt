package kr.co.wikibook.gallery.common.config.authenticate;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErrorResponseDto {
	private int httpStatus;
	private String message;
	private LocalDateTime timestamp;
}
