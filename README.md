# cron-parser

### Main Class : `org.example.CronParser`

### How to run it
- first you have to build the jar of this programme. Use below command to create the jar.<br>
  `mvn clean install`
- It will create the jar in `/target` directory by the name `CronParser-1.0-SNAPSHOT.jar`
- To run the the jar use the below command<br>
  `java -cp target/CronParser-1.0-SNAPSHOT.jar  org.example.CronParser <CRON EXPRESSION>`


### Example command and outpuy

#### Command
`java -cp target/CronParser-1.0-SNAPSHOT.jar  org.example.CronParser "*/15 0 1,15  * 1-5 /usr/bin/find"`
#### Output
```
minute          0 15 30 45
hour            0
day of month    1 15
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5
command         /usr/bin/find
```