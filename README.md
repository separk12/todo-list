# Todo-list

## :: 요구사항

### 1. 초기값으로 생성된 5명의 프로필을 조회할 수 있다.

### 2. 특정 담당자, 특정 일자의 todo list 를 중요도/우선순위 로 정렬하여 조회할 수 있다.

### 3.todo 를 생성할 수 있다.
+ task(required), description(optional),executionDay(required) 작성가능
+ 담당자(required) 필수 선택
+ 상태(status) 초기값은 진행중
+ 우선순위 설정 가능
  + S,A,B,C,D
  + ex) 동일날짜의todo우선순위: S0,S1,A0,A1,A2,A3 (O)
  + ex) 동일날짜의todo우선순위: S0,S1,A0,A1,A3 (X) -> A2 가누락됨

### 4. todo를 수정할 수 있다.
+ 수정가능한값: task, description, priority, status

###  5. todo를 다른사람에게 위임할 수 있다.
+ 위임한사람: 위임+위임받은사람으로 표시 **ex) 위임(담당자A)**
+ 위임받은사람: A0 중요도로 todo가 새로 생성되며 위임한담당자가 표시됨
+ 위임받은 todo는 재 위임할 수 없다.

### 6. 위임받은 todo를 취소할 수 있다.
+ 위임을 취소하면 원상복귀된다.

### 7. todo를 삭제할 수 있다.
+ 해당 todo의 위임관계도 삭제된다.

------------








