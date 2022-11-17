package org.test;

import java.io.IOException;

import org.pageObjects.AccountsPage;
import org.pageObjects.ContactPage;
import org.pageObjects.LoginPage;
import org.pageObjects.SignOffPage;
import org.pageObjects.TransactionPage;
import org.pageObjects.TransferPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utility.CommonUtility;

public class TestAutomation extends CommonUtility {
	@BeforeClass
	private void setup() throws IOException {
		launchBrowser("Chrome");
		launchUrl(getPropertyValue("url"));
		implicitWait(20);

	}

	@AfterMethod
	private void afterTestMethod(ITestResult r) throws IOException {
		takeScreenShot(r.getName());

	}

	@AfterClass
	private void tearDown() {
		quit();
	}

	@Test(priority = 1)
	private void loginValidation() throws IOException {
		LoginPage login = new LoginPage();
		login.clickSignIn();
		// invalid login
		login.login(getPropertyValue("invalid_username"), getPropertyValue("invalid_password"));

		// valid login
		login.login(getPropertyValue("valid_username"), getPropertyValue("valid_password"));
		Assert.assertTrue(login.verifyLoginAction(), "Verify the Login Valid Credentials");
		System.out.println(login.getProfileName().getText());

	}

	@Test(priority = 2)
	private void verifyTheBalance() throws IOException {
		AccountsPage ac = new AccountsPage();
		ac.selectAccoun();
		Assert.assertTrue(ac.verifyAVailableBalance(), "Verify The Available balance Status");
		System.out.println(ac.getAvailableBalance().getText());

	}

	@Test(priority = 3)
	private void transeferFund() throws IOException {
		TransferPage tf = new TransferPage();
		tf.transferFunds();
		Assert.assertTrue(tf.verifyAMountTransfered(), "Transfer Amount verication");
		System.out.println(getText(tf.getTransferMessage()));

	}

	@Test(priority = 4)
	private void transcationFund() throws IOException {
		TransactionPage tc = new TransactionPage();
		tc.getFirst2Transactions();
		Assert.assertTrue(tc.verifyTheTransactions(), "Verify The 2 transactions ");
		System.out.println("The transaction history was tested");

	}

	@Test(priority = 5)
	private void ContactValidation() throws IOException {
		ContactPage ct = new ContactPage();
		ct.selectContactAndOnlineForm();
		ct.fillThefeedBack();
		ct.verifyTheMessage();
		System.out.println("User Successfully submit the feedback");

	}

	@Test(priority = 6)
	private void signOff() throws IOException {
		SignOffPage s = new SignOffPage();
		s.clickSignOff();
		Assert.assertTrue(s.verifySignOff(), "Verify the sign off");
		System.out.println("User Successfully sign off the application");

	}
}
