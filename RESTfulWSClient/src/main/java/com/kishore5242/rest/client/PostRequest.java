package com.kishore5242.rest.client;

import com.google.gson.Gson;
import com.kishore5242.pojo.Todo;
import com.kishore5242.rest.service.ClientService;

public class PostRequest {
	
		public static void main(String[] args) {

			ClientService clientService = new ClientService();

			Todo todo = new Todo();
			todo.setTitle("My todo");
			todo.setUserId(123);
			todo.setCompleted("done");
			
			// convert to json
			String json = new Gson().toJson(todo);
			
			String responseJson = clientService.postJSON("https://jsonplaceholder.typicode.com/todos/", json, 3600);
						
			System.out.println(responseJson);

		}

}
