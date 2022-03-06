package com.Services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class WebFluxAndMonoTest {
    WebFluxAndMono fm = new WebFluxAndMono();

    @Test
    void fruitFlux() {
        var fruitFlux = fm.fruitFlux();

        StepVerifier.create(fruitFlux).expectNext("mango", "orange", "banana").verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = fm.fruitMono();
        StepVerifier.create(fruitMono).expectNext("mango").verifyComplete();
    }


    @Test
    void fruitFluxFilter() {
        var fruitFilter = fm.fruitFluxFilter(5);
        StepVerifier.create(fruitFilter).expectNext("mango").verifyComplete();
    }

    @Test
    void fruitFluxFlatMap() {
        var fruitFilter = fm.fruitFluxMapAsync();
        StepVerifier.create(fruitFilter).expectNextCount(17).verifyComplete();
    }

    @Test
    void fruitFluxConcatMap() {
        var fruitFilter = fm.fruitFluxConcatMapAsync();
        StepVerifier.create(fruitFilter).expectNextCount(17).verifyComplete();
    }

    @Test
    void fruitFluxFlatMapMany() {
        var fruitFilter = fm.fruitMonoFlatMapMany();
        StepVerifier.create(fruitFilter).expectNextCount(5).verifyComplete();
    }

    @Test
    void fruitFluxDefaultEmpty() {
        var fruitFilter = fm.fruitFluxTransformDefault(10);
        StepVerifier.create(fruitFilter).expectNext("default").verifyComplete();
    }

    //"I","Love","God"
    @Test
    void fruitFluxTransformDefaultswitch() {
        var fruitFilter = fm.fruitFluxTransformDefault(10);
        StepVerifier.create(fruitFilter).expectNext("default").verifyComplete();

    }

    @Test
    void fruitsFluxConcat() {
        var fruitFilter = fm.fruitsFluxConcat();
        StepVerifier.create(fruitFilter).expectNext("mango", "tomato").verifyComplete();


    }

    @Test
    void fruitsMonoConcatwith() {

        var fruitFilter = fm.fruitsMonoConcatwith();
        StepVerifier.create(fruitFilter).expectNext("mango", "tomato").verifyComplete();
    }

    @Test
    void fruitsMonoMergeWith() {
        var fruitFilter = fm.fruitsMonoMergeWith();
        StepVerifier.create(fruitFilter).expectNext("mango", "tomato").verifyComplete();
    }

    @Test
    void fruitFluxZip() {
        var fruitFilter = fm.fruitFluxZip();
        StepVerifier.create(fruitFilter).expectNext("mangotomato", "orangelemon").verifyComplete();
    }

    @Test
    void fruitFluxFilterDoon() {
        var fruitFilter = fm.fruitFluxFilterDoon(5);
        StepVerifier.create(fruitFilter).expectNext("mango").verifyComplete();
    }

    @Test
    void fruitFluxOnError() {

        var fruitFilter = fm.fruitFluxOnError();

        StepVerifier.create(fruitFilter).expectNext("Apple","Mango","Orange").verifyComplete();

    }

    @Test
    void fruitFluxOnErrorContinue() {
        var fruitFilter = fm.fruitFluxOnErrorContinue();

        StepVerifier.create(fruitFilter).expectNext("APPLE","ORANGE").verifyComplete();

    }
    @Test
    void fruitFluxOnErrorMap() {
        var fruitFilter = fm.fruitFluxOnErrorMap();

        StepVerifier.create(fruitFilter).expectNext("APPLE").expectError(IllegalStateException.class).verify();

    }
}
