package com.amore.todolist.todo.service;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.exception.CustomException;
import com.amore.todolist.common.exception.ExceptionType;
import com.amore.todolist.todo.adapter.persistence.TodoAssignRepository;
import com.amore.todolist.todo.adapter.persistence.TodoRepository;
import com.amore.todolist.todo.domain.Todo;
import com.amore.todolist.todo.domain.TodoAssign;
import com.amore.todolist.todo.service.model.AssignTodoCommand;
import com.amore.todolist.todo.service.model.CreateTodoCommand;
import com.amore.todolist.todo.service.model.UpdateTodoCommand;
import com.amore.todolist.user.adapter.persistence.UserRepository;
import com.amore.todolist.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TodoManagementService {

    private final TodoRepository todoRepository;
    private final TodoAssignRepository todoAssignRepository;
    private final UserRepository userRepository;

    public Long createTodo(CreateTodoCommand command) {
        User user = userRepository.findById(command.getUserNid()).orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_USER));
        List<Todo> todos = todoRepository.findByUserNidAndPriority(command.getUserNid(), command.getExecutionDay(), command.getPriority());
        Optional<Todo> todoMax = todos.stream().max(Comparator.comparing(Todo::getOrder));
        long order = 0L;
        if (todoMax.isPresent()) {
            order = todoMax.get().getOrder() + 1L;
        }
        return todoRepository.save(command.toEntity(user, order)).getTodoNid();
    }

    public void updateTodo(UpdateTodoCommand command) {
        Todo savedTodo = todoRepository.findById(command.getTodoNid())
                .orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_TODO));
        List<Todo> origins = todoRepository.findByUserNid(savedTodo.getUser().getUserNid(), savedTodo.getExecutionDay());

        // 중요도가 변경되었을 경우
        if (command.getPriority() != savedTodo.getPriority()) {
            // 기존 중요도 우선순위 정렬
            List<Todo> originList = origins.stream()
                    .filter(it -> it.getPriority() == savedTodo.getPriority())
                    .filter(it -> it.getOrder() > savedTodo.getOrder())
                    .collect(Collectors.toList());
            originList.forEach(
                    it -> {
                        it.updateOrder(it.getOrder() - 1);
                        todoRepository.update(it);
                    }
            );

            // 변경된 중요도 우선순위 변경
            long order = 0L;
            Optional<Todo> todoMax = origins.stream()
                    .filter(it -> it.getPriority() == command.getPriority())
                    .max(Comparator.comparing(Todo::getOrder));
            if (todoMax.isPresent()) order = todoMax.get().getOrder() + 1;
            savedTodo.updateOrder(order);
        }

        savedTodo.update(command);
        todoRepository.update(savedTodo);
    }


    public void deleteTodo(Long todoNid) {
        Todo savedTodo = todoRepository.findById(todoNid).orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_TODO));
        List<Todo> todoList = todoRepository.findByUserNidAndPriority(savedTodo.getUser().getUserNid(), savedTodo.getExecutionDay(), savedTodo.getPriority());
        List<Todo> updateList = todoList.stream()
                .filter(it -> it.getOrder() > savedTodo.getOrder())
                .collect(Collectors.toList());
        updateList.forEach(
                it -> {
                    it.updateOrder(it.getOrder() - 1);
                    todoRepository.update(it);
                }
        );

        // 위임관계 삭제
        if (savedTodo.getTodoAssign() != null) {
            cancelAssign(savedTodo.getTodoAssign().getAssigneeTodoNid());
        }
        todoRepository.deleteByTodoNid(todoNid);
    }

    public void assignTodo(AssignTodoCommand command) {
        Todo savedTodo = todoRepository.findById(command.getTodoNid())
                .orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_TODO));

        if (savedTodo.getTodoAssign() != null) {
            throw CustomException.of(ExceptionType.DISABLED_ASSIGN_TODO);
        }

        User assigneeUser = userRepository.findById(command.getAssigneeUserNid())
                .orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_USER));

        // 위임받을 담당자 우선순위 정하기
        List<Todo> assigneeTodos = todoRepository.findByUserNidAndPriority(command.getAssigneeUserNid(), savedTodo.getExecutionDay(), Priority.A);
        long order = 0L;
        Optional<Todo> todoMax = assigneeTodos.stream().max(Comparator.comparing(Todo::getOrder));
        if (todoMax.isPresent()) order = todoMax.get().getOrder() + 1;

        // 위임된 todo 추가
        Todo newAssignTodo = todoRepository.save(Todo.ofAssign(savedTodo, assigneeUser, Priority.A, order));
        TodoAssign todoAssign = todoAssignRepository.save(command.toEntity(savedTodo.getUser(), assigneeUser, newAssignTodo.getTodoNid(), savedTodo.getStatus()));
        savedTodo.assign(todoAssign);
        todoRepository.update(savedTodo);

        // 위임된 todo에 assign정보 저장
        newAssignTodo.assign(todoAssign);
        todoRepository.update(newAssignTodo);
    }


    public void cancelAssign(Long todoNid) {
        Todo assigneeTodo = todoRepository.findById(todoNid)
                .orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_TODO));

        if (assigneeTodo.getTodoAssign() == null) {
            throw CustomException.of(ExceptionType.NOT_FOUND_ASSIGN_TODO);
        }

        TodoAssign todoAssign = assigneeTodo.getTodoAssign();

        // 위임받았던 담당자 해당 중요도 재정렬
        List<Todo> assigneeTodos = todoRepository.findByUserNidAndPriority(todoAssign.getAssigneeUser().getUserNid(), assigneeTodo.getExecutionDay(), Priority.A);
        List<Todo> assigneeUpdateList = assigneeTodos.stream()
                .filter(it -> it.getOrder() > assigneeTodo.getOrder())
                .collect(Collectors.toList());
        assigneeUpdateList.forEach(
                it -> {
                    it.updateOrder(it.getOrder() - 1);
                    todoRepository.update(it);
                }
        );

        Todo assignerTodo = todoRepository.findById(todoAssign.getAssignerTodoNid())
                .orElseThrow(() -> CustomException.of(ExceptionType.NOT_FOUND_TODO));

        todoAssignRepository.delete(todoAssign.getTodoAssignNid());
        todoRepository.deleteByTodoNid(assigneeTodo.getTodoAssign().getAssigneeTodoNid());

        assignerTodo.cancelAssign(todoAssign.getStatus());
        todoRepository.update(assignerTodo);
    }

}
