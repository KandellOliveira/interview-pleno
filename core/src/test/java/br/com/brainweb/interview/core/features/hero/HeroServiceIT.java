package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired private TestRestTemplate testRestTemplate;

    @Test
    public void getHeroUuidAreIncorretShouldReturnCode404(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hero/c590ae9a-7709-4c04-825a-a73166df3d86", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void getHeroUuidAreCorretShouldReturnCode200(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hero/c590ae9a-7709-4c04-825a-a73166df3d86", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void listHeroNameAreIncorretShouldReturnCode200(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hero/nome/batman", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void listrHeroNameAreCorretShouldReturnCode200(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hero/nome/batman", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
