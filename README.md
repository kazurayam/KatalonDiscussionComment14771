One more example of parameterized Test Object
===
## What is this?

This is a simple Katalon Studio project for demonstration purose.
You can check this out onto your PC and execuite it with your Katalon Studio.

This project has been created with Katalon Studio version 5.4.1.

This project is meant to be a proposed solution for the question raised at
https://forum.katalon.com/discussion/comment/14676#Comment_14676

## How to execute this

1. Open the project with your Katalon Studio.
2. Load the "Test Cases/TC1" and run it with your browser. The test case would run successful.

## How this project is designed.

### HTML of Application Under Test

I expected that the HTML contains following snippet:

```
<div class="k-list-scroller" unselectable="on" style="height: 200px;">
    <ul unselectable="on" class="k-list k-reset" tabindex="-1" aria-hidden="true"
            id="ABC_listbox" aria-live="off" data-role="staticlist" role="listbox">
        <li tabindex="-1" role="option" unselectable="on"
            class="k-item k-state-selected k-state-focused" data-offset-index="0"
            id="e12345f3-02b2-1111-1faf-bcf11a22bef0">- Please Select -</li>
        <li tabindex="-1" role="option" unselectable="on"
            class="k-item" data-offset-index="1">A</li>
        <li tabindex="-1" role="option" unselectable="on" class="k-item"
            data-offset-index="2">B</li>
        <li tabindex="-1" role="option" unselectable="on" class="k-item"
            data-offset-index="3">C</li>
        <li tabindex="-1" role="option" unselectable="on" class="k-item"
            data-offset-index="4">D</li>
        <li tabindex="-1" role="option" unselectable="on" class="k-item"
            data-offset-index="5">E</li>
    </ul>
</div>
```

I assumed that we want to select one of the <li> elements in it, want to identify a <li> by the value of data-offset-index attribute (0, 1, 2, 3, 4, 5), want to create a single Test Object which is parameterized so that it accepts '0', '1' or '5'.

### Test Object definition

I made a Test Object with Basic Selector with xpath:

`//ul[@id='ABC_listbox']/li[@data-offset-index='${index}']`

### Test Case

I made a Test Case to demonstrate how to use the parameterized Test Object.

```
WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/14676_testbed.html')

def content0 = WebUI.getText(findTestObject('Page_14676/li_by_data-offset-index', ['index':'0']))
//WebUI.comment(">>> content0=${content0}")
WebUI.verifyEqual(content0, '- Please Select -')

def content1 = WebUI.getText(findTestObject('Page_14676/li_by_data-offset-index', ['index':'1']))
WebUI.verifyEqual(content1, 'A')

def content2 = WebUI.getText(findTestObject('Page_14676/li_by_data-offset-index', ['index':'2']))
WebUI.verifyEqual(content2, 'B')

def content3 = WebUI.getText(findTestObject('Page_14676/li_by_data-offset-index', ['index':'3']))
WebUI.verifyEqual(content3, 'C')

def content4 = WebUI.getText(findTestObject('Page_14676/li_by_data-offset-index', ['index':'4']))
WebUI.verifyEqual(content4, 'D')

WebUI.closeBrowser()
```
