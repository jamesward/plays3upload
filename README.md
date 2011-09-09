Make sure you have Play 1.2.3 installed.


Run Locally
-----------

Clone the git repo

    git clone git@github.com:jamesward/plays3upload.git

Move to the project dir

    cd plays3upload

Download the Play! Deps

    play deps

Set the AWS environment vars

    export AWS_ACCESS_KEY="YOUR_AWS_ACCESS_KEY"
    export AWS_SECRET_KEY="YOUR_AWS_SECRET_KEY"
    export S3_BUCKET="AN_AWS_UNIQUE_BUCKET_ID"


Use a local postgresql database:

    export DATABASE_URL=postgres://foo:foo@localhost/helloheroku

Or switch to the in-memory database by updating the conf/application.conf file

Run the app

    play run


Run on Heroku
-------------

Clone the git repo

    git clone git@github.com:jamesward/plays3upload.git

Move to the project dir

    cd plays3upload

Create the app on Heroku

    heroku create -s cedar

Set the AWS environment vars on Heroku

    heroku config:add AWS_ACCESS_KEY="YOUR_AWS_ACCESS_KEY" AWS_SECRET_KEY="YOUR_AWS_SECRET_KEY" S3_BUCKET="AN_AWS_UNIQUE_BUCKET_ID"

Upload the app to Heroku

    git push heroku master

Open the app in the browser

    heroku open

