Project now defunct. Please check out this wonderful project instead: http://stefanbirkner.github.io/system-rules/

assert-exit
===========

JUnit @Rule annotation that provides the ability to assert that `System.exit(...)` is called with the expected status code.

This is done using JUnit's @Rule annotation. Consider the following:
```java
public class ExpectedExitTest {

  @Rule
  public ExpectedExit expectedExit = ExpectedExit.noAttempt();
  
  @Test
  public void invokesExitAsExpected() {
	  this.expectedExit.expectExit(7);
	  System.exit(7);
  }
}
```
Your test will pass if:
* You expected no call to `System.exit(...)` and none was made
* You expected a call to `System.exit(...)` with an expected status code and the same call was made

Your test will fail if:
* You expected a call to `System.exit(...)` but none was made
* You did not expect a call to `System.exit(...)` but one was made

This is done through manipulating the Java system SecurityManager.

This is designed as a test library and is not intended for general purpose applications. 

Maven
=====

To use with Maven currently you must clone these sources, run `mvn install` to install into your location maven repository and then include the following dependency:
```xml
<dependency>
  <groupId>org.neverfear</groupId>
  <artifactId>assert-exit</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>test</scope>
</dependency>
```
		
