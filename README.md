## Starting project

In order to build and run app use the `startApp.sh` script. It will build the project using maven.

Address: `localhost:8080`

I left console message 'Data loaded' to mark the end of the project preparation.

## Example uses

Script `exampleRequests.sh` will:
0. Initialize project with test in-memory database (`BootStrapData.java`).
1. Show screenings on given day, between given hours, ordered by title and screening time.
2. Show details about one of the screenings.
3. Book seats for chosen screening and return the total amount to pay
4. Show that booked seats are marked as taken.
5. The project will refuse to make another three reservations, because of incorrect data (request one would leave a single seat between current and already made reservation, request two would leave a single seat at the end of the row, request three contains incorrect user data - too short name).

## What is where?

* `/book/show/{id}` -  POST requests for booking tickets to screening  given by id parameter. Uses `ReservationForm` to pass reservation data.

* `/screenings/list` - list of all screenings

* `/screenings/show/{id}` - data of screening given by id parameter

* `/screenings/find` - POST request for finding screening in given time interval. Uses `ScreeningForm` to pass query parameters.

Extra functionalities, used for debugging and practice:

* `/movies/list` - list of all movies

* `/movies/add/` - used to add a new movie, uses String as parameter




