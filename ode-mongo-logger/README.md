# WYDOT ODE Data Logger

This project inserts BSMs from the ODE into a database running on WYDOT's server 146.166.249.2. CVCOMMS is the Oracle schema that has been created for this located at 10.145.9.172:1521 (ordb-p01-vip). There are currently 4 tables collecting the data: 

- bsm_core_data - records the "core" data of the bsm 
- bsm_part2_vse - records the data encapsulated in objects J2735VehicleSafetyExtensions associated with the bsm
- bsm_part2_spve - records the data encapsulated in objects J2735SpecialVehicleExtensions associated with the bsm
- bsm_part2_suve - records the data encapsulated in objects J2735SupplementalVehicleExtensions associated with the bsm

### Usage

To use a running version of the ODE Data Logger, upload any .uper or .hex into the ODE through the web interface. Alternatively files can be dropped into /jpo-ode-svcs/target/uploads/bsm. Data will then be decoded by the ODE and then inserted into the CVCOMMS tables via the ODE Data Logger. 

### Oracle Schema Access

**Step 1**: Log onto WYDOT server 10.145.9.204

**Step 2**: Open sql plus with the Oracle client:

```bash
$ cd /opt/oracle/instantclient_12_2
$ ./sqlplus cvcomms/C0ll1s10n@cvlogger_dev
```
PLSQL commands will now work on the CVCOMMS schema.

**Note**: The column names are very long and feature multiple abbreviations. We will be adding a dictionary to help keep track of these fields

### Installation Instructions

The follwing instructions describe the process to run the ODE Data Logger.

#### Downloading the source code

**Step 1**: Clone the solution from BitBucket using:

```bash
git clone https://<username>@bitbucket.org/szumpf/ode-data-logger.git
```

**Step 2**: Change into the repository directory

```bash
$ cd oracle-data-logger
```

**Step 3**: Add the Oracle JDBC driver to your local Maven repository

```bash
$ mvn install:install-file -Dfile=oracle/ojdbc6.jar
      -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar
```

**Step 4**: Change into the project directory

```bash
$ cd oracle-data-logger
```

**Step 5**: Compile the project 

```bash
$ mvn clean compile assembly:single install
```

**Step 5**: Run *Note: this will use a current configuration of localhost:9092 for the bootstrap.servers endpoint and J2735Bsm for the topic.

```bash
$ java -jar target/cv-logger-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
```

WINDOWS COMMANDS

Desktop 

mvn install:install-file -Dfile="C:\\Users\\kperry\\ode-data-logger\\oracle\\ojdbc6.jar" -DgroupId="com.oracle" -DartifactId=ojdbc6 -Dversion="11.2.0.4" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-plugins-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-plugins -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-core-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-core -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-common-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-common -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-svcs-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-svcs -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\cv-data-logger-library\\target\\cv-data-logger-library-1.0-SNAPSHOT.jar" -DgroupId="com.trihydro.library.service" -DartifactId=cv-data-logger-library -Dversion="1.0-SNAPSHOT" -Dpackaging=jar


Laptop

mvn install:install-file -Dfile="C:\\Users\\kperry\\Trihydro\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-plugins-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-plugins -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\Trihydro\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-core-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-core -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\Trihydro\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-common-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-common -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\Trihydro\\ode-data-logger\\ode-data-logger\\resources\\jpo-ode-svcs-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-svcs -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\Trihydro\\cv-data-logger-library\\target\\cv-data-logger-library-1.0-SNAPSHOT.jar" -DgroupId="com.trihydro.library.service" -DartifactId=cv-data-logger-library -Dversion="1.0-SNAPSHOT" -Dpackaging=jar


//

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\resources\\jpo-ode-plugins-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-plugins -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\resources\\jpo-ode-core-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-core -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\resources\\jpo-ode-common-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-common -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\resources\\jpo-ode-svcs-0.0.1-SNAPSHOT.jar" -DgroupId="us.dot.jpo.ode" -DartifactId=jpo-ode-svcs -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\cv-data-service-library\\target\\cv-data-service-library-0.0.1-SNAPSHOT.jar" -DgroupId="com.wyocv" -DartifactId=cv-data-service-library -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\ode-data-logger\\resources\\ojdbc6.jar" -DgroupId="com.oracle" -DartifactId=ojdbc6 -Dversion="11.2.0.4" -Dpackaging=jar

mvn install:install-file -Dfile="C:\\Users\\kperry\\wyocv\\cv-data-service-library\\src\\main\\resources\\ojdbc8.jar" -DgroupId="com.oracle" -DartifactId=ojdbc8 -Dversion="12.2.0.1.0" -Dpackaging=jar
