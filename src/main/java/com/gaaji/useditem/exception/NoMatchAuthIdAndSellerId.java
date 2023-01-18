package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.No_Match_Auth_Id_And_Seller_Id;

public class NoMatchAuthIdAndSellerId extends AbstractApiException {

	public NoMatchAuthIdAndSellerId() {
		super(No_Match_Auth_Id_And_Seller_Id);
	}

}
