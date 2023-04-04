package com.amore.todolist.user.adapter.web.in;

import com.amore.todolist.common.CommonControllerTest;
import com.amore.todolist.user.service.UserQueryService;
import com.amore.todolist.user.service.model.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends CommonControllerTest {

    @MockBean
    private UserQueryService userQueryService;

    @Test
    void findUsers() throws Exception {
        // given
        String url = "/api/users";
        String documentPath = "users/findUsers";

        List<UserQuery> users = List.of(
                UserQuery.builder()
                        .userNid(1L)
                        .name("user1").build(),
                UserQuery.builder()
                        .userNid(2L)
                        .name("user2").build(),
                UserQuery.builder()
                        .userNid(3L)
                        .name("user3").build(),
                UserQuery.builder()
                        .userNid(4L)
                        .name("user4").build(),
                UserQuery.builder()
                        .userNid(5L)
                        .name("user5").build());

        // when
        when(userQueryService.findUsers()).thenReturn(users);
        ResultActions resultActions = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data[].userNid").type(JsonFieldType.NUMBER).description("user번호"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("이름")
                        )));

    }
}
