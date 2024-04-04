package com.example.accountservice.account;


import com.example.accountservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;


class AccountApiTest extends ApiTest {

    @Test
    void 회원등록() {
        // given
        final AddAccountRequest addAccountRequest = 회원등록요청모델_생성();
        final ValidatableResponse validatableResponse = 회원등록요청(addAccountRequest);
        assertThat(validatableResponse.extract().response().statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 회원중복검사(){
        // given
        회원등록();
        // when
        final ApiResponse apiResponse = 이메일중복요청(이메일중복검사요청모델_생성());
        // then
        Assertions.assertThat(apiResponse.success()).isFalse();
    }

    private static ApiResponse 이메일중복요청(CheckEmailDuplicationRequest checkEmailDuplicationRequest) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(checkEmailDuplicationRequest)
                .when()
                .post("/api/v1/accounts/check-email")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ApiResponse.class);
    }

    private static ValidatableResponse 회원등록요청(AddAccountRequest addAccountRequest) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(addAccountRequest)
                .when()
                .post("/api/v1/accounts")
                .then().log().all()
                .statusCode(201);
    }

    static AddAccountRequest 회원등록요청모델_생성() {
        return new AddAccountRequest("이름", "닉네임", "이메일@com", "password", EmailReceptionPolicy.YES);
    }

    static CheckEmailDuplicationRequest 이메일중복검사요청모델_생성() {
        return new CheckEmailDuplicationRequest("이메일@com");
    }
}
