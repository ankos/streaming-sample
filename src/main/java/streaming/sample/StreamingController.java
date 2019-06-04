package streaming.sample;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;

import java.time.LocalDate;

@Controller
public class StreamingController {

    @Get(value = "/groups", processes = "text/csv")
    public Flowable<String> text() {
        return Flowable.range(1, 10).map(StreamingController::text);
    }

    private static String text(int r) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            builder.append(LocalDate.now().plusYears(i))
                    .append(", ")
                    .append("Group: ")
                    .append(r)
                    .append("\n");
        }
        return builder.toString();
    }
}
