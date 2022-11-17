package org.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class TransferPage extends CommonUtility {
	public TransferPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="MenuHyperLink3")
	private WebElement transferFunds;
	
	@FindBy(id="fromAccount")
	private WebElement fromAccount;
	
	@FindBy(id="toAccount")
	private WebElement toAccount;
	
	@FindBy(id="transferAmount")
	private WebElement txtAmount;
	
	@FindBy(id="transfer")
	private WebElement btnTransfer;
	
	@FindBy(xpath="//span[contains(@id,'Content_Main')]")
	private WebElement transferMessage;

	public WebElement getTransferFunds() {
		return transferFunds;
	}

	public WebElement getFromAccount() {
		return fromAccount;
	}

	public WebElement getToAccount() {
		return toAccount;
	}

	public WebElement getTxtAmount() {
		return txtAmount;
	}

	public WebElement getBtnTransfer() {
		return btnTransfer;
	}

	public WebElement getTransferMessage() {
		return transferMessage;
	}
	
	String amt;
	public void transferFunds() throws IOException {
		btnClick(getTransferFunds());
		selectByValue(getFromAccount(), getPropertyValue("account1"));
		selectByValue(getToAccount(), getPropertyValue("account2"));
		sendkeys(getTxtAmount(), getPropertyValue("amount"));
		 amt = getAttribute(getTxtAmount());
		btnClick(getBtnTransfer());
	}
	public boolean verifyAMountTransfered() {
		boolean res=false;
		String msg = getText(getTransferMessage());
		if(msg.contains(amt)) {
			res= true;
		}
		return res;

	}
	

}
