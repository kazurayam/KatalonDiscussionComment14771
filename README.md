Yet another example of parameterized Test Object
===
## What is this?

This is a simple Katalon Studio project for demonstration purose.

You can check this out onto your PC and execute it with your Katalon Studio.
This project has been created with Katalon Studio version 5.4.1.

This project is meant to be a proposed solution for the question raised at
https://forum.katalon.com/discussion/comment/14771#Comment_14771

## How to execute this

1. Open the project with your Katalon Studio.
2. Load the "Test Cases/TC1" and run it with your browser. The test case would run successful.

## How this project is designed.

### HTML of Application Under Test

I expected that the HTML contains following snippet:

```
<div id="ManufacturerList-list">
    <div class="k-list-scroller">
        <ul id="ManufacturerList_listbox">
            <li tabindex="-1" role="option" unselectable="on"
                class="k-item k-state-selected k-state-focused" data-offset-index="0"
                id="e12345f3-02b2-1111-1faf-bcf11a22bef0">- Please Select -</li>
            <li tabindex="-1" role="option" unselectable="on"
                class="k-item" data-offset-index="1">BAIC</li>
            <li tabindex="-1" role="option" unselectable="on" class="k-item"
                data-offset-index="2">BMW</li>
            <li tabindex="-1" role="option" unselectable="on" class="k-item"
                data-offset-index="3">BYD</li>
            <li tabindex="-1" role="option" unselectable="on" class="k-item"
                data-offset-index="4">Changan</li>
        </ul>
    </div>
</div>
```

### What do we want

We want to select one of the li elements, identifying a li by the value of data-offset-index attribute (1, 2, 3, 4).

We want to create a single Test Object which is parameterized so that it accepts the value of index 1,2,3,4. We do not like to create 4 test objects per each li element.

### Wait for elements visible

https://forum.katalon.com/discussion/comment/14771#Comment_14771 implied that
the Application-Under-Test is Ajax-driven (possibly implemented upon jQuery). Ajax-driven means that the HTML
document is dynamically updated by JavaScript. Therefore test code have to wait
until the target HTML elements become visible.

### Test Object definition

I made a Test Object with Basic Selector with xpath:

`//ul[@id='ManufacturerList_listbox']/li[@data-offset-index='${index}']`

### Test Case script

I made a Test Case to demonstrate how to use the parameterized Test Object.

```
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
```
