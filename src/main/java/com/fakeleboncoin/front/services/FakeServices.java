package com.fakeleboncoin.front.services;

import com.fakeleboncoin.client.ApiClient;
import com.fakeleboncoin.client.api.AnnonceControllerApi;
import com.fakeleboncoin.client.api.UserControllerApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class FakeServices {


    private ApiClient apiClient;
    private ObjectMapper oMapper;
    private UserControllerApi userController;
    private AnnonceControllerApi annonceController;
    public FakeServices() {

        apiClient = new ApiClient();

        apiClient.addAuthorization("basic", new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                Request authRequest = request.newBuilder().
                        header("Authorization", Credentials.basic("juliensibue@outlook.fr" , "Admin123")).
                        build();
                return chain.proceed(authRequest);
            }
        });
        apiClient.getAdapterBuilder().baseUrl("http://localhost:8080");


        apiClient.getOkBuilder().readTimeout(30, TimeUnit.SECONDS);

        apiClient.getOkBuilder().addInterceptor(new Interceptor() {



            @Override

            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                okhttp3.Response response = chain.proceed(request);



                // Pas un code 2xx : erreur !

                if (response.code() >= 300) {

                    throw new IOException("Erreur " + response.code() + " lors de l'appel REST : " + response.message());

                }



                return response;

            }

        });



        oMapper = new ObjectMapper();



        userController = apiClient.createService(UserControllerApi.class);
        annonceController = apiClient.createService(AnnonceControllerApi.class);

    }

    public UserControllerApi getUserController() {
        return userController;
    }
    public AnnonceControllerApi getAnnonceController() {
        return annonceController;
    }
}
