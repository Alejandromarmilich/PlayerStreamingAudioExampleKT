# PlayerStreamingAudioExampleKT

I was searching the internet for tutorials to create a project like this, but none of the ones I tried were complete, they had deprecated methods or the bad usp of prepare() causing the app to freeze, this was solved with an asynchronous prepare.

The important thing if you want to replicate this project is that not only the INTERNET permissions are needed but also the attribute:
android:usesCleartextTraffic="true"
inside the manifest in application.

Enjoy the project :)
