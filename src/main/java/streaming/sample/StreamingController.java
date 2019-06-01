package streaming.sample;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;

import java.time.LocalDateTime;

@Controller("/api")
public class StreamingController {

    @Get(value = "/values", processes = "text/csv")
    public Flowable<String> text() {
        return Flowable.range(1, 1000).map(x -> LocalDateTime.now().toString() + ",comment + " + x + "\n");
    }

}