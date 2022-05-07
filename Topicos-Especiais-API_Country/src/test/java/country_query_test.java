
import org.apache.commons.lang3.ObjectUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class country_query_test extends Base{
    // Teste verica se o conteúdo de restorno é String.
    @Test
    public void verifica_contrato_country(){

        given().
                header("Content-Type","application/json").
                body(Query).
                when().
                post(URL).
        then().
                assertThat().
                statusCode(200).
                body("data.country.code",instanceOf(String.class)).
                body("data.country.name",instanceOf(String.class)).
                body("data.country.native",instanceOf(String.class)).
                body("data.country.phone",instanceOf(String.class));
    }
    // Teste verifica se o valor da requisição retorna nulo.
    @Test
    public void Verifica_Country_Nulo(){

        given().
                header("Content-Type","application/json").
                body(Query_Null).
                when().
                post(URL).
                then().
                assertThat().
                statusCode(200).
                //A matriz JSON é uma lista de valores, portanto, em vez de emptyArray(), use empty()
                body("data.country",anyOf(nullValue(),empty()));

    }
    // Teste valida se o retorno da consulta é errado, pois a consulta está quebrada.
    @Test
    public void Verifica_Status_400() {

        given().
                header("Content-Type", "application/json").
                body(Query_Errada).
                when().
                post(URL).
                then().
                assertThat().
                statusCode(400).log().body();

    }

}




