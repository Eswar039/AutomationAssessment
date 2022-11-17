package org.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class ContactPage extends CommonUtility {
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "HyperLink3")
	private WebElement contactUslink;

	@FindBy(xpath = "//a[text()='online form']")
	private WebElement onlineForm;

	@FindBy(xpath = "//input[@name='email_addr']")
	private WebElement txtemail;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement txtSubject;

	@FindBy(xpath = "//textarea[@name='comments']")
	private WebElement txtComments;

	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnSubmit;

	@FindBy(xpath = "//h1[text()='Thank You']")
	private WebElement msgThanku;

	public WebElement getContactUslink() {
		return contactUslink;
	}

	public WebElement getOnlineForm() {
		return onlineForm;
	}

	public WebElement getTxtemail() {
		return txtemail;
	}

	public WebElement getTxtSubject() {
		return txtSubject;
	}

	public WebElement getTxtComments() {
		return txtComments;
	}

	public WebElement getBtnSubmit() {
		return btnSubmit;
	}

	public WebElement getMsgThanku() {
		return msgThanku;
	}

	public void selectContactAndOnlineForm() {
		btnClick(getContactUslink());
		btnClick(getOnlineForm());

	}

	public void fillThefeedBack() throws IOException {
		sendkeys(getTxtemail(), getPropertyValue("email"));
		sendkeys(getTxtSubject(), getPropertyValue("subject"));
		sendkeys(getTxtComments(), getPropertyValue("comments"));
		btnClick(getBtnSubmit());

	}

	public boolean verifyTheMessage() {
		String text = getText(getMsgThanku());
		boolean res=false;
		if(text.equals("Thank You")) {
			res=true;
		}
		return res;

	}
	

}
