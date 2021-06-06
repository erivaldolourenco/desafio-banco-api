package com.erivaldo.desafiobancoapi.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.erivaldo.desafiobancoapi.controller.AccountController;
import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.controller.form.BalanceForm;
import com.erivaldo.desafiobancoapi.model.Account;
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
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    
    @Dado("que existam as seguintes contas")
    public void que_existam_as_seguintes_contas(DataTable table) {
    	System.out.println("");
    }

    @Dado("que seja solicitado um depósito de {string}")
    public void que_seja_solicitado_um_depósito_de(String string) {
    	System.out.println("");
    }
    @Quando("for executada a operação de depósito")
    public void for_executada_a_operação_de_depósito() {
    	System.out.println("");
    }
    @Então("deverá ser apresentada a seguinte mensagem {string}")
    public void deverá_ser_apresentada_a_seguinte_mensagem(String string) {
    	System.out.println("");
    }
    @Então("o saldo da conta {string} deverá ser de {string}")
    public void o_saldo_da_conta_deverá_ser_de(String string, String string2) {
    	System.out.println("");
    }
    
}
