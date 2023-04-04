# Todo-list

## :paperclip: 요구사항

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

## :paperclip: api 문서

<div id="header">

# TODO API

<div id="toc" class="toc2">

<div id="toctitle">Table of Contents</div>

*   [User](#_user)
  *   [1\. <span class="small">전체 사용자 조회</span>](#_전체_사용자_조회)
*   [Todos](#_todos)
  *   [2\. <span class="small">할일 조회</span>](#_할일_조회)
  *   [3\. <span class="small">할일 생성</span>](#_할일_생성)
  *   [4\. <span class="small">할일 수정</span>](#_할일_수정)
  *   [5\. <span class="small">할일 삭제</span>](#_할일_삭제)
  *   [6\. <span class="small">할일 위임</span>](#_할일_위임)
  *   [7\. <span class="small">위임된 할일 취소</span>](#_위임된_할일_취소)

</div>

</div>

<div id="content">

<div id="preamble">

<div class="sectionbody">

<div class="paragraph">

Todo-list api 문서

</div>

</div>

</div>

# [User](#_user)

<div class="sect1">

## [1\. <span class="small">전체 사용자 조회</span>](#_전체_사용자_조회)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>초기생성된 전체 사용자 정보를 조회합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    GET /api/users HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Host: localhost:8080

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 183

    {"code":"200","message":"OK","data":[{"userNid":1,"name":"user1"},{"userNid":2,"name":"user2"},{"userNid":3,"name":"user3"},{"userNid":4,"name":"user4"},{"userNid":5,"name":"user5"}]}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].userNid`

</td>

<td class="tableblock halign-left valign-top">

`Number`

</td>

<td class="tableblock halign-left valign-top">

user번호

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].name`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

이름

</td>

</tr>

</tbody>

</table>

</div>

</div>

# [Todos](#_todos)

<div class="sect1">

## [2\. <span class="small">할일 조회</span>](#_할일_조회)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>특정 실행일의 특정 담당자의 할일을 조회합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    GET /api/todos?executionDay=2023-04-04&userNid=1&sortType=ORDER HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Host: localhost:8080

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 765

    {"code":"200","message":"OK","data":[{"todoNid":1,"task":"제목1","description":"설명1","priorityOrder":"S0","userName":"user1","status":"진행중","executionDay":"2023-04-04"},{"todoNid":2,"task":"제목2","description":"설명2","priorityOrder":"S1","userName":"user1","status":"진행중","executionDay":"2023-04-04"},{"todoNid":3,"task":"제목3","description":"설명3","priorityOrder":"A0","userName":"user1","status":"완료","executionDay":"2023-04-04"},{"todoNid":4,"task":"제목4","description":"설명4","priorityOrder":"B0","userName":"위임(user2)","status":"위임","executionDay":"2023-04-04"},{"todoNid":5,"task":"제목5","description":"설명5","priorityOrder":"B1","userName":"위임(user3)","status":"위임","executionDay":"2023-04-04"}]}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Request parameters</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Parameter</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`userNid`

</td>

<td class="tableblock halign-left valign-top">

담당유저Nid

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`executionDay`

</td>

<td class="tableblock halign-left valign-top">

실행일

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`sortType`

</td>

<td class="tableblock halign-left valign-top">

정렬조건(PRIORITY 중요도 / ORDER 우선순위)

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].todoNid`

</td>

<td class="tableblock halign-left valign-top">

`Number`

</td>

<td class="tableblock halign-left valign-top">

todo번호

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].task`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

제목

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].description`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

설명

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].priorityOrder`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

우선순위및중요도

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].userName`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

담당자이름

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].status`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

상태

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data[].executionDay`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

실행일

</td>

</tr>

</tbody>

</table>

</div>

</div>

<div class="sect1">

## [3\. <span class="small">할일 생성</span>](#_할일_생성)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>할일을 생성합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    POST /api/todos HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Content-Length: 83
    Host: localhost:8080

    {"task":"task","description":"description","userNid":1,"executionDay":"2023-04-04"}

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 38

    {"code":"200","message":"OK","data":1}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Request fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`task`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

제목

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`description`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

설명

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`userNid`

</td>

<td class="tableblock halign-left valign-top">

`Number`

</td>

<td class="tableblock halign-left valign-top">

유저Nid

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`executionDay`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

실행일

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data`

</td>

<td class="tableblock halign-left valign-top">

`Number`

</td>

<td class="tableblock halign-left valign-top">

null

</td>

</tr>

</tbody>

</table>

</div>

</div>

<div class="sect1">

## [4\. <span class="small">할일 수정</span>](#_할일_수정)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>할일을 수정합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    PUT /api/todos/1 HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Content-Length: 79
    Host: localhost:8080

    {"task":"task","description":"description","priority":"A","status":"COMPLETED"}

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 41

    {"code":"200","message":"OK","data":null}

</div>

</div>

<div class="paragraph">


#### <span class="red big">Path parameters</span>

</div>

<table class="tableblock frame-all grid-all stretch"><caption class="title">Table 2\./api/todos/{todoNid}</caption> <colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Parameter</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`todoNid`

</td>

<td class="tableblock halign-left valign-top">

todoNid

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Request fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`task`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

제목

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`description`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

설명

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`priority`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

중요도 (S, A, B, C, D)

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`status`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

상태 (IN_PROGRESS 진행중 / COMPLETED 완료 / CANCELLED 취소 / ASSIGN 위임)

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`memo`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

메모

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data`

</td>

<td class="tableblock halign-left valign-top">

`Null`

</td>

<td class="tableblock halign-left valign-top">

null

</td>

</tr>

</tbody>

</table>

</div>

</div>

<div class="sect1">

## [5\. <span class="small">할일 삭제</span>](#_할일_삭제)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>할일을 삭제합니다. 위임정보가 있으면 함께 삭제됩니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    DELETE /api/todos/1 HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Host: localhost:8080

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 41

    {"code":"200","message":"OK","data":null}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Path parameters</span>

</div>

<table class="tableblock frame-all grid-all stretch"><caption class="title">Table 1\. /api/todos/{todoNid}</caption> <colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Parameter</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`todoNid`

</td>

<td class="tableblock halign-left valign-top">

todoNid

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data`

</td>

<td class="tableblock halign-left valign-top">

`Null`

</td>

<td class="tableblock halign-left valign-top">

null

</td>

</tr>

</tbody>

</table>

</div>

</div>

<div class="sect1">

## [6\. <span class="small">할일 위임</span>](#_할일_위임)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>할일을 다른 담당자에게 위임합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    POST /api/todos/1/assign HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Content-Length: 22
    Host: localhost:8080

    {"assigneeUserNid":10}

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 41

    {"code":"200","message":"OK","data":null}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Path parameters</span>

</div>

<table class="tableblock frame-all grid-all stretch"><caption class="title">Table 2\. /api/todos/{todoNid}/assign</caption> <colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Parameter</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`todoNid`

</td>

<td class="tableblock halign-left valign-top">

todoNid

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Request fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`assigneeUserNid`

</td>

<td class="tableblock halign-left valign-top">

`Number`

</td>

<td class="tableblock halign-left valign-top">

위임할UserNid

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data`

</td>

<td class="tableblock halign-left valign-top">

`Null`

</td>

<td class="tableblock halign-left valign-top">

null

</td>

</tr>

</tbody>

</table>

</div>

</div>

<div class="sect1">

## [7\. <span class="small">위임된 할일 취소</span>](#_위임된_할일_취소)

<div class="sectionbody">

<div class="paragraph">

#### <span class="red big">Overview</span>

</div>

<div class="listingblock">

<div class="content">

<pre>위임받은 할일을 취소합니다.</pre>

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP request</span>

</div>

<div class="listingblock">

<div class="content">

    POST /api/todos/1/assign/cancel HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Host: localhost:8080

</div>

</div>

<div class="paragraph">

#### <span class="red big">HTTP response</span>

</div>

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 41

    {"code":"200","message":"OK","data":null}

</div>

</div>

<div class="paragraph">

#### <span class="red big">Path parameters</span>

</div>

<table class="tableblock frame-all grid-all stretch"><caption class="title">Table 3\. /api/todos/{todoNid}/assign/cancel</caption> <colgroup><col style="width: 50%;"> <col style="width: 50%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Parameter</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`todoNid`

</td>

<td class="tableblock halign-left valign-top">

todoNid

</td>

</tr>

</tbody>

</table>

<div class="paragraph">

#### <span class="red big">Response fields</span>

</div>

<table class="tableblock frame-all grid-all stretch"><colgroup><col style="width: 33.3333%;"> <col style="width: 33.3333%;"> <col style="width: 33.3334%;"></colgroup> 

<thead>

<tr>

<th class="tableblock halign-left valign-top">Path</th>

<th class="tableblock halign-left valign-top">Type</th>

<th class="tableblock halign-left valign-top">Description</th>

</tr>

</thead>

<tbody>

<tr>

<td class="tableblock halign-left valign-top">

`code`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과코드

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`message`

</td>

<td class="tableblock halign-left valign-top">

`String`

</td>

<td class="tableblock halign-left valign-top">

결과메시지

</td>

</tr>

<tr>

<td class="tableblock halign-left valign-top">

`data`

</td>

<td class="tableblock halign-left valign-top">

`Null`

</td>

<td class="tableblock halign-left valign-top">

null

</td>

</tr>

</tbody>

</table>

</div>

</div>

</div>

<div id="footer">

<div id="footer-text">Last updated 2023-04-04 01:33:15 +0900</div>

</div>
</div>
