# coachqa

## Deployment Instructions:

a) Build the projects
1. Checkout NotificationSystem project from git and run maven clean install for this project. (Git url: https://github.com/aknigam/NotificationSystem.git)
2. Checkout CoachQA project from git and run maven clean install for this project. (Git url: https://github.com/aknigam/coachqa.git). 
Run the maven command from the coachqa folder which contains coachqa-shared and learn-qa folders.

b) Run the learnqa service
2. Navigate to /coachqa/learn-qa folder and run following command from command line:
    
        mvn spring-boot:run

   Note: You can also run the service by adding a maven configuration for this project.

## Authentication
run the following command to generate the bearer token. 

curl 'http://localhost:8080/oauth/token?grant_type=password&scope=read&username=anigam@expedia.com&password=pass' -X POST -H 'Origin: chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: en-US,en;q=0.8' -H 'Authorization: Basic Zm9vOmJhcg==' -H 'Accept: */*' -H 'Cache-Control: no-cache' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36' -H 'Connection: keep-alive' -H 'Content-Length: 0' --compressed

Above is a post call with required basic authorisation (user: foo, password:bar)

username and passwords can be read from the`learn-qa`.appuser table in DB.


## API documentation using swagger
http://localhost:8080/swagger-ui.html#/

Install : Modify headers chrome extension to set bearer token while loading swagger UI otherwise you will get authentication error.
header name: Authorization
header value: bearer 0987111f-3e99-4840-851e-f8f9e2a88585

Swagger yml is available at: http://localhost:8080/v2/api-docs
It can be imported to postman by providing the contents of above url.
Name of postman collection: Crajee-API docs


###Swagger can generate the retrofit client interfaces

Use the following command from following folder:

/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/main/resources

<code>
java -jar swagger-codegen-cli-2.2.1.jar generate -i /Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/main/resources/swagger-api-docs.json  -l java --library=retrofit2 -DmodelPackage=com.coachqa.entity,apiPackage=com.crajee.learnqa.repository.retrofit -o generated-retrofit
</code>

Refer:

http://pscode.rs/swagger-code-gen-retrofit2-library/
https://swagger.io/swagger-codegen/
https://swagger.io/docs/swagger-tools/#swagger-codegen-documentation-9 


## Android app details

Checkout android project and import it into Android studio. Setup an emulator or use an android device by debug options enabled.
Git url: https://github.com/aknigam/LearnQAApp

This app connects to the learn QA service running locally. You should be able to login and see the list of questions and ask a new question using this app.


## Firebase 
https://console.firebase.google.com/project/leanqaandroidapp/settings/general/android:com.crajee.learnqa

## Environment setup

The application uses GCP Storage  java client which requires the authentication to be setup by setting the following 
environment property

Provide authentication credentials to your application code by setting the 
environment variable GOOGLE_APPLICATION_CREDENTIALS. Replace [PATH] with the file path of the JSON file that contains your service account key, and [FILE_NAME] with the filename. This variable only applies to your current shell session, so if you open a new session, set the variable again.


