| 기능       | Method | URL             | request                                 | response                                                             | 상태코드       |
|----------|--------|-----------------|-----------------------------------------|----------------------------------------------------------------------|------------|
| 일정 생성    | POST   | /schedules      | `{"todo":"", "name":"", "password":""}` | `{"id":"", "todo":"", "name":"", "createAt":"", "modifiedAt":""}`      | 201: 정상 생성 |
| 전체 일정 조회 | GET    | /schedules      | `{"name":"", modifiedAt:""}`            | `[{"id":"", "todo":"", "name":"", "createAt":"", "modifiedAt":""}, {"id":"", "todo":"", "name":"", "createAt":"", "modifiedAt":""}]` | 200: 정상 조회 |
| 선택 일정 조회 | GET    | /schedules/{id} | 요청 param                                | `{"id":"", "todo":"", "name":"", "createAt":"", "modifiedAt":""}`                                    | 200: 정상 조회 |
| 선택 일정 수정 | PATCH  | /schedules/{id} | `{"todo":"", "name":"", "password":""}`   | `{"id":"", "todo":"", "name":"", "createAt":"", "modifiedAt":""}`                                    | 200: 정상 변경 |
| 선택 일정 삭제 | DELETE | /schedules/{id} | `{"password":""}`                              | -                                                                    | 204: 정상 삭제 |

