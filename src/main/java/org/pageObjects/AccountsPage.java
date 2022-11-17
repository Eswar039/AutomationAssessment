package org.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class AccountsPage extends CommonUtility {

	public AccountsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "MenuHyperLink1")
	private WebElement accountSummary;

	@FindBy(id = "listAccounts")
	private WebElement accountList;

	@FindBy(id = "btnGetAccount")
	private WebElement goButton;

	@FindBy(xpath = "//td[contains(text(),'Available balance')]//following-sibling::td")
	private WebElement availableBalance;

	@FindBy(xpath = "//td[contains(text(),'Ending balance')]//following-sibling::td")
	private WebElement endingBalance;

	public WebElement getAccountSummary() {
		return accountSummary;
	}

	public WebElement getAccountList() {
		return accountList;
	}

	public WebElement getGoButton() {
		return goButton;
	}

	public WebElement getAvailableBalance() {
		return availableBalance;
	}

	public WebElement getEndingBalance() {
		return endingBalance;
	}

	// -------------------------------------------------------//
	public void selectAccoun() throws IOException {
		btnClick(getAccountSummary());
		selectByValue(getAccountList(), getPropertyValue("account2"));
		btnClick(getGoButton());
	}

	public boolean verifyAVailableBalance() {
		String avial = getText(getAvailableBalance());
		String ending = getText(getEndingBalance());
		if (avial.equals(ending)) {
			return true;
		}
		return false;
	}

}
