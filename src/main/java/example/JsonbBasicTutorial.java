/**
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package example;

import org.apache.johnzon.jsonb.JohnzonBuilder;

import javax.json.bind.Jsonb;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonbBasicTutorial {

	public static void main(String[] args) throws IOException {

		PrintWriter writer = null;

		try {
			// Read the JSON file into a java.lang.String
			String data = new String(Files.readAllBytes(Paths.get("MOCK_DATA.json")));

			// You need a instance of the Johnzon Jsonb class to marshal JSON to pojos.
			Jsonb jsonb = new JohnzonBuilder().build();

			// Convert a java.lang.String of JSON data into an List of Person type
			List<Person> persons = jsonb.fromJson(data, new ArrayList<Person>() {
			}.getClass().getGenericSuperclass());

			// Marshal the Person POJOs back into JSON
			String myData = jsonb.toJson(persons);
			
			// Create a print writer for the file MY_DATA.json
			writer = new PrintWriter("MY_DATA.json", "UTF-8");
			
			// write JSON data to MY_DATA.json
			writer.print(myData);

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}