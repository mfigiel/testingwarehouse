package metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MappingMetricsImpl {

    @Autowired
    private CounterService counterService;

    private static final String PREFIX = "mapper.";
    private static final String withId = " with id:";

    public void increment(Metrics mapperName, Long id) {
        counterService.increment(PREFIX.concat(mapperName.toString()).concat(withId) + id);
    }

    public void increment(Metrics mapperName) {
        counterService.increment(PREFIX + mapperName.toString());
    }

    public enum Result {
        SUCCESS("succeed"),
        FAILED("failed"),
        MISSING("missing"),
        TOTAL("total");

        private String stringValue;

        Result(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

}
