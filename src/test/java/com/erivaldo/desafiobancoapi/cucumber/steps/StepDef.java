package com.erivaldo.desafiobancoapi.cucumber.steps;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class StepDef {
    protected MockMvc mockMvc;
    private Gson gson = new Gson();

    public String execPost(String url, Object conteudo) throws Exception {

        MvcResult mvcResult = mockMvc.perform(post(url)
                .content(gson.toJson(conteudo))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        return mvcResult.getResponse().getContentAsString();
    }
}
