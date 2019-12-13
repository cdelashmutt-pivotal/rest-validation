package io.pivotal.pa.restvalidation;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ObjectController.class)
class ObjectControllerTests {

	@Autowired
	private MockMvc mockMvc;  

	@Test
	void validationSucceeds() 
	throws Exception {
		DataObject validData = DataObject.with()
			.name("Chris DeLashmutt")
			.ssn("123-45-6789")
		.build();

		ObjectMapper objectMapper = new ObjectMapper();

		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(validData)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(validData.getName())))
			.andExpect(jsonPath("$.ssn", is(validData.getSsn())));
	}

	@Test
	void validationOfNameFails()
	throws Exception {
		DataObject invalidName = DataObject.with()
			.name("")
			.ssn("123-45-6789")
		.build();

		ObjectMapper objectMapper = new ObjectMapper();

		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidName)))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	void validationOfSSNFails()
	throws Exception {
		DataObject invalidName = DataObject.with()
			.name("Chris DeLashmutt")
			.ssn("123-45-6789999999")
		.build();

		ObjectMapper objectMapper = new ObjectMapper();

		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidName)))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	void validationOfNameAndSSNFails()
	throws Exception {
		DataObject invalidName = DataObject.with()
			.name("")
			.ssn("123-45-6789999999")
		.build();

		ObjectMapper objectMapper = new ObjectMapper();

		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidName)))
			.andDo(print())
			.andExpect(status().isBadRequest());

	}

	@Test
	void validationOfNoDataFails()
	throws Exception {
		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	void validationOfBadJsonFails()
	throws Exception {
		this.mockMvc
			.perform(
				get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("}}}THIS ISN'T JSON{{{"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}


}
