# Systemarchitekturen UB1

## Project Structure
The project is divided into 3 subprojects 
- Writeside
- Eventside
- Readside

## Communication
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

# Run and Interact with the Application
1. start the eventside
2. start the writeside
3. start the readside
4. Open a browser on http://localhost:8081/
5. Interact with the webinterface to create or cancel bookings, to show existing bookings or to find free rooms