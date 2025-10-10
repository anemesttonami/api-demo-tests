package in.reqres.utils;

import io.qameta.allure.Step;

import java.time.Duration;
import java.time.Instant;

public class TestHelper {

    @Step("Разница между текущим и сегенерированным временем не больше 10 сек?")
    public static boolean isTimeDiffNotMoreThanTenSeconds(String generatedTime){
        Instant currentTime = Instant.now();
        Instant time = Instant.parse(generatedTime);

        return Math.abs(Duration.between(currentTime, time).getSeconds()) < 11;
    }
}
