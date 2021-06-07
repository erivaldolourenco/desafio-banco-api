	package com.erivaldo.desafiobancoapi.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.erivaldo.desafiobancoapi.controller.AccountController;
import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.model.Account;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import com.google.gson.Gson;

public class CreateAcoountSteps extends StepDef{
	
	private AccountForm account;
    private Exception error;
    private String response;
    private Gson gson = new Gson();

    @Autowired
    private AccountController accountController;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).addFilter(((request, response, chain) -> {
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        })).build();
    }
    
    
    @Dado("que seja solicitada a criação de uma nova conta com os seguintes valores")
    public void que_seja_solicitada_a_criação_de_uma_nova_conta_com_os_seguintes_valores(DataTable table) {
    	 List<Map<String, String>> rows = table.asMaps(String.class, String.class);
    	 for (Map<String, String> columns : rows) {
             account = new AccountForm();
             account.setName(columns.get("Nome"));
             account.setCpf(columns.get("Cpf"));
             account.setBalance(Double.parseDouble(columns.get("Saldo")));
         }
    	
    }
    
    @Quando("for enviada a solicitação de criação de nova conta")
    public void for_enviada_a_solicitação_de_criação_de_nova_conta() throws Exception {
        try {
            response = execPost("/accounts", account);
        }catch (Exception e) {
            error = e;
        }
    }


    @Então("deverá ser apresentada a seguinte mensagem de erro {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem_de_erro(String string) {
    	String message;
    	if(error == null) {
    		message = gson.fromJson(response, AccountDto.class).getMessage();
    	}else {
    		message = error.getCause().getLocalizedMessage();
    		System.out.println(error.getCause().getLocalizedMessage());
    	}
        assertEquals(string, message);
    }
    
    @Então("deverá ser retornado o número da conta criada")
    public void deverá_ser_retornado_o_número_da_conta_criada() {
    	
    	AccountDto accountDto = gson.fromJson(response, AccountDto.class);
    	assertNotNull(accountDto);
    }
    
    @E("deverá ser apresentada a mensagem {string}")
    public void deverá_ser_apresentada_a_mensagem(String string) {
    	AccountDto accountDto = gson.fromJson(response, AccountDto.class);
    	 assertEquals(string, accountDto.getMessage() );
    }
}
