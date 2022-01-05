package in.kodecamp.testpoc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(PersonController.class)
public class TestPersonController {

  private final String FETCH_ALL_URL = "/person/all";
  private final String CREATE_NEW_URL = "/person/new";

  @Autowired
  private MockMvc mockMvc;

  @MockBean // use this bean
  private PersonService service;

  @Autowired
  private ObjectMapper objectMapper;


  public void setUp() {
    MockitoAnnotations.openMocks(this);
    MockMvc mvc = MockMvcBuilders
        .standaloneSetup(new PersonController(service))
        // .setControllerAdvice(new YourExceptionHandler())
        // .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .build();
  }

  @Test
  public void shouldReturnAllPerson() throws Exception {

    List<PersonEntity> people = List.of(
      PersonEntity.from(UUID.randomUUID(), "Sunil", "Naini"),
      PersonEntity.from(UUID.randomUUID(),"Rakesh", "Lucknow"),
      PersonEntity.from(UUID.randomUUID(),"Suresh", "MP"));

    when(service.findAll()).thenReturn(people);

    this.mockMvc.perform(get(FETCH_ALL_URL))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.values").hasJsonPath())
      .andExpect(jsonPath("$.values").isArray())
      .andExpect(jsonPath("$.values.length()").value(3))

      .andExpect(jsonPath("$.values[0].name").value("Sunil"))
      .andExpect(jsonPath("$.values[0].address").value("Naini"))

      .andExpect(jsonPath("$.values[1].name").value("Rakesh"))
      .andExpect(jsonPath("$.values[1].address").value("Lucknow"))

      .andExpect(jsonPath("$.values[2].name").value("Suresh"))
      .andExpect(jsonPath("$.values[2].address").value("MP"));

    verify(service, times(1)).findAll();
  }

  @Test
  public void shouldCreateNewPerson() throws Exception,JsonProcessingException {

    PersonEntity pe = PersonEntity.from("Sunil", "Naini");

    UUID id = UUID.randomUUID();

    PersonEntity resultPe = PersonEntity.from(id, "Sunil", "Naini");

    when(service.createNew(any())).thenReturn(resultPe);

    this.mockMvc.perform(post(CREATE_NEW_URL)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(pe)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))

      .andDo(print())

      .andExpect(jsonPath("$.value").hasJsonPath())

      .andExpect(jsonPath("$.value.id").exists())
      .andExpect(jsonPath("$.value.name").value("Sunil"))
      .andExpect(jsonPath("$.value.address").value("Naini"));

    verify(service, times(1)).createNew(any());
  }
}
