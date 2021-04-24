package krawler.erp.testCases.SmokeTestSuiteSG.SampleDataSetup;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.modules.CashSale1;
import krawler.erp.modules.CustomerQuotation1;
import krawler.erp.modules.SalesInvoice1;
import krawler.erp.modules.SalesOrder1;

public class SalesModule extends BaseSetUpProper {

	@Test(priority = 211)
	public void Create_CustomerQautation() throws InterruptedException, IOException, AWTException {
		CustomerQuotation1.create_CustomerQuotation("00001", productid, "John", driver);
		CustomerQuotation1.create_CustomerQuotation("00002", productid, "Calvin", driver);
		CustomerQuotation1.create_CustomerQuotation("00003", productid, "Arnold", driver);
		CustomerQuotation1.create_CustomerQuotation("00004", productid, "Peeter", driver);
		CustomerQuotation1.create_CustomerQuotation("00005", productid, "Kalvin", driver);
	}

	@Test(priority = 221)
	public void Create_SalesOrder() throws InterruptedException, IOException, AWTException {
		SalesOrder1.create_SalesOrder("00001", productid, "John", driver);
		SalesOrder1.create_SalesOrder("00002", productid, "Calvin", driver);
		SalesOrder1.create_SalesOrder("00003", productid, "Arnold", driver);
		SalesOrder1.create_SalesOrder("00004", productid, "Peeter", driver);
		SalesOrder1.create_SalesOrder("00005", productid, "Kalvin", driver);
	}

	@Test(priority = 231)
	public void Create_CashSales() throws InterruptedException, IOException, AWTException {
		CashSale1.create_CashSale("00001", productid, "John", driver);
		CashSale1.create_CashSale("00002", productid, "Calvin", driver);
		CashSale1.create_CashSale("00003", productid, "Arnold", driver);
		CashSale1.create_CashSale("00004", productid, "Peeter", driver);
		CashSale1.create_CashSale("00005", productid, "Kalvin", driver);
	}

	@Test(priority = 241)
	public void Create_SalesInvoice() throws InterruptedException, IOException, AWTException {
		SalesInvoice1.create_salesInvoice_Proper("00001", productid, "John", "10", driver);
		SalesInvoice1.create_salesInvoice_Proper("00002", productid, "Calvin", "5", driver);
		SalesInvoice1.create_salesInvoice_Proper("00003", productid, "Arnold", "8", driver);
		SalesInvoice1.create_salesInvoice_Proper("00004", productid, "Peeter", "20", driver);
		SalesInvoice1.create_salesInvoice_Proper("00005", productid, "Kalvin", "40", driver);
	}

}
