API 명세
==================================
| 기능       | Method | URL             | request                                                                                                                                                             | response                                                                                                                                                                                                                                                          | 상태코드       |
|----------|--------|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| 유저 생성    | POST   | /users          | `{"name":"testUser1", "password":"asdasdasd", "email":"asdfg@hjk.qwe"}`                                                                                             | `{"uid":1, "name":"testUser1", "email": "asdfg@hjk.qwe", "createAt":"2025-05-25T17:07:03", "modifiedAt":"2025-05-25T17:07:03"}`                                                                                                                                   | 201: 정상 생성 |
| 일정 생성    | POST   | /schedules      | `{"todo":"works1", "name":"user1", "password":"asdasdasd", "uid":1}` <br/>name 생략 시 등록된 user의 name으로 자동 등록                                                          | `{"id":"1", "todo":"works1", "name":"user1", "createAt":"2025-05-25T17:09:10", "modifiedAt":""2025-05-25T17:09:10", "uid": 1}`                                                                                                                                    | 201: 정상 생성 |
| 전체 일정 조회 | GET    | /schedules      | 요청 쿼리<br/>p: 페이지 번호, 기본 값 1<br/>pSize: 페이지의 크기, 기본 값 5<br/>name: 스케줄의 name이 일치하는 데이터 찾기<br/>modifiedAt: 스케줄의 modifiedAt이 일치하는 데이터 찾기<br/>uid: 스케줄의 uid가 일치하는 데이터 찾기 | `[{"id":"1", "todo":"works1", "name":"user1", "createAt":"2025-05-25T17:09:10", "modifiedAt":""2025-05-25T17:09:10", "uid": 1}, {"id":"1", "todo":"works2", "name":"testUser2", "createAt":"2025-05-25T17:32:51", "modifiedAt":"2025-05-25T17:32:51", "uid": 2}]` | 200: 정상 조회 |
| 선택 일정 조회 | GET    | /schedules/{id} | -                                                                                                                                                                   | `{"id":"1", "todo":"works1", "name":"user1", "createAt":"2025-05-25T17:09:10", "modifiedAt":"2025-05-25T17:09:10", "uid": 1}`                                                                                                                                     | 200: 정상 조회 |
| 선택 일정 수정 | PATCH  | /schedules/{id} | `{"todo":"updatedcontent1", "name":"updated1", "password":"asdasdasd"}`   <br/>name, todo 둘다 입력하지 않을 시 에러 반환                                                        | `{"id":"1", "todo":"updatedcontent1", "name":"updated1", "createAt":"2025-05-25T17:09:10", "modifiedAt":"2025-05-25T17:12:25", "uid": 1}`                                                                                                                         | 200: 정상 변경 |
| 선택 일정 삭제 | DELETE | /schedules/{id} | `{"password":"asdasd"}`                                                                                                                                             | -                                                                                                                                                                                                                                                                 | 204: 정상 삭제 |

ERD
===================================
![Blank diagram.png](./ERD.png)

문제 답변
=====================================
**적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)**

- Controller(UserController, ScheduleController)관심사: 클라이언트의 요청을 받아 Sevice에 전달, 응답을 반환함.
- Service(UserService, ScheduleController)관심사: 사용자의 요청 사항을 처리(비즈니스 로직), DB와의 상호작용이 필요시 Repository로 요청함
- Repository(UserRepository, ScheduleRepository)관심사: DB와의 상호작용만을 담당

**RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?**

- 성숙도 모델 level 2를 준수함 -> API별 적절한 Http Method 사용
    - 일정 선택 조회, 일정 전체 조회 -> 읽기 용도로 사용하기 위해 GET
    - 유저 생성, 스케줄 생성 ->새로운 리소스를 추가하기 때문에 POST
    - 선택 일정 수정 -> 기존 리소스의 상태를 변경하기 때문에 PATCH
    - 선택 일정 삭제 -> 리소스를 삭제하기 위해 DELETE
- 각각의 API 요청에 따라 적절한 HTTP 상태코드를 반환
  -> 예시)스케줄 생성, 일정 수정, 삭제시 패스워드가 틀릴 경우 401상태코드를 반환한다
- URI에는 사용자의 정보를 포함하지 않는다
- 쿼리에는 검색 파라미터, 페이징 등 민감하지 않은 정보만 포함.
  비밀번호 등의 민감한 정보는 RequestBody에 의해 전달.
- 데이터에 대해 users, schedules같은 복수형, 단수를 사용.

**수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)**
비밀번호 등의 정보를 전달하기 위해 Request Body 를 사용