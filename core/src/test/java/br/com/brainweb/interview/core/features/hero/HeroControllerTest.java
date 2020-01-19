package br.com.brainweb.interview.core.features.hero;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class HeroControllerTest {

    private MockMvc mockMvc;

    private final UUID idDefault = UUID.fromString("c590ae9a-7709-4c04-825a-a73166df3d86");

    @Autowired private  HeroController heroController;
    @Autowired private HeroRepository heroRepository;

    @Before
    public  void sehtUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(heroController).build();
    }

    public void testSaveHero() throws Exception{

        MultiValueMap<String, String> heroMap = new LinkedMultiValueMap<>();
        heroMap.add("name", "batman");
        heroMap.add("race", "HUMAN");

        MultiValueMap<String, String> powerStatsMap = new LinkedMultiValueMap<>();
        powerStatsMap.add("strength", "1");
        powerStatsMap.add("agility", "1");
        powerStatsMap.add("dexterity", "1");
        powerStatsMap.add("intelligence", "1");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
        .params(heroMap)
        .params(powerStatsMap)).andExpect((MockMvcResultMatchers.status().isOk()));
    }

    @Test
    public void testGetHeroIdNotFound() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"+idDefault)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetHeroIdOk() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"+idDefault)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetHeroNomeNotFound() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/nome/batman")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetHeroNomeOk() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/batman")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteHeroIdNotFound() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/"+idDefault)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteHeroIdOk() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/"+idDefault)).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
