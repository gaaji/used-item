package com.gaaji.useditem.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UsedItemErrorCode implements ErrorCode {

    INPUT_NULL_DATA_ON_POST_ID(HttpStatus.INTERNAL_SERVER_ERROR, "u-0001",
            "중고거래 데이터 생성 과정에서 PostId에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_SELLER_ID(HttpStatus.INTERNAL_SERVER_ERROR, "u-0002",
            "중고거래 데이터 생성 과정에서 sellerId에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_TITLE(HttpStatus.INTERNAL_SERVER_ERROR, "u-0003",
            "중고거래 데이터 생성 과정에서 Post.Title에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_CATEGORY(HttpStatus.INTERNAL_SERVER_ERROR, "u-0004",
            "중고거래 데이터 생성 과정에서 Post.Category에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_PRICE(HttpStatus.INTERNAL_SERVER_ERROR, "u-0005",
            "중고거래 데이터 생성 과정에서 Price에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_TOWN_ID(HttpStatus.INTERNAL_SERVER_ERROR, "u-0006",
            "중고거래 데이터 생성 과정에서 Town.id에 Null이 입력되었습니다."),

    INPUT_NULL_DATA_ON_ADDRESS(HttpStatus.INTERNAL_SERVER_ERROR, "u-0007",
            "중고거래 데이터 생성 과정에서 Town.address에 Null이 입력되었습니다."),

    NO_SEARCH_POST_EXCEPTION(HttpStatus.NOT_ACCEPTABLE, "u-0008",
    		"해당하는 중고거래글을 찾지 못했습니다."),
    NO_MATCH_AUTH_ID_AND_SELLER_ID(HttpStatus.UNAUTHORIZED, "u-0009",
    		"유저 정보(Id)가 작성자 정보(Id)와 일치하지 않습니다."),
    TOWN_UNAUTHENTICATED(HttpStatus.FORBIDDEN, "u-0010",
            "동네가 인증되지 않았습니다."),
    JSON_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "u-0011",
            "JSON 파싱 과정에서 오류가 발생"),


    No_Search_Post_Counter_Exception(HttpStatus.NOT_ACCEPTABLE, "u-0012",
            "해당하는 중고거래 조회 정보를 찾지 못했습니다."),
    RESERVATION_STATUS_CHANGE_PRICE(HttpStatus.BAD_REQUEST,"u-0013","예약 중엔 가격을 변경할 수 없습니다."),

    BOTH_SIZE_DOSE_NOT_MATCHED(HttpStatus.BAD_REQUEST,"u-0014","인덱스의 개수와 파일의 개수가 맞지 않습니다."),
    INDEX_OUT_OF_BOUNDS(HttpStatus.BAD_REQUEST,"u-0015","인덱스가 범위를 초과했습니다.")
    ,

    ;
	
	private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
