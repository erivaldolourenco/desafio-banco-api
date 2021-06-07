package com.erivaldo.desafiobancoapi.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.erivaldo.desafiobancoapi.controller.AccountController;
import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.dto.BalanceDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.controller.form.BalanceForm;
import com.erivaldo.desafiobancoapi.service.AccountService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import com.google.gson.Gson;

public class DepositSteps extends StepDef{
	
	@Autowired
	AccountService accountService;
	
    @Autowired
    private AccountController accountController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).addFilter(((request, response, chain) -> {
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        })).build();
    }
    
    private String response;
    private Exception error;
    private BalanceForm balanceForm;
    private BalanceDto balanceDto;
    private AccountForm accountForm = null;
    private AccountDto accountDto;
    private Gson gson = new Gson();
    
    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(DataTable table) throws Exception {
	     
		 List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		 
		 for (Map<String, String> columns : rows) {
		        accountForm = new AccountForm();
		        accountForm.setAccountNumber(Long.parseLong(columns.get("Numero Conta")));
		        accountForm.setBalance(Double.parseDouble(columns.get("Saldo")));
				 accountForm.setName("Usuario Teste");
				 accountForm.setCpf("99999999999");
		    }

		String reponseAccount =  execPost("/accounts", accountForm);
		accountDto = gson.fromJson(reponseAccount, AccountDto.class);
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String string) {
    	
    	balanceForm = new BalanceForm();
    	balanceForm.setAccountId(accountDto.getAccountId());
    	balanceForm.setValue(Double.parseDouble(string));
        try {
            response = execPost("/accounts/deposit", balanceForm);
            balanceDto = gson.fromJson(response, BalanceDto.class);
        }catch (Exception e) {
            error = e;
            System.out.println(error.getMessage());
        }
    }
    
    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() {
    	assertNotNull(balanceDto);	
    }
    
    @Então("deverá ser apresentada a seguinte mensagem {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem(String string) {
    	
    	assertEquals(string, balanceDto.getMessage() );
    }
    
    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String string, String string2) {
    	assertEquals(string, balanceDto.getBalance());
    }
    
}
