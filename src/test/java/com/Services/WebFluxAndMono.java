package com.Services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.PublicKey;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class WebFluxAndMono {
    public Flux<String> fruitFlux(){

       return  Flux.fromIterable(List.of("mango","orange","banana")).log();
    }

    public Flux<String> fruitFluxFilter(int number){

        return  Flux.fromIterable(List.of("mango","orange","banana")).filter(s->s.length()<=number);
    }


    public Flux<String> fruitFluxMap(){
      //  return Mono.justOrEmpty(Optional.ofNullable());

        return  Flux.fromIterable(List.of("mango","orange","banana")).map(String::toUpperCase).filter(s->s.length()<=4).log();
    }


    public Flux<String> fruitFluxMapAsync(){
        //Flatmap will not preserve order as we can go with Asyncronus calls
        return  Flux.fromIterable(List.of("mango","orange","banana")).flatMap(w->Flux.just(w.split(""))).delayElements(Duration.ofMillis(new Random().nextInt(1000))).log();


    }

    public  Flux<String> fruitFluxTransform(int number){

        Function<Flux<String>,Flux<String>> filterData=data->data.filter(s->s.length()>number);
        return Flux.fromIterable(List.of("mango","orange","banana")).transform(filterData).log();
    }

    public  Flux<String> fruitFluxTransformDefault(int number){

        Function<Flux<String>,Flux<String>> filterData=data->data.filter(s->s.length()>number);
        return Flux.fromIterable(List.of("mango","orange","banana")).transform(filterData).defaultIfEmpty("default").log();
    }

    public  Flux<String> fruitFluxTransformDefaultswitch(int number){

        Function<Flux<String>,Flux<String>> filterData=data->data.filter(s->s.length()>number);
        return Flux.fromIterable(List.of("mango","orange","banana")).transform(filterData).switchIfEmpty(Flux.just("I","Love","God")).log();
    }

    public Flux<String> fruitsFluxConcat(){

        var fruits=Flux.just("mango");
        var veggies =Flux.just("tomato");
        return Flux.concat(fruits,veggies).log();
    }

    public Flux<String> fruitsFluxConcatwith(){

        var fruits=Flux.just("mango");
        var veggies =Flux.just("tomato");
        return fruits.concatWith(veggies);
    }
//we have merge,mergewith and mergewithsequanical
    //by default merge is NOT sequncial
    public Flux<String> fruitsMonoMergeWith(){

        var fruits=Mono.just("mango").delayElement(Duration.ofMillis(50));
        var veggies =Mono.just("tomato").delayElement(Duration.ofMillis(75));
        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsMonoConcatwith(){

        var fruits=Mono.just("mango");
        var veggies =Mono.just("tomato");
        return fruits.concatWith(veggies);
    }



    public Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("mango").flatMapMany(s->Flux.just(s.split(""))).log();
    }

    public Flux<String> fruitFluxConcatMapAsync(){
        //Flatmap will not preserve order as we can go with Asyncronus calls
        return  Flux.fromIterable(List.of("mango","orange","banana")).concatMap(w->Flux.just(w.split(""))).delayElements(Duration.ofMillis(new Random().nextInt(1000))).log();


    }


    public Mono<String> fruitMono(){
        return  Mono.just("mango").log();
    }


    //zip and zipwith
    public Flux<String> fruitFluxZip(){
        var fruits=Flux.just("mango","orange");
        var veggies =Flux.just("tomato","lemon");
        //max 8 publisher
       return Flux.zip(fruits,veggies,(first,second)->first+second).log();


    }

    public Flux<String> fruitFluxZiptouple(){
        var fruits=Flux.just("mango","orange");
        var veggies =Flux.just("tomato","lemon");
        var moreveggies =Flux.just("jini","mini");
        //max 8 publisher
        return Flux.zip(fruits,veggies,moreveggies).map(objects -> objects.getT1()+objects.getT2()+objects.getT3());


    }


    //debug , send notification
    //side call
    public Flux<String> fruitFluxFilterDoon(int number){

        return  Flux.fromIterable(List.of("mango","orange","banana")).filter(s->s.length()<=number).doOnNext(s->
                System.out.println(s));
    }


    //Exceptions
    //1) onerrorReturn

    public Flux<String> fruitFluxOnError(){
        return Flux.just("Apple","Mango").concatWith(Flux.error(new RuntimeException("Exception"))).onErrorReturn("Orange").log();
    }


    public Flux<String> fruitFluxOnErrorContinue(){
        return Flux.just("Apple","Mango","Orange").map(
                s->{
                    if(s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("Exception Occured");
                    return s.toUpperCase();
                }).onErrorContinue((w,f)->{
            System.out.println(w);
            System.out.println(f);
        });

    }


    //Onerror map mapping exceptions to the custom exception
    public Flux<String> fruitFluxOnErrorMap(){
        return Flux.just("Apple","Mango","Orange").map(
                s->{
                    if(s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("Exception Occured");
                    return s.toUpperCase();
                }).onErrorMap(t->{
            System.out.println(t);
            return  new IllegalStateException("Ille");
        });



    }



    public static void main(String[] args) {
        WebFluxAndMono fm=new WebFluxAndMono();
     /*   fm.fruitFlux().subscribe(s->{
            System.out.println(s);
        });
        fm.fruitFluxMap().subscribe(s->{
            System.out.println(s);
        });*/

        fm.fruitFluxMapAsync().subscribe(s->{
            System.out.println("jj");
        });
    }
}
