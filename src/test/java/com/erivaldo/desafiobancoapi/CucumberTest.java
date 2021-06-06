package com.erivaldo.desafiobancoapi;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
@SpringBootTest
@CucumberContextConfiguration
public class CucumberTest {

}
