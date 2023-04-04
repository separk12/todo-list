package com.amore.todolist.todo.adapter.web.in;

import com.amore.todolist.common.CommonControllerTest;
import com.amore.todolist.todo.adapter.web.model.AssignTodoRequest;
import com.amore.todolist.todo.adapter.web.model.CreateTodoRequest;
import com.amore.todolist.todo.adapter.web.model.UpdateTodoRequest;
import com.amore.todolist.todo.service.TodoManagementService;
import com.amore.todolist.todo.service.TodoQueryService;
import com.amore.todolist.todo.service.model.TodoQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest extends CommonControllerTest {

    @MockBean
    private TodoManagementService todoManagementService;
    @MockBean
    private TodoQueryService todoQueryService;

    @Test
    void createTodo() throws Exception {
        // given
        String url = "/api/todos";
        String documentPath = "todos/createTodo";
        Long todoNid = 1L;
        CreateTodoRequest request = CreateTodoRequest.builder()
                .task("task")
                .description("description")
                .userNid(1L)
                .executionDay(LocalDate.of(2023, 4, 4))
                .build();

        // when
        when(todoManagementService.createTodo(any())).thenReturn(todoNid);
        ResultActions resultActions = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        PayloadDocumentation.requestFields(
                                fieldWithPath("task").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("설명"),
                                fieldWithPath("userNid").type(JsonFieldType.NUMBER).description("유저Nid"),
                                fieldWithPath("executionDay").type(JsonFieldType.STRING).description("실행일")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER).description("null")
                        )
                ));

    }

    @Test
    void updateTodo() throws Exception {
        // given
        String url = "/api/todos/{todoNid}";
        String documentPath = "todos/updateTodo";
        Long todoNid = 1L;
        UpdateTodoRequest request = UpdateTodoRequest.builder()
                .task("task")
                .description("description")
                .priority("A")
                .status("COMPLETED")
                .build();

        // when
        Mockito.doNothing().when(todoManagementService).updateTodo(any());

        ResultActions resultActions = mockMvc.perform(
                put(url, todoNid)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RequestDocumentation.pathParameters(parameterWithName("todoNid").description("todoNid")),
                        PayloadDocumentation.requestFields(
                                fieldWithPath("task").type(JsonFieldType.STRING).description("제목").optional(),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("설명").optional(),
                                fieldWithPath("priority").type(JsonFieldType.STRING).description("중요도 (S, A, B, C, D)"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태 (IN_PROGRESS 진행중 / COMPLETED 완료 / CANCELLED 취소 / ASSIGN 위임)"),
                                fieldWithPath("memo").type(JsonFieldType.STRING).description("메모").optional()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("null")
                        )
                ));
    }

    @Test
    void deleteTodo() throws Exception {
        // given
        String url = "/api/todos/{todoNid}";
        String documentPath = "todos/deleteTodo";
        Long todoNid = 1L;

        // when
        Mockito.doNothing().when(todoManagementService).deleteTodo(any());

        ResultActions resultActions = mockMvc.perform(
                delete(url, todoNid)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RequestDocumentation.pathParameters(parameterWithName("todoNid").description("todoNid")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("null")
                        )
                ));
    }


    @Test
    void assign() throws Exception {
        // given
        String url = "/api/todos/{todoNid}/assign";
        String documentPath = "todos/assignTodo";
        Long todoNid = 1L;

        Long assigneeUserNid = 10L;
        AssignTodoRequest request = new AssignTodoRequest(assigneeUserNid);

        // when
        Mockito.doNothing().when(todoManagementService).assignTodo(any());
        ResultActions resultActions = mockMvc.perform(post(url, todoNid)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RequestDocumentation.pathParameters(parameterWithName("todoNid").description("todoNid")),
                        PayloadDocumentation.requestFields(
                                fieldWithPath("assigneeUserNid").type(JsonFieldType.NUMBER).description("위임할UserNid")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("null")
                        )
                ));

    }

    @Test
    void cancelAssign() throws Exception {
        // given
        String url = "/api/todos/{todoNid}/assign/cancel";
        String documentPath = "todos/assignCancelTodo";
        Long todoNid = 1L;

        Long assigneeUserNid = 10L;
        AssignTodoRequest request = new AssignTodoRequest(assigneeUserNid);

        // when
        Mockito.doNothing().when(todoManagementService).assignTodo(any());
        ResultActions resultActions = mockMvc.perform(post(url, todoNid)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RequestDocumentation.pathParameters(parameterWithName("todoNid").description("todoNid")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("null")
                        )
                ));

    }


    @Test
    void findTodos() throws Exception {
        // given
        String url = "/api/todos";
        String documentPath = "todos/findTodos";

        List<TodoQuery> todos = List.of(
                TodoQuery.builder().todoNid(1L)
                        .task("제목1")
                        .description("설명1")
                        .priorityOrder("S0")
                        .userName("user1")
                        .status("진행중")
                        .executionDay(LocalDate.of(2023, 4, 4))
                        .build(),
                TodoQuery.builder().todoNid(2L)
                        .task("제목2")
                        .description("설명2")
                        .priorityOrder("S1")
                        .userName("user1")
                        .status("진행중")
                        .executionDay(LocalDate.of(2023, 4, 4))
                        .build(),
                TodoQuery.builder().todoNid(3L)
                        .task("제목3")
                        .description("설명3")
                        .priorityOrder("A0")
                        .userName("user1")
                        .status("완료")
                        .executionDay(LocalDate.of(2023, 4, 4))
                        .build(),
                TodoQuery.builder().todoNid(4L)
                        .task("제목4")
                        .description("설명4")
                        .priorityOrder("B0")
                        .userName("위임(user2)")
                        .status("위임")
                        .executionDay(LocalDate.of(2023, 4, 4))
                        .build(),
                TodoQuery.builder().todoNid(5L)
                        .task("제목5")
                        .description("설명5")
                        .priorityOrder("B1")
                        .userName("위임(user3)")
                        .status("위임")
                        .executionDay(LocalDate.of(2023, 4, 4))
                        .build());

        // when
        when(todoQueryService.findTodos(any())).thenReturn(todos);
        ResultActions resultActions = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("executionDay", "2023-04-04")
                .param("userNid", String.valueOf(1L))
                .param("sortType", "ORDER"));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RequestDocumentation.requestParameters(
                                parameterWithName("userNid").description("담당유저Nid"),
                                parameterWithName("executionDay").description("실행일"),
                                parameterWithName("sortType").description("정렬조건(PRIORITY 중요도 / ORDER 우선순위)")

                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data[].todoNid").type(JsonFieldType.NUMBER).description("todo번호"),
                                fieldWithPath("data[].task").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("data[].description").type(JsonFieldType.STRING).description("설명"),
                                fieldWithPath("data[].priorityOrder").type(JsonFieldType.STRING).description("우선순위및중요도"),
                                fieldWithPath("data[].userName").type(JsonFieldType.STRING).description("담당자이름"),
                                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("data[].executionDay").type(JsonFieldType.STRING).description("실행일")
                        )));

    }
}
