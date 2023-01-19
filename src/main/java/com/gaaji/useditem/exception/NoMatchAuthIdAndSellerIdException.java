package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.No_Match_Auth_Id_And_Seller_Id;

public class NoMatchAuthIdAndSellerIdException extends AbstractApiException {

	public NoMatchAuthIdAndSellerIdException() {
		super(No_Match_Auth_Id_And_Seller_Id);
	}

}
