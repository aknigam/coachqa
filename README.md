# coachqa

Deployment Instructions:

1. Download tomcat and unzip it from 
http://apache.mirrors.hoobly.com/tomcat/tomcat-8/v8.0.26/bin/apache-tomcat-8.0.26.zip

2. Run following maven command from command line or IDE. Run it from the root of the project as shown below:

C:\workspace\coachqa\learn-qa>mvn package

This will generate a war file in following location: C:\workspace\coachqa\learn-qa\target
rename the war file from learn-qa.war to ROOT.war

3. Go to tomcat webapps folder. Rename Exitign ROOT folder to ROOT-original. 
4. Copy the ROOT.war file created in step 2 here.
5. Start the tomcat folder.
6. To login to the application go to following link:

http://localhost:8080/login.html
sample username: anigam@expedia.com
password: pass

# Authentication
run the following command to generate the bearer token. Refer: https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-secure-oauth2/src/main/java/sample/secure/oauth2/SampleSecureOAuth2Application.java
curl localhost:8080/oauth/token -d "grant_type=password&scope=read&username=greg&password=turnquist" -u foo:bar

