package eventside.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Event {

    protected long timestamp;
    protected String uri;
    protected String className;

    public long getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp=" + timestamp +
                ", uri='" + uri + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
