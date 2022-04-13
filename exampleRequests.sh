#!/bin/bash

#The user wants to see screenings on the given day, between given hours.
curl -X POST localhost:8080/screenings/find -H 'Content-type:application/json' -d '{"day": "2022-04-21", "leftTime": "10:00", "rightTime": "19:30"}'

#Details about chosen screening.
curl -v localhost:8080/screenings/show/117

#User with name filled with polish characters makes reservation (seats are given by their index in screening.reservations List).
curl -X POST localhost:8080/book/show/117 -H 'Content-type:application/json' -d '{"name": "Grzegorz", "surname": "Brzęczyszczykiewicz-Coś", "chosenSeats": [7, 8, 9], "ticketTypes": {"7": "ADULT", "8": "ADULT", "9": "CHILD"} }'

#Details about chosen screening after tickets were reserved.
curl -v localhost:8080/screenings/show/117

#User tries to reserve seat, but it's illegal choice (leaves one seat between his seat and end of row).
curl -X POST localhost:8080/book/show/117 -H 'Content-type:application/json' -d '{"name": "Jan", "surname": "Pechowy", "chosenSeats": [10], "ticketTypes": {"10": "ADULT"} }'

#User tries to reserve seat, but it's illegal choice (leaves one seat between his seat and first reservation).
curl -X POST localhost:8080/book/show/117 -H 'Content-type:application/json' -d '{"name": "Jan", "surname": "Pechowy", "chosenSeats": [11], "ticketTypes": {"10": "ADULT"} }'

#User with a name that does not meet the requirements is not able to book ticket.
curl -X POST localhost:8080/book/show/117 -H 'Content-type:application/json' -d '{"name": "Jo", "surname": "Pechowy", "chosenSeats": [11], "ticketTypes": {"10": "ADULT"} }'
