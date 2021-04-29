# QA Challenge Solution

## Installation/Environment Setup:

For installation, I followed this link: https://www.swtestacademy.com/appium-tutorial/, the steps are listed below:
* Java and Maven Installation. Make sure JDK version is 11
* Android Studio Installation and Android Emulator Creation
* Appium Desktop Installation and configuration
* Android Virtual Device and Pre-Test Settings
* Development IDE installation, I installed IntelliJ community version

----------------------------------------------
## Test Specification
Based on my understanding of required automation steps, the implementation is performing the following actions:
* Click Get Started button after application start
* Choose Raptors as favorite team and click Continue button
* Click Continue then Done button to finish adding favorite
* Search for Team or Player provided by "theScoreTest.json" data file
* Click Team or Player tab then click first search result
* Verify next page displays correct Team or Player
* Click on Stat tab for the Team or Player
* Verify result for Stat is correct. For example, for Team, verify "Pace" is under Stat result
* Click BACK button
* Verify Team and Player tab is displayed 

Note: 
* "theScoreTest.json" currently is simple, for the purpose of demonstrating data driven approach, more data can be included this way

Bonus Question:
* The data driven approach can be expanded to cover list of devices and iterations of different team/player
* The sample test covers application start up/location permission/add favorite/search/team|player details


