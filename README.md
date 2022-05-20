# Systemarchitekturen UB1

## Run and Interact with the Application
1. start the eventside
2. start the writeside
3. start the readside
4. open a browser on http://localhost:8081/
5. interact with the webinterface to create or cancel bookings, to show existing bookings or to find free rooms

Detailed instructions on the individual functions can be found in the file `Documentation.pdf`. A detailed explanation of what exactly happens with the various functions is described in this file's Communication > Procedures section.

## Project Structure
The project is divided into 3 subprojects 
- Writeside
- Eventside
- Readside

## Architecture
The individual subprojects were implemented independently of each other and are therefore to be seen as independent applications as they only communicate with each other via interfaces. 

The WriteSide is designed according to principle of the 4-layered architecture, containing View, Application, Domain and Infrastructure Layer.

The EventSide contains event logic and is responsible for notifying the various subscribers of appropriate events.

In contrast to the WriteSide, the ReadSide is designed without Domain and Application Layer and thus contains no domain logic. Because of this decision, data can be accessed more quickly. On this side, the data is stored in a simplified structure using the projection.

In the `Documentation.pdf` file there is a clear diagram showing the structure of the architecture.

## Communication

### The 3 Subprojects
The 3 subprojects communicate with each other over REST Interfaces. 

**Webclient (Port 8081):**
- is implemented on the Writeside
- interacts with the BookingController on the Writeside
- can create and cancel bookings over the webinterface

**Writeside (Port 8081):**
- handles webclient requests
- publishes Events to the EventRestController on the Eventside
- reads data directly from the Readside by interacting with the ReadRestController

**Eventside (Port 8080):**
- receives Events from the Writeside 
- interacts with the ReadRestController

**Readside (Port 8082):**
- receives requests from the Eventside and performs writes with the aid of a projection class
- processes read-data-requests from the BookingController


### Procedures
To understand how these different sides (WriteSide, EventSide and ReadSide) communicate with each other, we have written down the procedure of creating and cancelling bookings.
This is mainly about access via REST interfaces to create (/bookingCreated/) and cancel (/bookingCancelled/) bookings. But when creating a booking, the function for obtaining free rooms (/freeRooms/) is additionally accessed, see step 1 and 2 of "Creating a Booking".

#### Creating a Booking
The backend process is started when new booking data has been entered on http://localhost:8081/ and confirmed via the "Create" button.

1. A search for free rooms for the period and the number of guests is carried out by sending an HTTP GET request to the ReadRestController (URI: /freeRooms/)
2. The ReadRestController receives a list of free rooms by the RoomRepositoryRead and passes them back to the BookingController
3. The BookingServiceWrite passes data to the BookingRepositoryWrite, adding a new booking on the WriteSide
4. The event publisher (WriteSide) creates a new event (BookingCreatedEvent) by sending an HTTP POST request to the EventRestController (URI: /bookingCreated/)
5. The EventRestController passes this event to the repository (EventRepository)
6. The repository notifies every subscriber about the new event, sending an HTTP POST request to the ReadRestController (URI: /bookingCreated/)
7. The ReadRestController passes this event to the projection
8. The projection adds the newly created booking to the ReadSide's booking list and updates the periods in the rooms that are used in this booking


#### Cancelling a Booking
The backend process is started when an existing booking is cancelled on http://localhost:8081/bookingOverview

1. The BookingServiceWrite passes the booking ID to the BookingRepositoryWrite, removing the corresponding booking on WriteSide
2. The event publisher (WriteSide) creates a new event (BookingCancelledEvent) by sending an HTTP POST request to the EventRestController (URI: /bookingCancelled/)
3. The EventRestController passes this event to the repository (EventRepository)
4. The repository notifies every subscriber about the new event, sending an HTTP POST request to the ReadRestController (URI: /bookingCancelled/)
5. The ReadRestController passes this event to the projection
6. The projection removes the corresponding booking from the ReadSide's booking list and also removes the stored time periods of the rooms
