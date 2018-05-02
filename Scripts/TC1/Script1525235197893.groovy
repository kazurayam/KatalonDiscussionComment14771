import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/comment14771_testbed.html')

// The target page is Ajax-driven which page is dynamically updated by JavaScript.
// We need to wait until the page is displayed.
// Let's wait for '- Please Select -' become visible.
WebUI.waitForElementVisible(
	findTestObject('Page_14771/li_- Please Select -'), 20)

def contentA = WebUI.getText(
	findTestObject('Page_14771/li_- Please Select -'))
WebUI.verifyEqual(contentA, '- Please Select -')

def contentB = WebUI.getText(
	findTestObject('Page_14771/li_ManufacturerList_BAIC'))
WebUI.verifyEqual(contentB, 'BAIC')

def content1 = WebUI.getText(
	findTestObject('Page_14771/li_ManufacturerList_parameterized', 
		['index':'1']))
WebUI.verifyEqual(content1, 'BAIC')

def content2 = WebUI.getText(findTestObject(
	'Page_14771/li_ManufacturerList_parameterized',
	['index':'2']))
WebUI.verifyEqual(content2, 'BMW')

// If we want to know how long the ManufacturerList is,
// The value of data-offset-index attribute of 
// the last <li> element signifies the list size. 
def lastIndex = WebUI.getAttribute(
	findTestObject('Page_14771/li_ManufacturerList_last'),
	'data-offset-index')
WebUI.verifyEqual(lastIndex, '4')

WebUI.closeBrowser()

