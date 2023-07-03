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

-This project has many endpoints for use, like:
```bash
### Patients
[GET] /api/pacientes
[POST] /api/pacientes
[PUT] /api/pacientes/:id
[GET] /api/pacientes/cedula/:cedula
[GET] /api/pacientes/:id
[DELETE] /api/pacientes/:id


### Specialities
[GET] /api/especialidades/nombre/:NOMBRE_ESPECIALDIAD
[GET] /api/especialidades
[GET] /api/especialidades/:id


### Doctors
[GET] /api/doctores
[POST] /api/doctores
[GET] /api/doctores/:id
[GET] /api/doctores/especialidad/:NOMBRE_ESPECIALDIAD


### Consulting room
[GET] /api/consultorios
[GET] /api/consultorios/:id
[POST] /api/consultorios
[PUT] /api/consultorios/:id
[DELETE] /api/consultorios/:id
[GET] /api/consultorios/reserva/fecha-inicio=DD-MM-YYY :HH:MM&fecha-fin=DD-MM-YYY :HH:MM
//show Availables consulting rooms (consulting rooms without a doctor assigned)


##Assigned consulting rooms
[GET] /api/consultorios-asignados
[GET] /api/consultorios-asignados/:id
[GET] /api/consultorios-asignados/consultorios/:id
[GET] /api/consultorios-asignados/especialidad/:NOMBRE_ESPECIALIDAD
[POST] /api/consultorios-asignados


##Medical appointments
[GET] /api/citas-medicas
[GET] /api/citas-medicas/consultorio-asignado/:id
[GET] /api/citas-medicas/paciente/:id
[GET] /api/citas-medicas/doctor/:id
[GET] /api/citas-medicas/especialidad/:id
[POST] /api/citas-medicas
```
> Note: Assigned consulting rooms can only be created if the consulting room is available and have a valid date (does not have a doctor assigned in the range of dates given in the json)
A medical appointment can only be created if previously exist an assigned consulting room with the requested specialty , is available for medical appointments and has a valid date (does not have a previous medical appointment assigned on that range of dates)。




### See how it works


##### Patients
![Post and get Methods for patients](/assets/PostPatients.gif)

##### Doctors
![Post and get Methods for Doctors](/assets/PostDoctors.gif)

##### Medical Appointments
![Post and get Methods for Medical appointments](/assets/PostMedicalAppointments.gif)



