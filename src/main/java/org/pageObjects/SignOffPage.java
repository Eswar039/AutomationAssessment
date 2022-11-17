package org.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class SignOffPage extends CommonUtility{

	public SignOffPage() {
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="LoginLink")
	private WebElement signOffButton;

	public WebElement getSignOffButton() {
		return signOffButton;
	}
	
	
	public void clickSignOff() {
		btnClick(signOffButton);

	}
	
	public boolean verifySignOff() {
		String text = getText(getSignOffButton());
		boolean res=false;
		if(text.equals("Sign In")) {
			res=true;
		}
		return res;

	}
}
