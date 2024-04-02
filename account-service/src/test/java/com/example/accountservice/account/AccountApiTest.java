package com.example.accountservice.account;


import com.example.accountservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;


class AccountApiTest extends ApiTest {


    @Test
    void 회원등록() {
        // given
        final AddAccountRequest addAccountRequest = 회원등록요청모델_생성();

        final ValidatableResponse validatableResponse = RestAssured.given().log().all()
                .contentType("application/json")
                .body(addAccountRequest)
                .when()
                .post("/api/v1/accounts")
                .then().log().all()
                .statusCode(201);

        assertThat(validatableResponse.extract().response().statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static AddAccountRequest 회원등록요청모델_생성() {
        return new AddAccountRequest("이름", "닉네임", "이메일@com", "password", EmailReceptionPolicy.YES);
    }
}
