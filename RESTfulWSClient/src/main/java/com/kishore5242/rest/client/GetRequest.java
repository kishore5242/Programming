package com.kishore5242.rest.client;

import com.google.gson.Gson;
import com.kishore5242.pojo.Todo;
import com.kishore5242.rest.service.ClientService;

public class GetRequest {

	public static void main(String[] args) {

		ClientService clientService = new ClientService();

		String responseJson = clientService.getJSON("https://jsonplaceholder.typicode.com/todos/201", 3600);

		// parse
		Todo todo = new Gson().fromJson(responseJson, Todo.class);
		
		System.out.println(todo.getTitle());
		
	}
}
