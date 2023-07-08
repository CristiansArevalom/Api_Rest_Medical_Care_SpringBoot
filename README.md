# API REST for Medical Appointments


Basic api rest for Medical appointments using Java,Springboot, Mysql and JPA as ORM including the main data models like doctors, patients and medical appointment between doctors and patients.


Author:Cristian Arevalo.


You can clone this repo as starter project for your Springboot, Mysql API server


## Data Model
    Data model based on this relational model
        ![Relational Data Model](/assets/image.png)


## Tech Stack 💻


- [Java](https://www.java.com/es/)
- [Mysql](https://www.mysql.com/)
- [JPA](https://spring.io/projects/spring-data-jpa)
- [SpringBooot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)



## Installation and Running App :zap:



**1. Clone this repo by running the following command :-**


```bash
 git clone https://github.com/CristiansArevalom/Api_Rest_Medical_Care_SpringBoot.git
 cd citas_medicas
```


**2. Install the required package:-**
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies, however you should configure the MySQL Connection on aplication.properties

Remember that the .application.properties file must be created for the API to work.

```bash
Create a MySQL database with the name citas_medicas and add the credentials to /resources/application.properties.
The default ones are :
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/citas_medicas
spring.datasource.username=root
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.hikari.minimum-idle=3
spring.datasource.hikari.maximum-pool-size=10

```



**3. Now run the mvn spring-boot:run command to start the project :-**


```bash
mvn spring-boot:run
```


**4.** **🎉 OpenPostmann and test the restAPI on this url: `https://127.0.0.1:8080`**

**Follow the steps of the Steps presentation in the repository -**


## Features and Functionalities
-Java, SpringBoot, MySQL, and JPA as ORM for Rest API medical appointments


-Crud operations for patients, doctors, consulting rooms, and reserve rooms, taking in mind office availability and dates


-SQL for database: Relational database MySql

# Available Endpoints:

- [Patients](#patients)
    - [GET] /api/pacientes 
    - [POST] /api/pacientes
    - [PUT] /api/pacientes/:id
    - [GET] /api/pacientes/cedula/:cedula
    - [GET] /api/pacientes/:id
    - [DELETE] /api/pacientes/:id

- [Specialities](#specialities)
    - [GET] /api/especialidades/nombre/:NOMBRE_ESPECIALIDAD
    - [GET] /api/especialidades
    - [GET] /api/especialidades/:id

- [Doctors](#doctors)
    - [GET] /api/doctores
    - [POST] /api/doctores
    - [GET] /api/doctores/:id
    - [GET] /api/doctores/especialidad/:NOMBRE_ESPECIALIDAD


- [Consulting Room](#consulting-room)
    - [GET] /api/consultorios
    - [GET] /api/consultorios/:id
    - [POST] /api/consultorios
    - [PUT] /api/consultorios/:id
    - [DELETE] /api/consultorios/:id
    - [GET] /api/consultorios/reserva/fecha-inicio=DD-MM-YYY :HH:MM&fecha-fin=DD-MM-YYY :HH:MM
//show Availables consulting rooms (consulting rooms without a doctor assigned)


- [Assigned Consulting Rooms](#assigned-consulting-rooms)
    - [GET] /api/consultorios-asignados
    - [GET] /api/consultorios-asignados/:id
    - [GET] /api/consultorios-asignados/consultorios/:id
    - [GET] /api/consultorios-asignados/especialidad/:NOMBRE_ESPECIALIDAD
    - [POST] /api/consultorios-asignados
- [Medical Appointments](#medical-appointments)
    - [GET] /api/citas-medicas
    - [GET] /api/citas-medicas/consultorio-asignado/:id
    - [GET] /api/citas-medicas/paciente/:id
    - [GET] /api/citas-medicas/doctor/:id
    - [GET] /api/citas-medicas/especialidad/:nombre
    - [POST] /api/citas-medicas


> Note: Assigned consulting rooms can only be created if the consulting room is available and have a valid date (does not have a doctor assigned in the range of dates given in the json)
A medical appointment can only be created if previously exist an assigned consulting room with the requested specialty , is available for medical appointments and has a valid date (does not have a previous medical appointment assigned on that range of dates)。




# See how it works

### Patients
##### [GET] /api/pacientes
![Get Methods for patients](/assets/Patients/GetPatients.gif)

##### [POST] /api/pacientes
![Post Methods for patients](/assets/Patients/PostPatients.gif)

##### [PUT] /api/pacientes/:id
![Put Methods for patients](/assets/Patients/PutPatients.gif)

##### [GET] /api/pacientes/cedula/:cedula
![Get patients by cedula](/assets/Patients/GetPatientByCedula.gif)

##### [GET] /api/pacientes/:id
![Get patients by id](/assets/Patients/GetPatientById.gif)

##### [DELETE] /api/pacientes/:id
![Delete patients](/assets/Patients/DeletePatient.gif)

#### Doctors
##### [GET] /api/doctores
![Get Methods for Doctors](/assets/Doctors/GetDoctors.gif)

##### [POST] /api/doctores
![Post Methods for Doctors](/assets/Doctors/PostDoctors.gif)

##### [GET] /api/doctores/:id
![Get Doctors by id](/assets/Doctors/GetDoctorById.gif)

##### [GET] /api/doctores/especialidad/:NOMBRE_ESPECIALDIAD
![Get Doctors by Speciality](/assets/Doctors/GetDoctorBySpeciality.gif)

##### [PUT] /api/doctores/:id
![Put Methods for Doctors](/assets/Doctors/PutDoctor.gif)

##### [DELETE] /api/doctores/:id
![Delete Doctor](/assets/Doctors/DeleteDoctor.gif)


#### Medical Appointments

##### [GET] /api/citas-medicas
![Get Medical appointments](/assets/medical%20appointments/GetMedicalAppointments.gif)


##### [GET] /api/citas-medicas/consultorio-asignado/:id
![Get Medical appointments by Asignned roomid](/assets/medical%20appointments/GetMedicalAppointmentByAssignedRoom.gif)


##### [GET] /api/citas-medicas/paciente/:id
![Get Medical appointments by patient id](/assets/medical%20appointments/GetMedicalAppointmentsByPatient.gif)

##### [GET] /api/citas-medicas/doctor/:id
![Get Medical appointments by doctor id](/assets/medical%20appointments/GetMedicalAppointmentsByDoctor.gif)

##### [GET] /api/citas-medicas/especialidad/:nombre
![Get Medical appointments by Specialty](/assets/medical%20appointments/GetMedicalAppointmentsBySpeciality.gif)

##### [POST] /api/citas-medicas
![POST Medical appointments](/assets/medical%20appointments/PostMedicalAppointments.gif)

### Specialities
##### [GET] /api/especialidades/nombre/:NOMBRE_ESPECIALDIAD
![Get Specialty by Name](/assets/Specialities/GetSpecialitiesByNombre.gif)

##### [GET] /api/especialidades
![Get Specialities](/assets/Specialities/GetSpecialities.gif)


##### [GET] /api/especialidades/:id
![Get Specialities by Id](/assets/Specialities/GetSpecialitiesById.gif)


### Consulting room
##### [GET] /api/consultorios
![Get Consulting room](/assets/Consulting%20rooms/GetConsultingRooms.gif)

##### [GET] /api/consultorios/:id
![Get Consulting room by Id](/assets/Consulting%20rooms/GetConsultingRoomsById.gif)

##### [POST] /api/consultorios
![POST Consulting room](/assets/Consulting%20rooms/PostConsultingRooms.gif)

##### [PUT] /api/consultorios/:id
![PUT Consulting room](/assets/Consulting%20rooms/PutConsultingRooms.gif)

##### [DELETE] /api/consultorios/:id
![DELETE Consulting room](/assets/Consulting%20rooms/DeleteConsultingRoom.gif)

##### [GET] /api/consultorios/reserva/fecha-inicio=DD-MM-YYY :HH:MM&fecha-fin=DD-MM-YYY :HH:MM
//show Availables consulting rooms (consulting rooms without a doctor assigned)
![Get Consulting room without a doctor assigned](/assets/Consulting%20rooms/GetAvailableConsultingRooms.gif)


## Assigned consulting rooms
##### [GET] /api/consultorios-asignados
![Get Assigned Consulting room](/assets/Asigned%20consulting%20rooms/GetAssignedConsultingRooms.gif)


##### [GET] /api/consultorios-asignados/consultorios/:id
![Get Assigned Consulting room by room id](/assets/Asigned%20consulting%20rooms/GetAssignedConsultingRoomsByRoom.gif)


##### [GET] /api/consultorios-asignados/especialidad/:NOMBRE_ESPECIALIDAD
![Get Assigned Consulting room by speciality name](/assets/Asigned%20consulting%20rooms/GetAssignedConsultingRoomsBySpeciality.gif)

##### [POST] /api/consultorios-asignados
![POST Assigned Consulting room](/assets/Asigned%20consulting%20rooms/PostAssignedConsultingRooms.gif)

