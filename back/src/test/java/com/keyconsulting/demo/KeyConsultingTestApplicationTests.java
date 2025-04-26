package com.keyconsulting.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyconsulting.demo.model.Task;
import com.keyconsulting.demo.service.TaskService;

@SpringBootTest
@AutoConfigureMockMvc
class KeyConsultingTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TaskService taskService; // On utilise le vrai service avec ses données en mémoire !

	@Test
	void fetchAllTasks_shouldReturnAllTasks() throws Exception {
		mockMvc.perform(get("/task")) // Mets ton path ici si besoin
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(taskService.getAllTasks().size()))
				.andExpect(jsonPath("$[0].id").value(taskService.getAllTasks().get(0).getId()))
				.andExpect(jsonPath("$[1].label").value(taskService.getAllTasks().get(1).getLabel()));
	}

	@Test
	void fetchTaskById_shouldReturnCorrectTask() throws Exception {
		String taskId = "uuid-example-1";

		mockMvc.perform(get("/task/{taskId}", taskId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(taskId))
				.andExpect(jsonPath("$.label").value("Sortir Médor"));
	}

	@Test
	void fetchAllTaskToComplete_shouldReturnOnlyIncompleteTasks() throws Exception {
		mockMvc.perform(get("/task/todo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(taskService.getAllTaskToComplete().size()))
				.andExpect(jsonPath("$[0].completed").value(false));
	}

	@Test
	void addNewTask_shouldAddTask() throws Exception {
		Task newTask = new Task(null, "Nouvelle tâche", "Description de la tâche", false);

		mockMvc.perform(post("/task")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(newTask)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.label").value("Nouvelle tâche"))
				.andExpect(jsonPath("$.description").value("Description de la tâche"))
				.andExpect(jsonPath("$.id").isNotEmpty());
	}

	@Test
	void updateTaskStatus_shouldUpdateStatus() throws Exception {
		String taskId = "uuid-example-2";

		mockMvc.perform(put("/task/{taskId}/status", taskId)
				.param("newStatus", "true"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.completed").value(true));
	}

}
