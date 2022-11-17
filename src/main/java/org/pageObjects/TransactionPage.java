package org.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class TransactionPage extends CommonUtility {
	public TransactionPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "MenuHyperLink2")
	private WebElement viewTransaction;

	@FindBy(xpath = "//table[contains(@id,'MyTransactions')]")
	private WebElement transactionTable;

	@FindBy(tagName = "tr")
	private List<WebElement> tableRows;

	@FindBy(tagName = "td")
	private List<WebElement> tableDatas;

	public WebElement getViewTransaction() {
		return viewTransaction;
	}

	public WebElement getTransactionTable() {
		return transactionTable;
	}

	String deposit;
	String withdraw;

	public void getFirst2Transactions() {
		btnClick(getViewTransaction());
		List<WebElement> trows = getTransactionTable().findElements(By.tagName("tr"));
		// 1st row
		List<WebElement> tdata1 = trows.get(1).findElements(By.tagName("td"));
		deposit = tdata1.get(4).getText();
		// 2nd row
		List<WebElement> tdata2 = trows.get(2).findElements(By.tagName("td"));
		withdraw = tdata2.get(4).getText().replace("-", "");

	}

	public boolean verifyTheTransactions() {
		boolean tr = false;
		if (deposit.equals(withdraw)) {
			tr = true;
		}

		return tr;

	}

}
