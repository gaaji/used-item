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
            "중고거래 데이터 생성 과정에서 Town.address에 Null이 입력되었습니다.")

	
    ;
	
	private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
