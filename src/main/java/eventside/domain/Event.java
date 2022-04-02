package eventside.domain;

public abstract class Event {

    protected long timestamp;
    protected String uri;

    public long getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }
}
