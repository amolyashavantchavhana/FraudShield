# investigation-service

**Module 8** - Reviews flagged transactions, manages cases and records outcomes.

| Item | Value |
| --- | --- |
| Planned port | 8088 |
| Base package | `com.fraudshield.investigation` |
| PostgreSQL schema | `investigation` (role `investigation_svc`) |
| Needs Kafka later | yes |
| Needs Redis later | no |
| Called by (REST) | Investigators (UI / API) |
| Calls (REST) | Transaction Service |
| Events produced | `case.updated` |
| Events consumed | `alert.raised` |
| Developer | _[to be assigned in Step 1]_ |

## Run

```bash
./mvnw -pl services/investigation-service spring-boot:run
```


## Package layout

Standard layout (see root `docs/coding-standards.md`); each package has a `package-info.java` describing its role.

```
src/main/java/com/fraudshield/investigation/
|-- InvestigationApplication.java
|-- controller/
|-- dto/request/
|-- dto/response/
|-- entity/
|-- repository/
|-- service/
|-- service/impl/
|-- mapper/
|-- validation/
|-- exception/
|-- config/
|-- security/
|-- enums/
|-- client/
|-- messaging/producer/
|-- messaging/consumer/
|-- messaging/event/
|-- messaging/config/
```

Request flow: Controller -> Request DTO + validation -> Service -> Repository -> Database.
Kafka flow: Topic -> Consumer -> Service -> Repository or next event.

## Empty skeleton

- `pom.xml` contains only `spring-boot-starter-web` (enough to start the app).
- `src/main/resources/application.properties` is **empty** - the module developer fills it in (port, database, Kafka, Redis, etc.). No credentials are stored in the repo.
- Packages contain only `package-info.java`; no business code, entities or tests yet.

Add dependencies only when you need them. Likely candidates for this service:

- spring-kafka
- spring-boot-starter-data-jpa + postgresql (+ flyway-core if you use migrations)
- spring-boot-starter-validation
- spring-boot-starter-security
- spring-boot-starter-actuator
- springdoc-openapi-starter-webmvc-ui

## Before you start coding

- [ ] Module ownership and scope approved (Step 1, 2)
- [ ] REST contract in `contracts/openapi/` approved (Step 3)
- [ ] Event contracts in `contracts/events/` approved (Step 4)
- [ ] Tables designed and added in `src/main/resources/db/migration/` (add Flyway first) (Step 5)
