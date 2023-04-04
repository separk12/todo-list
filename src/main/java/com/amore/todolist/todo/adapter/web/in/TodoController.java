package com.amore.todolist.todo.adapter.web.in;

import com.amore.todolist.common.model.CommonResponse;
import com.amore.todolist.todo.adapter.web.model.AssignTodoRequest;
import com.amore.todolist.todo.adapter.web.model.CreateTodoRequest;
import com.amore.todolist.todo.adapter.web.model.TodoQueryFilterRequest;
import com.amore.todolist.todo.adapter.web.model.UpdateTodoRequest;
import com.amore.todolist.todo.service.TodoManagementService;
import com.amore.todolist.todo.service.TodoQueryService;
import com.amore.todolist.todo.service.model.TodoQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class TodoController {

    private final TodoManagementService todoManagementService;
    private final TodoQueryService todoQueryService;

    @PostMapping("/todos")
    public CommonResponse<Long> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        return CommonResponse.ok(todoManagementService.createTodo(request.convertTo()));
    }

    @PutMapping("/todos/{todoNid}")
    public CommonResponse updateTodo(@PathVariable Long todoNid, @RequestBody UpdateTodoRequest request) {
        todoManagementService.updateTodo(request.convertTo(todoNid));
        return CommonResponse.ok();
    }

    @DeleteMapping("/todos/{todoNid}")
    public CommonResponse deleteTodo(@PathVariable Long todoNid) {
        todoManagementService.deleteTodo(todoNid);
        return CommonResponse.ok();
    }

    @PostMapping("/todos/{todoNid}/assign")
    public CommonResponse assign(@PathVariable Long todoNid, @Valid @RequestBody AssignTodoRequest request) {
        todoManagementService.assignTodo(request.convertTo(todoNid));
        return CommonResponse.ok();
    }

    @PostMapping("/todos/{todoNid}/assign/cancel")
    public CommonResponse cancelAssign(@PathVariable Long todoNid) {
        todoManagementService.cancelAssign(todoNid);
        return CommonResponse.ok();
    }

    @GetMapping("/todos")
    public CommonResponse<List<TodoQuery>> findTodos(@Valid TodoQueryFilterRequest request) {
        return CommonResponse.ok(todoQueryService.findTodos(request.convertTo()));
    }

}
