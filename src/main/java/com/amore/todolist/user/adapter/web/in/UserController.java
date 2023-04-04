package com.amore.todolist.user.adapter.web.in;

import com.amore.todolist.common.model.CommonResponse;
import com.amore.todolist.user.service.UserQueryService;
import com.amore.todolist.user.service.model.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class UserController {

    private final UserQueryService userQueryService;

    @GetMapping("/users")
    public CommonResponse<List<UserQuery>> findUsers() {
        return CommonResponse.ok(userQueryService.findUsers());
    }
}
