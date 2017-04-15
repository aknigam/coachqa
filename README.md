# coachqa

Deployment Instructions:

a) Build the projects
1. Checkout NotificationSystem project from git and run maven clean install for this project. (Git url: https://github.com/aknigam/NotificationSystem.git)
2. Checkout CoachQA project from git and run maven clean install for this project. (Git url: https://github.com/aknigam/coachqa.git). 
Run the maven command from the coachqa folder which contains coachqa-shared and learn-qa folders.

b) Run the learnqa service
2. Navigate to /coachqa/learn-qa folder and run following command from command line:
    
        mvn spring-boot:run

   Note: You can also run the service by adding a maven configuration for this project.

# Authentication
run the following command to generate the bearer token. 

curl 'http://localhost:8080/oauth/token?grant_type=password&scope=read&username=anigam@expedia.com&password=pass' -X POST -H 'Origin: chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: en-US,en;q=0.8' -H 'Authorization: Basic Zm9vOmJhcg==' -H 'Accept: */*' -H 'Cache-Control: no-cache' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36' -H 'Connection: keep-alive' -H 'Content-Length: 0' --compressed

Above is a post call with required basic authorisation (user: foo, password:bar)

username and passwords can be read from the`learn-qa`.appuser table in DB.


# Swagger
http://localhost:8080/swagger-ui.html#/

https://console.firebase.google.com/project/leanqaandroidapp/settings/general/android:com.crajee.learnqa

Web API Key: AIzaSyDYNBHuO2_WCmqP5C6U5swyyM4-9ci9iO0
