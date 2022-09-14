# eComm test project
## Requirements
* JDK11
* maven
## Usage
### Browsers
#### Firefox
`mvn clean test -Dbrowser=firefox`
#### Chrome
`mvn clean test -Dbrowser=chrome`
#### Chrome with mobile resolution
`mvn clean test -Dbrowser=chromeMobile`
#### Dismiss banner
To dismiss banner use `-Dbanner=y` with one of the options above