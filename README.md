# General information

This is email sender app created with spring 3 and maven, this is just intended to be a reference for anyone who finds this helpful or for me in a future.

## Installation

Clone this project and open it in your favorite IDE o Text Editor to start

### Eclipse
1. Click File
2. Import
3. Maven
4. Existing Maven Project
5. Browse
6. Click in the app folder
7. Click Select folder button
8. pom.xml will be selected
9. Finish
10. Wait until project is imported to your workspace

### IntelliJ

1. Open IntelliJ IDEA
2. From the Welcome screen, click Open
3. Navigate to your Maven project and select the top-level folder.
4. Click OK
5. If prompt is opened Click on Trust in this project
10. Wait until project is imported to your workspace

## Usage

### Considerations

**Please note** this app was developed just to send emails using GMAIL host as the sender, with this configuration you can send emails to any email address, but you can freely change it to send to any host you want, even your own mail relay.

### Needed configurations GMAIL

1. Sign in to your gmail account which will be the mail sender
2. Go to Manage your Google Account
3. From main menu go to Security -> How to access Google (section) -> 2-step Authentication
4. Introduce your google account credentials
5. At the end of this same section search for "Application's passwords"
6. Introduce an Application name, example SpringMailSenderApp
7. Copy the generated password
8. Open the project and go to src/main/resources/application.properties
9. In the ```email.password=pasteHereYourPsd``` value paste the copied password (step 7), result will be like ```email.password=4ffsgsgwgehgfyt```, remove spaces from generated password if needed.
10. In the ```email.sender=mail@gmail.com``` value, change ```mail@gmail.com``` for your gmail account, remember, this account will be used to send mails from spring app.
11. Optional, change application port, file support configuration from this same file if needed

## Test

### Test to send a single email message

```
POST http://localhost:8082/spring-mail-sender/v1/sendMessage
{
    "toUser": ["mail@gmail.com"],
    "subject": "Test - Single message",
    "message": "This is a test for a single message from Spring"
}
```


### Test to send email message attaching a file

```
curl --location 'http://localhost:8082/spring-mail-sender/v1/sendMessageFile' \
--form 'toUser="mailhotmail.com, anotherAddress@mail.com"' \
--form 'subject="Test - Message with file"' \
--form 'message="This is an email message with a file"' \
--form 'file=@"/C:/Downloads/fondo.jpg"'
```

Postman example

![Request](https://github.com/roman-bgonz/spring-mail-sender/blob/main/fondo.jpg)

** Note**: If you want to send the message to multiple email addresses you can do it by adding a comma (,) after each address.

## What else can I do?

1. Create a standard to your ResponseEntity in order to have custom responder according to your need.
2. Handle exceptions properly logging them in log files
3. Create ExceptionAdvice to handle general exceptions
4. Create a front to consume this application
5. Add security (JWT) to this project